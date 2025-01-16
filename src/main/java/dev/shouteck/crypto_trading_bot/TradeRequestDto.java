package dev.shouteck.crypto_trading_bot;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class TradeRequestDto {
    private String username;
    private String symbol;
    private BigDecimal quantity;
    @JsonProperty("isBuyOrder")
    private boolean isBuyOrder;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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

    public boolean isBuyOrder() {
        return isBuyOrder;
    }

    public void setBuyOrder(boolean buyOrder) {
        isBuyOrder = buyOrder;
    }
}