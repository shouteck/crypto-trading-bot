package dev.shouteck.crypto_trading_bot;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/trade")
public class TradeController {

    private final TradeService tradeService;

    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @PostMapping
    public String trade(
            @RequestParam String username,
            @RequestParam String symbol,
            @RequestParam BigDecimal quantity,
            @RequestParam boolean isBuyOrder) {
        return tradeService.trade(username, symbol, quantity, isBuyOrder);
    }
}
