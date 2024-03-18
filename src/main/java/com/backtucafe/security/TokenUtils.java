package com.backtucafe.security;

import com.backtucafe.model.Business;
import com.backtucafe.model.Client;
import com.backtucafe.security.detailsimpl.ClientDetailsImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;

@Data
@Component
public class TokenUtils {

    private final static  String ACCESS_TOKEN_SECRET = "4qhq8LrEBfTcaRHxhdb9zURb2rf8e7Ud";
    private final static  Long ACCESS_TOKEN_VALIDITY_SECONDS = 2_592_000L;

    public String getTokenClient(Client client) {
        return createToken(client.getName(), client.getEmail());
    }

    public String getTokenBusiness(Business business) {
        return createToken(business.getName(), business.getEmail());
    }

    public static String createToken(String nombre, String email){
        long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1_000;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        Map<String, Object> extra = new HashMap<>();
        extra.put("nombre", nombre);

        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expirationDate)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String email = claims.getSubject();

            return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());

        }catch (JwtException e){
            return null;
        }
    }




}
