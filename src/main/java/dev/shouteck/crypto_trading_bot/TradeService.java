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
        User user = userRepository.findByUsername(username);
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

        if (isBuyOrder && user.getWalletBalance().compareTo(totalCost) < 0) {
            throw new IllegalArgumentException("Insufficient balance");
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
