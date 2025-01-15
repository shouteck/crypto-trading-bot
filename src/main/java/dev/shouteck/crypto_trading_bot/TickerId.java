package dev.shouteck.crypto_trading_bot;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TickerId implements Serializable {

    private String symbol;
    private String service;

    public TickerId() {}

    public TickerId(String symbol, String service) {
        this.symbol = symbol;
        this.service = service;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TickerId tickerId = (TickerId) o;
        return symbol.equals(tickerId.symbol) && service.equals(tickerId.service);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, service);
    }
}
