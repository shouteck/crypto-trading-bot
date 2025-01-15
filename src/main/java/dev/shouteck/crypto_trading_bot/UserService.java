package dev.shouteck.crypto_trading_bot;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public CryptoUser createUser(String username, BigDecimal walletBalance) {
        if (userRepository.findByUsername(username) != null) {
            throw new IllegalArgumentException("Username already exists");
        }

        CryptoUser user = new CryptoUser();
        user.setUsername(username);
        user.setWalletBalance(walletBalance);
        return userRepository.save(user);
    }
}
