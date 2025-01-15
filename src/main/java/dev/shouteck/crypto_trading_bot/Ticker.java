package dev.shouteck.crypto_trading_bot;

import java.math.BigDecimal;

public record Ticker(
    String symbol,
    BigDecimal bidPrice,
    BigDecimal bidQty,
    BigDecimal askPrice,
    BigDecimal askQty
) { }
