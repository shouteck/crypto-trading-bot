package dev.shouteck.crypto_trading_bot;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "TICKER")
public class TickerEntity {

    @EmbeddedId
    private TickerId id;

    @Column(precision = 20, scale = 8)
    private BigDecimal bidPrice;
    @Column(precision = 20, scale = 8)
    private BigDecimal bidQuantity;
    @Column(precision = 20, scale = 8)
    private BigDecimal askPrice;
    @Column(precision = 20, scale = 8)
    private BigDecimal askQuantity;

    public TickerEntity() {}

    public TickerEntity(Ticker ticker, String service) {
        this.id = new TickerId(ticker.getSymbol(), service);
        this.bidPrice = ticker.getBidPrice();
        this.bidQuantity = ticker.getBidQuantity();
        this.askPrice = ticker.getAskPrice();
        this.askQuantity = ticker.getAskQuantity();
    }

    public TickerId getId() {
        return id;
    }

    public void setId(TickerId id) {
        this.id = id;
    }

    public BigDecimal getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(BigDecimal bidPrice) {
        this.bidPrice = bidPrice;
    }

    public BigDecimal getBidQuantity() {
        return bidQuantity;
    }

    public void setBidQuantity(BigDecimal bidQuantity) {
        this.bidQuantity = bidQuantity;
    }

    public BigDecimal getAskPrice() {
        return askPrice;
    }

    public void setAskPrice(BigDecimal askPrice) {
        this.askPrice = askPrice;
    }

    public BigDecimal getAskQuantity() {
        return askQuantity;
    }

    public void setAskQuantity(BigDecimal askQuantity) {
        this.askQuantity = askQuantity;
    }
}