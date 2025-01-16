package dev.shouteck.crypto_trading_bot;

public class InsufficientQuantityException extends RuntimeException {
    public InsufficientQuantityException(String message) {
        super(message);
    }
}
