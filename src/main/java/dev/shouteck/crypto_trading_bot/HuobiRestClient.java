package dev.shouteck.crypto_trading_bot;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class HuobiRestClient {

    private final RestClient restClient;

    public HuobiRestClient(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("https://api.huobi.pro/market/tickers")
                .build();
    }

}
