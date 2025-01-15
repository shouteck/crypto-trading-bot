package dev.shouteck.crypto_trading_bot;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class BinanceRestClient {

    private final RestClient restClient;

    public BinanceRestClient(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("https://api.binance.com/api/v3/ticker/bookTicker")
                .build();
    }

    Ticker findBySymbol(String symbol) {
        return restClient.get()
                .uri("?symbol={symbol}", symbol)
                .retrieve()
                .body(Ticker.class);
    }

}
