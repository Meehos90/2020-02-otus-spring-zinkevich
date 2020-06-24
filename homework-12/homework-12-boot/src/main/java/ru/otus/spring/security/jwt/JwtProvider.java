package ru.otus.spring.security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import ru.otus.spring.security.services.UserPrinciple;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Slf4j
@Configuration
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtProvider {

    private String jwtSecret;

    public String generateJwtToken(Authentication authentication) {
        UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateJwtToken(HttpServletRequest request, String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (ExpiredJwtException e) {
            log.error("{} [{}] JWT Token has expired", request.getMethod(), request.getRequestURI(), e);
        } catch (UnsupportedJwtException e) {
            log.error("{}[{}] Unsupported JWT Token", request.getMethod(), request.getRequestURI(), e);
        } catch (IllegalArgumentException e) {
            log.error("{} [{}] Unable to get JWT Token", request.getMethod(), request.getRequestURI(), e);
        }
        return false;
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

}
