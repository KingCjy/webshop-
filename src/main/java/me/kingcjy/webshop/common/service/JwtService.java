package me.kingcjy.webshop.common.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import me.kingcjy.webshop.common.exception.InvalidTokenException;
import me.kingcjy.webshop.common.security.SecurityUser;
import me.kingcjy.webshop.user.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author KingCjy
 */
@Service
public class JwtService {

    @Value("webshop.jwt.auth-key")
    private String authKey;

    public String create(User user) {
        LocalDateTime localDateTime = LocalDateTime.now().plusDays(1);
        Date expiredDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        String jwt = Jwts.builder()
                .claim("user_id", user.getId())
                .claim("email", user.getEmail())
                .claim("username", user.getUsername())
                .signWith(SignatureAlgorithm.HS512, authKey)
                .setExpiration(expiredDate)
                .compact();
        return jwt;
    }

    public boolean isValidToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(authKey)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public SecurityUser getSecurityUser(String token) {
        if(!isValidToken(token)) {
            throw new InvalidTokenException("잘못된 토큰입니다.");
        }

        Claims claims = Jwts.parser()
                .setSigningKey(authKey)
                .parseClaimsJws(token)
                .getBody();

        SecurityUser securityUser = new SecurityUser(
                claims.get("user_id", Long.class),
                claims.get("email", String.class),
                claims.get("uuid", String.class));

        return securityUser;
    }

    public <T> T getValue(String tokenString, String key, Class<T> targetClass) {
        Jws<Claims> claims = Jwts.parser()
                .setSigningKey(authKey)
                .parseClaimsJws(tokenString);

        return claims.getBody().get(key, targetClass);
    }
}
