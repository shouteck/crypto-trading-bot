package dev.shouteck.crypto_trading_bot;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class BinanceTickerService {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String BINANCE_API_URL = "https://api.binance.com/api/v3/ticker/bookTicker";

    public List<Ticker> fetchTickers() {
        List<Ticker> tickers = new ArrayList<>();

        JsonNode response = restTemplate.getForObject(BINANCE_API_URL, JsonNode.class);
        if (response != null && response.isArray()) {
            for (JsonNode node : response) {
                tickers.add(new Ticker(
                        node.get("symbol").asText(),
                        new BigDecimal(node.get("bidPrice").asText()),
                        new BigDecimal(node.get("bidQty").asText()),
                        new BigDecimal(node.get("askPrice").asText()),
                        new BigDecimal(node.get("askQty").asText())
                ));
            }
        }

        return tickers;
    }
}
