package dev.shouteck.crypto_trading_bot;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class TickerAggregatorService {

    private final HuobiTickerService huobiTickerService;
    private final BinanceTickerService binanceTickerService;

    public TickerAggregatorService(HuobiTickerService huobiTickerService, BinanceTickerService binanceTickerService) {
        this.huobiTickerService = huobiTickerService;
        this.binanceTickerService = binanceTickerService;
    }

    public List<Ticker> fetchCombinedTickers() {
        List<Ticker> combinedTickers = new ArrayList<>();

        // Fetch tickers from Huobi
        combinedTickers.addAll(huobiTickerService.fetchTickers());

        // Fetch tickers from Binance
        combinedTickers.addAll(binanceTickerService.fetchTickers());

        return combinedTickers;
    }
}