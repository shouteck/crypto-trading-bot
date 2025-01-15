package dev.shouteck.crypto_trading_bot;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TickerScheduler {

    private final BinanceTickerService binanceTickerService;
    private final HuobiTickerService huobiTickerService;
    private final TickerRepository tickerRepository;

    public TickerScheduler(BinanceTickerService binanceTickerService, HuobiTickerService huobiTickerService,
                           TickerRepository tickerRepository) {
        this.binanceTickerService = binanceTickerService;
        this.huobiTickerService = huobiTickerService;
        this.tickerRepository = tickerRepository;
    }

    @Scheduled(fixedRate = 10000) // Every 10 seconds
    public void fetchTickersPeriodically() {

        List<Ticker> binanceTickers = binanceTickerService.fetchTickers();
        List<Ticker> huobiTickers = huobiTickerService.fetchTickers();

        binanceTickers.forEach(ticker -> {
            TickerEntity tickerEntity = new TickerEntity(ticker, "Binance");
            tickerRepository.save(tickerEntity);
        });
        huobiTickers.forEach(ticker -> {
            TickerEntity tickerEntity = new TickerEntity(ticker, "Huobi");
            tickerRepository.save(tickerEntity);
        });

        System.out.println("Saved tickers to H2 database");

    }
}
