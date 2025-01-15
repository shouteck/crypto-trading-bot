package dev.shouteck.crypto_trading_bot;

import java.math.BigDecimal;

public record BinanceTicker(
    String symbol,
    BigDecimal bidPrice,
    BigDecimal bidQty,
    BigDecimal askPrice,
    BigDecimal askQty
) { }
