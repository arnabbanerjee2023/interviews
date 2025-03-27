package com.arnab.spring.spring_security_demo.service;

import com.arnab.spring.spring_security_demo.domains.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    private Key key;

    public JwtService() throws NoSuchAlgorithmException {
        SecretKey hmacSHA256 = KeyGenerator.getInstance("HmacSHA256").generateKey();
        String encodedKey = Base64.getEncoder().encodeToString(hmacSHA256.getEncoded());
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(encodedKey));
    }

    public String generateToken(User user) throws NoSuchAlgorithmException {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (10 * 60 * 1000)))
                .and()
                .signWith(key)
                .compact();
    }

    public String extractUsername(String jwtToken) throws NoSuchAlgorithmException {
        return getClaims(jwtToken)
                .getSubject();
    }

    private Claims getClaims(String jwtToken) throws NoSuchAlgorithmException {
        return Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    public boolean validateToken(String jwtToken, UserDetailsService userDetailsService) throws NoSuchAlgorithmException {
        String username = this.extractUsername(jwtToken);
        return username.equals(userDetailsService.loadUserByUsername(username).getUsername())
                && !isTokenExpired(jwtToken);
    }

    private boolean isTokenExpired(String jwtToken) throws NoSuchAlgorithmException {
        return getClaims(jwtToken).getExpiration().before(new Date());
    }
}
