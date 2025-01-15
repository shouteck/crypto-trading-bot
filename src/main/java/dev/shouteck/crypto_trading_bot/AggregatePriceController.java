package dev.shouteck.crypto_trading_bot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AggregatePriceController {

    private final AggregatedTickerService aggregatedTickerService;

    public AggregatePriceController(AggregatedTickerService aggregatedTickerService) {
        this.aggregatedTickerService = aggregatedTickerService;
    }

    @GetMapping("/aggregated-tickers")
    public List<AggregatedPrice> getAggregatedTickers() {
        return aggregatedTickerService.getAggregatedBestPrices();
    }
}
