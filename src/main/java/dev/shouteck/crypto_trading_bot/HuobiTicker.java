package dev.shouteck.crypto_trading_bot;

import java.math.BigDecimal;

public record HuobiTicker(
    String symbol,
    BigDecimal open,
    BigDecimal high,
    BigDecimal low,
    BigDecimal close,
    BigDecimal amount,
    BigDecimal vol,
    Integer count,
    BigDecimal bid,
    BigDecimal bidSize,
    BigDecimal ask,
    BigDecimal askSize
) { }
