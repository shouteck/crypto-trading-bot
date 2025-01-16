package dev.shouteck.crypto_trading_bot;

import java.math.BigDecimal;
import java.util.List;

public class WalletBalanceDto {

    private BigDecimal cashBalance;
    private List<WalletItemDto> walletItems;

    public WalletBalanceDto(BigDecimal cashBalance, List<WalletItemDto> walletItems) {
        this.cashBalance = cashBalance;
        this.walletItems = walletItems;
    }

    // Getters and Setters
    public BigDecimal getCashBalance() {
        return cashBalance;
    }

    public void setCashBalance(BigDecimal cashBalance) {
        this.cashBalance = cashBalance;
    }

    public List<WalletItemDto> getWalletItems() {
        return walletItems;
    }

    public void setWalletItems(List<WalletItemDto> walletItems) {
        this.walletItems = walletItems;
    }
}