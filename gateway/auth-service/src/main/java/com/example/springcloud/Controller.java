package com.example.springcloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.example.springcloud.AuthResponseCode.SUCCESS;
import static com.example.springcloud.AuthResponseCode.USER_NOT_FOUND;

@RestController
@Slf4j
public class Controller implements AuthService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    @PostMapping("/login")
    @ResponseBody
    public AuthResponse login(@RequestParam String username,
                              @RequestParam String password) {

        // TODO 验证username + password

        Account account = Account.builder()
                .username(username)
                .build();

        String token = jwtService.token(account);
        account.setToken(token);
        account.setRefreshToken(UUID.randomUUID().toString());

        redisTemplate.opsForValue().set(account.getRefreshToken(), account);

        return AuthResponse.builder()
                .account(account)
                .code(SUCCESS)
                .build();
    }

    @Override
    @PostMapping("/refresh")
    @ResponseBody
    public AuthResponse refresh(@RequestParam String refreshToken) {
        Account account = (Account) redisTemplate.opsForValue().get(refreshToken);
        if (account == null) {
            return AuthResponse.builder()
                    .code(USER_NOT_FOUND)
                    .build();
        }

        String jwt = jwtService.token(account);
        account.setToken(jwt);
        account.setRefreshToken(UUID.randomUUID().toString());

        redisTemplate.delete(refreshToken);
        redisTemplate.opsForValue().set(account.getRefreshToken(), account);

        return AuthResponse.builder()
                .account(account)
                .code(SUCCESS)
                .build();
    }

    @Override
    @GetMapping("/verify")
    public AuthResponse verify(@RequestParam String token,
                               @RequestParam String username) {
        boolean success = jwtService.verify(token, username);
        return AuthResponse.builder()
                // TODO 此处最好用invalid token之类的错误信息
                .code(success ? SUCCESS : USER_NOT_FOUND)
                .build();
    }

}
