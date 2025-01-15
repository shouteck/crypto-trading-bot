package dev.shouteck.crypto_trading_bot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CryptoTradingBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptoTradingBotApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(TickerAggregatorService tickerAggregatorService) {
		return args -> {
			System.out.println("Testing");
			List<Ticker> tickers = tickerAggregatorService.fetchCombinedTickers();
			System.out.println(tickers.get(0));
		};
	}

}
