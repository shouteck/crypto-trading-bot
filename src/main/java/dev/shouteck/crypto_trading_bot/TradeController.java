package dev.shouteck.crypto_trading_bot;

import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/trade")
public class TradeController {

    private final TradeService tradeService;
    private final UserRepository userRepository;

    public TradeController(TradeService tradeService, UserRepository userRepository) {
        this.tradeService = tradeService;
        this.userRepository = userRepository;
    }

    @PostMapping("/execute")
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

    @GetMapping("/history")
    public ResponseEntity<List<TradeHistoryDto>> getTradeHistory(@RequestParam String username) {
        try {
            CryptoUser user = userRepository.findByUsername(username);
            List<TradeHistoryDto> tradeHistory = tradeService.getTradeHistoryForUser(user);

            return ResponseEntity.ok(tradeHistory);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
