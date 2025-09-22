package ru.aidar.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service
public class JWTService {
    public static final String SECRET = "44f696cae9b03360519b2bfa7f98930409714e81e2bd702104c01d39175d0ed1";

    private SecretKey getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Jws<Claims> validateToken(String token) {
        return Jwts.parser().verifyWith(getSignKey()).build().parseSignedClaims(token);
    }
}
