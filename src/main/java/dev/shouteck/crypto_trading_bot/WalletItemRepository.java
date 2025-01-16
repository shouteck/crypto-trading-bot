package dev.shouteck.crypto_trading_bot;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletItemRepository extends JpaRepository<WalletItem, Long> {
    Optional<WalletItem> findByWalletAndSymbol(Wallet wallet, String symbol);
}
