package dev.shouteck.crypto_trading_bot;

import java.math.BigDecimal;

public class AdjustBalanceRequest {

    private String username;
    private BigDecimal amount;

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
