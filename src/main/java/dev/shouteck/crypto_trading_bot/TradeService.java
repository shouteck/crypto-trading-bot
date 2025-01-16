package dev.shouteck.crypto_trading_bot;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TradeService {

    private final UserRepository userRepository;
    private final TradeRepository tradeRepository;
    private final AggregatedTickerService aggregatedTickerService;
    private final WalletItemRepository walletItemRepository;
    private final WalletRepository walletRepository;

    public TradeService(UserRepository userRepository, TradeRepository tradeRepository,
                        AggregatedTickerService aggregatedTickerService, WalletItemRepository walletItemRepository,
                        WalletRepository walletRepository) {
        this.userRepository = userRepository;
        this.tradeRepository = tradeRepository;
        this.aggregatedTickerService = aggregatedTickerService;
        this.walletItemRepository = walletItemRepository;
        this.walletRepository = walletRepository;
    }

    public List<TradeHistoryDto> getTradeHistoryForUser(CryptoUser user) {
        List<Trade> trades = tradeRepository.findAllByCryptoUser(user);

        return trades.stream()
                .map(trade -> new TradeHistoryDto(
                        trade.getSymbol(),
                        trade.getQuantity(),
                        trade.getPrice(),
                        trade.isBuyOrder(),
                        trade.getTradeTimestamp()
                ))
                .collect(Collectors.toList());
    }

    public void trade(String username, String symbol, BigDecimal quantity, boolean isBuyOrder) {
        CryptoUser user = userRepository.findByUsername(username);

        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        AggregatedPrice aggregatedPrice = aggregatedTickerService
                .getAggregatedBestPrices()
                .stream()
                .filter(dto -> dto.getSymbol().equalsIgnoreCase(symbol))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Symbol not found"));

        Optional<WalletItem> existingWalletItem = walletItemRepository.findByWalletAndSymbol(user.getWallet(), symbol);

        if (isBuyOrder) {

            BigDecimal availableQuantity = aggregatedPrice.getBidQuantity().min(quantity);
            BigDecimal totalCost = aggregatedPrice.getBidPrice().multiply(availableQuantity);

            if (user.getWallet().getCashBalance().compareTo(totalCost) < 0) {
                throw new InsufficientBalanceException("Insufficient cash balance for buy order: Available balance is less than the required cost.");
            }
            // For a buy, decrease the cash balance
            user.getWallet().setCashBalance(user.getWallet().getCashBalance().subtract(totalCost));

            if (existingWalletItem.isPresent()) {
                // Update the existing WalletItem's quantity
                WalletItem walletItem = existingWalletItem.get();
                walletItem.setQuantity(walletItem.getQuantity().add(availableQuantity));
                walletItemRepository.save(walletItem);
            } else {
                // Create a new WalletItem if none exists
                WalletItem newWalletItem = new WalletItem(user.getWallet(), symbol, availableQuantity);
                walletItemRepository.save(newWalletItem);
            }
        } else {

            if (existingWalletItem.isEmpty()) {
                throw new InsufficientQuantityException("You do not own any " + symbol + " to sell.");
            }

            if (quantity.compareTo(aggregatedPrice.getAskQuantity()) > 0) {
                throw new InsufficientQuantityException("Insufficient quantity for buy order: Available bid quantity is less than requested.");
            }

            BigDecimal availableQuantity = aggregatedPrice.getAskQuantity().min(quantity);
            BigDecimal totalEarnings = aggregatedPrice.getAskPrice().multiply(availableQuantity);

            user.getWallet().setCashBalance(user.getWallet().getCashBalance().add(totalEarnings));

            // For a sell, decrease the owned quantity of the symbol in wallet
            BigDecimal updatedQuantity = existingWalletItem.get().getQuantity().subtract(availableQuantity);
            if (updatedQuantity.equals(BigDecimal.ZERO)) {
                walletItemRepository.delete(existingWalletItem.get());
            }
            else {
                existingWalletItem.get().setQuantity(updatedQuantity);
                walletItemRepository.save(existingWalletItem.get());
            }
        }

        Trade trade = new Trade();
        trade.setCryptoUser(user);
        trade.setSymbol(symbol);
        trade.setQuantity(quantity);
        trade.setPrice(isBuyOrder ? aggregatedPrice.getAskPrice() : aggregatedPrice.getBidPrice());
        trade.setBuyOrder(isBuyOrder);
        trade.setTradeTimestamp(LocalDateTime.now());
        tradeRepository.save(trade);

    }
}
