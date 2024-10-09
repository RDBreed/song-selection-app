package eu.phaf.songselector.admin.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JwtTokenUtil {

  private final String secret;

  public JwtTokenUtil(String secret) {
    this.secret = secret;
  }

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parser().verifyWith(getSecretKey(secret)).build().parseSignedClaims(token).getPayload();
  }

  private SecretKey getSecretKey(String base64EncodedSecretKey) {
    byte[] bytes = Decoders.BASE64.decode(base64EncodedSecretKey);
    return Keys.hmacShaKeyFor(bytes);
  }

  private Boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  public String generateToken(String username) {
    Map<String, Object> claims = new HashMap<>();
    return createToken(claims, username);
  }

  private String createToken(Map<String, Object> claims, String subject) {
    return Jwts.builder()
      .claims(claims)
      .subject(subject)
      .issuedAt(new Date(System.currentTimeMillis()))
      .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
      .signWith(getSecretKey(secret))
      .compact();
  }

  public Boolean validateToken(String token, String username) {
    final String usernameFromToken = extractUsername(token);
    return (usernameFromToken.equals(username) && !isTokenExpired(token));
  }
}