package dev.shouteck.crypto_trading_bot;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String username) {
        super("User with username " + username + " not found.");
    }
}
