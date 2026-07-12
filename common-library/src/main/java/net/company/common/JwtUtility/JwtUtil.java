package net.company.common.JwtUtility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    @Value("${jwt.secret.key}")
    private String SECRET_KEY;


    public String generateToken(Long userId, String email, String role) {

        Map<String, Object> claims = new HashMap<>();

        claims.put("userId", userId);
        claims.put("role", role);

        return createToken(claims, email);
    }


    private String createToken(Map<String,Object> claims, String email){

        SecretKey key = Keys.hmacShaKeyFor(
                SECRET_KEY.getBytes(StandardCharsets.UTF_8)
        );

        return Jwts.builder()
                .claims(claims)
                .subject(email)
                .issuedAt(new Date())
                .expiration(
                        new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)
                )
                .signWith(key)
                .compact();
    }


    private Claims extractAllClaims(String token) {

        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


    public <T> T extractClaim(
            String token,
            Function<Claims,T> resolver
    ){

        Claims claims = extractAllClaims(token);

        return resolver.apply(claims);
    }


    public String extractEmail(String token){

        return extractClaim(
                token,
                Claims::getSubject
        );
    }


    public Long extractUserId(String token){

        return extractClaim(
                token,
                claims -> claims.get("userId", Long.class)
        );
    }


    public String extractRole(String token){

        return extractClaim(
                token,
                claims -> claims.get("role", String.class)
        );
    }


    public Date extractExpiration(String token){

        return extractClaim(
                token,
                Claims::getExpiration
        );
    }


    public boolean isTokenExpired(String token){

        return extractExpiration(token)
                .before(new Date());
    }


    public boolean validateToken(String token){

        return !isTokenExpired(token);
    }
}
