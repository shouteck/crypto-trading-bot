package dev.shouteck.crypto_trading_bot;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WalletService {

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;

    public WalletService(UserRepository userRepository, WalletRepository walletRepository) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
    }

    public Wallet getWalletForUser(String username) {
        CryptoUser user = userRepository.findByUsername(username);
        if (user != null && user.getWallet() != null) {
            return user.getWallet();
        }
        return null;
    }

    public void adjustCashBalance(String username, BigDecimal amount) {
        CryptoUser user = userRepository.findByUsername(username);
        if (user != null && user.getWallet() != null) {
            Wallet wallet = user.getWallet();
            wallet.setCashBalance(wallet.getCashBalance().add(amount));
            walletRepository.save(wallet);
        }
    }
}
