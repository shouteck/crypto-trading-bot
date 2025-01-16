package dev.shouteck.crypto_trading_bot;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> trade(
            @RequestParam String username,
            @RequestParam String symbol,
            @RequestParam BigDecimal quantity,
            @RequestParam boolean isBuyOrder) {
        try {
            tradeService.trade(username, symbol, quantity, isBuyOrder);
            return ResponseEntity.ok("Trade executed successfully.");
        } catch (InsufficientBalanceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient cash balance: " + e.getMessage());
        } catch (InsufficientQuantityException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient quantity for the symbol: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the trade: " + e.getMessage());
        }
    }
}
