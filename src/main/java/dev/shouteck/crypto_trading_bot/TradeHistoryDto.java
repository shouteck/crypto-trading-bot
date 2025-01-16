package dev.shouteck.crypto_trading_bot;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TradeHistoryDto {
    private String symbol;
    private BigDecimal quantity;
    private BigDecimal price;
    private boolean isBuyOrder;
    private LocalDateTime tradeTimestamp;

    // Constructor
    public TradeHistoryDto(String symbol, BigDecimal quantity, BigDecimal price, boolean isBuyOrder, LocalDateTime tradeTimestamp) {
        this.symbol = symbol;
        this.quantity = quantity;
        this.price = price;
        this.isBuyOrder = isBuyOrder;
        this.tradeTimestamp = tradeTimestamp;
    }

    // Getters and Setters
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isBuyOrder() {
        return isBuyOrder;
    }

    public void setBuyOrder(boolean buyOrder) {
        isBuyOrder = buyOrder;
    }

    public LocalDateTime getTradeTimestamp() {
        return tradeTimestamp;
    }

    public void setTradeTimestamp(LocalDateTime tradeTimestamp) {
        this.tradeTimestamp = tradeTimestamp;
    }
}
