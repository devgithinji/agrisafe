package com.agrisafe.authservice.service.impl;

import com.agrisafe.authservice.repository.FarmerRepository;
import com.agrisafe.common.exception.APIException;
import com.agrisafe.common.exception.ResourceNotFoundException;
import com.agrisafe.common.model.Farmer;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JWTService {
    @Value("${jwt.secret-key}")
    private String JWT_SECRET;
    @Value("${jwt.secret-issuer}")
    private String JWT_ISSUER;
    @Value("${jwt.expiration-in-ms}")
    private int JWT_EXPIRATION_IN_MS;

    private final FarmerRepository farmerRepository;

    public String generateToken(String subject, String userType) {
        return Jwts.builder()
                .setSubject(subject)
                .addClaims(Map.of("user_type", userType))
                .setIssuer(JWT_ISSUER)
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_IN_MS))
                .compact();
    }

    public Date getExpirationDate(String token) {
        return getJWTClaims(token).getBody().getExpiration();
    }

    public Long getFarmer(String token) {
        try {
            Jws<Claims> claimsJws = getJWTClaims(token);
            Date expirationTime = claimsJws.getBody().getExpiration();
            if (Instant.now().isAfter(expirationTime.toInstant())) throw new APIException("Token is expired");
            String email = claimsJws.getBody().getSubject();
            return farmerRepository.findFarmerByEmail(email)
                    .map(Farmer::getId)
                    .orElseThrow(() -> new ResourceNotFoundException("farmer", "email", email));

        } catch (Exception e) {
            String message = "invalid token";
            if (e instanceof APIException || e instanceof ResourceNotFoundException) {
                message = e.getMessage();
            }
            throw new APIException(message);
        }

    }


    public Map<String, String> getUserDetailsFromToken(String token) {
        return Map.of(
                "username", getJWTClaims(token).getBody().getSubject(),
                "user_type", getJWTClaims(token).getBody().get("user_type").toString()
        );
    }

    private Jws<Claims> getJWTClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token);
    }

    private Key getKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(JWT_SECRET));
    }
}
