package dev.shouteck.crypto_trading_bot;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public CryptoUser createUser(@RequestParam String username, @RequestParam BigDecimal walletBalance) {
        return userService.createUser(username, walletBalance);
    }
}
