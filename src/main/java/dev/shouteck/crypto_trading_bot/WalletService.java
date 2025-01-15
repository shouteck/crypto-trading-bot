package dev.shouteck.crypto_trading_bot;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WalletService {

    private final UserRepository userRepository;

    public WalletService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public BigDecimal getWalletBalance(String username) {
        CryptoUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        return user.getWalletBalance();
    }
}
