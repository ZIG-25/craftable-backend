package org.zig.craftablebackend.shared;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TokenUtils {

    @Value("${token.signing.key}")
    private String jwtSigningKey;

    public String getEmailFromToken(String token) {
        String email;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            email = claims.getSubject();
        } catch (Exception e) {
            email = null;
        }
        return email;
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSigningKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
