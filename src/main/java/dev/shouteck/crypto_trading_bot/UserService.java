package dev.shouteck.crypto_trading_bot;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;

    public UserService(UserRepository userRepository, WalletRepository walletRepository) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
    }

    public CryptoUser createUser(String username) {
        if (userRepository.findByUsername(username) != null) {
            throw new IllegalArgumentException("Username already exists");
        }

        CryptoUser user = new CryptoUser();
        user.setUsername(username);
        user = userRepository.save(user);

        Wallet wallet = new Wallet();
        wallet.setCryptoUser(user);
        wallet.setCashBalance(new BigDecimal("50000"));
        walletRepository.save(wallet);

        user.setWallet(wallet);
        return userRepository.save(user);
    }
}
