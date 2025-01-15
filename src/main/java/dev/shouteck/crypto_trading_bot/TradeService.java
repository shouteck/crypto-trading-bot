package dev.shouteck.crypto_trading_bot;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TradeService {

    private final UserRepository userRepository;
    private final TradeRepository tradeRepository;
    private final AggregatedTickerService aggregatedTickerService;

    public TradeService(UserRepository userRepository, TradeRepository tradeRepository, AggregatedTickerService aggregatedTickerService) {
        this.userRepository = userRepository;
        this.tradeRepository = tradeRepository;
        this.aggregatedTickerService = aggregatedTickerService;
    }

    public String trade(String username, String symbol, BigDecimal quantity, boolean isBuyOrder) {
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

        BigDecimal price = isBuyOrder ? aggregatedPrice.getAskPrice() : aggregatedPrice.getBidPrice();
        BigDecimal totalCost = price.multiply(quantity);

        if (isBuyOrder) {
            if (quantity.compareTo(aggregatedPrice.getAskQuantity()) > 0) {
                return "Cannot buy more than the available quantity at the best ask price";
            }
            BigDecimal totalPrice = aggregatedPrice.getAskPrice().multiply(quantity);
            if (totalPrice.compareTo(user.getWalletBalance()) > 0) {
                return "Insufficient funds for the buy order";
            }
        }

        if (isBuyOrder) {
            user.setWalletBalance(user.getWalletBalance().subtract(totalCost));
        } else {
            user.setWalletBalance(user.getWalletBalance().add(totalCost));
        }

        userRepository.save(user);

        Trade trade = new Trade(user, symbol, quantity, price, isBuyOrder);
        tradeRepository.save(trade);

        return "Trade successful";
    }
}
