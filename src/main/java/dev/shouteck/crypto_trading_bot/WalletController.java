package dev.shouteck.crypto_trading_bot;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/balance")
    public ResponseEntity<WalletBalanceDto> getWalletBalance(@RequestParam String username) {
        try {
            Wallet wallet = walletService.getWalletForUser(username);

            List<WalletItemDto> walletItems = wallet.getWalletItems().stream()
                    .map(item -> new WalletItemDto(item.getSymbol(), item.getQuantity()))
                    .collect(Collectors.toList());

            WalletBalanceDto walletBalanceDto = new WalletBalanceDto(wallet.getCashBalance(), walletItems);

            return ResponseEntity.ok(walletBalanceDto);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @PostMapping("/adjust")
    public ResponseEntity<String>adjustWalletBalance(@RequestBody Map<String, String> request) {
        try {
            String username = request.get("username");
            BigDecimal amount = new BigDecimal(request.get("amount"));

            // Ensure the amount is valid (e.g., not negative)
            if (amount.compareTo(BigDecimal.ZERO) < 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Amount cannot be negative.");
            }

            walletService.adjustCashBalance(username, amount);
            return ResponseEntity.ok("Wallet balance adjusted successfully.");

        } catch (NumberFormatException e) {
            // Handle invalid number format for 'amount'
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid amount format.");
        } catch (UserNotFoundException e) {
            // Handle case where the user is not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        } catch (Exception e) {
            // Handle any other unforeseen exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while adjusting the wallet balance.");
        }
    }
}
