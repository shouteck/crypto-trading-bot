package dev.shouteck.crypto_trading_bot;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<CryptoUser, Long> {
    CryptoUser findByUsername(String username);
}
