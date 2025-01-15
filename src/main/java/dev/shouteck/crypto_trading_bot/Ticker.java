package dev.shouteck.crypto_trading_bot;

import jakarta.persistence.Id;
import java.math.BigDecimal;

public class Ticker {

    @Id
    private String symbol;
    private BigDecimal bidPrice;
    private BigDecimal bidQuantity;
    private BigDecimal askPrice;
    private BigDecimal askQuantity;

    public Ticker() {}

    public Ticker(String symbol, BigDecimal bidPrice, BigDecimal bidQuantity, BigDecimal askPrice, BigDecimal askQuantity) {
        this.symbol = symbol;
        this.bidPrice = bidPrice;
        this.bidQuantity = bidQuantity;
        this.askPrice = askPrice;
        this.askQuantity = askQuantity;
    }

    public BigDecimal getAskQuantity() {
        return askQuantity;
    }

    public void setAskQuantity(BigDecimal askQuantity) {
        this.askQuantity = askQuantity;
    }

    public BigDecimal getAskPrice() {
        return askPrice;
    }

    public void setAskPrice(BigDecimal askPrice) {
        this.askPrice = askPrice;
    }

    public BigDecimal getBidQuantity() {
        return bidQuantity;
    }

    public void setBidQuantity(BigDecimal bidQuantity) {
        this.bidQuantity = bidQuantity;
    }

    public BigDecimal getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(BigDecimal bidPrice) {
        this.bidPrice = bidPrice;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
