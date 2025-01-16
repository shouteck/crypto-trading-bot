package dev.shouteck.crypto_trading_bot;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
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
    public String adjustWalletBalance(@RequestParam String username, @RequestParam BigDecimal amount) {
        walletService.adjustCashBalance(username, amount);
        return "Wallet balance adjusted";
    }
}
