package dev.shouteck.crypto_trading_bot;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private CryptoUser cryptoUser;

    @Column(precision = 20, scale = 8)
    private BigDecimal cashBalance;

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<WalletItem> walletItems = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CryptoUser getCryptoUser() {
        return cryptoUser;
    }

    public void setCryptoUser(CryptoUser cryptoUser) {
        this.cryptoUser = cryptoUser;
    }

    public BigDecimal getCashBalance() {
        return cashBalance;
    }

    public void setCashBalance(BigDecimal cashBalance) {
        this.cashBalance = cashBalance;
    }

    public Set<WalletItem> getWalletItems() {
        return walletItems;
    }

    public void setWalletItems(Set<WalletItem> walletItems) {
        this.walletItems = walletItems;
    }
}
