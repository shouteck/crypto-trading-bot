package dev.shouteck.crypto_trading_bot;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class HuobiTickerService {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String HUOBI_API_URL = "https://api.huobi.pro/market/tickers";

    public List<Ticker> fetchTickers() {
        List<Ticker> tickers = new ArrayList<>();

        JsonNode response = restTemplate.getForObject(HUOBI_API_URL, JsonNode.class);
        if (response != null && response.has("data")) {
            for (JsonNode node : response.get("data")) {
                if (!node.get("symbol").asText().equals("btcusdt") && !node.get("symbol").asText().equals("ethusdt"))
                    continue;
                tickers.add(new Ticker(
                        node.get("symbol").asText(),
                        new BigDecimal(node.get("bid").asText()),
                        new BigDecimal(node.get("bidSize").asText()),
                        new BigDecimal(node.get("ask").asText()),
                        new BigDecimal(node.get("askSize").asText())
                ));
            }
        }

        return tickers;
    }
}
