package dev.shouteck.crypto_trading_bot;

import java.math.BigDecimal;

public class WalletItemDto {

    private String symbol;
    private BigDecimal quantity;

    public WalletItemDto(String symbol, BigDecimal quantity) {
        this.symbol = symbol;
        this.quantity = quantity;
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
}
