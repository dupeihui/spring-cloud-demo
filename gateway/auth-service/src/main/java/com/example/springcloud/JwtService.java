package com.example.springcloud;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class JwtService {

    private static final String KEY = "changeIt";
    private static final String ISSUER = "du";

    private static final long TOKEN_EXP_TIME = 60000;
    private static final String USER_NAME = "username";

    /**
     * 生成token
     * @param acct
     * @return
     */
    public String token(Account acct){
        Date now = new Date();
        Algorithm algorithm = Algorithm.HMAC256(KEY);

        String token = JWT.create()
                .withIssuer(ISSUER)
                .withIssuedAt(now)
                .withExpiresAt(new Date(now.getTime() + TOKEN_EXP_TIME))
                .withClaim(USER_NAME, acct.getUsername())
                .sign(algorithm);

        log.info("jwt generated user={}", acct.getUsername());
        return token;
    }

    /**
     * 校验token
     * @param token
     * @param username
     * @return
     */
    public boolean verify(String token, String username){
        log.info("verifying jwt user={}", username);

        try{
            Algorithm algorithm = Algorithm.HMAC256(KEY);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .withClaim(USER_NAME, username)
                    .build();

            verifier.verify(token);
            return true;
        }catch (Exception e){
            log.error("auth failed", e);
            return false;
        }
    }
}
