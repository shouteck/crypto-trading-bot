package dev.shouteck.crypto_trading_bot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CryptoTradingBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptoTradingBotApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(BinanceRestClient client) {
		return args -> {
			System.out.println("Testing");
		};
	}

}
