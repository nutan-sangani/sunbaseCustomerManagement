package com.example.sunbaseTest.customerManagement.Utils;

import com.example.sunbaseTest.customerManagement.Model.AccessToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${JWT_SECRET_KEY}")
    private String secretKey;

    @Value("${JWT_EXPIRATION_TIME}")
    private long expiration;

    private Key getSignKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    private String buildToken(Map<String,Object> extraClaims, UserDetails userDetails)
    {
        return Jwts.builder()
                .addClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public AccessToken generateToken(UserDetails userDetails)
    {
        String token = buildToken(new HashMap<String,Object>(),userDetails);
        AccessToken accessToken = new AccessToken();
        accessToken.setAccess_token(token);
        return accessToken;
    }

    private <T> T extractClaim(String token, Function<Claims,T> claimResolver)
    {
        Claims allClaims = extractAllClaims(token);
        return claimResolver.apply(allClaims);
    }

    private Claims extractAllClaims(String token)
    {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isExpired(String token) {
        Date expirationDate = extractClaim(token,Claims::getExpiration);
        return expirationDate.before(new Date());
    }

    public boolean isTokenValid(String token,String userName){
        String extractedUserName = extractClaim(token,Claims::getSubject);
        return (extractedUserName.equals(userName) && !isExpired(token));
    }

    public String extractUserName(String token)
    {
        return extractClaim(token,Claims::getSubject);
    }
}
