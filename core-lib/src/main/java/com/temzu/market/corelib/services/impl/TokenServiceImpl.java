package com.temzu.market.corelib.services.impl;

import com.temzu.market.corelib.models.UserInfo;
import com.temzu.market.corelib.services.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.Instant;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:secret.properties")
public class TokenServiceImpl implements TokenService {

  @Value("${jwt.secret}")
  private String JWT_SECRET;

  @Value("${jwt.lifetime}")
  private Integer JWT_TTL;

  private static final String USERID_CLAIM = "id";
  private static final String LOGIN_CLAIM = "login";
  private static final String ROLE_CLAIM = "role";

  @Override
  public String generateToken(UserInfo user) {
    Instant expirationTime = Instant.now().plusSeconds(JWT_TTL);
    Date expirationDate = Date.from(expirationTime);
    return "Bearer " + Jwts.builder()
        .claim(USERID_CLAIM, user.getUserId())
        .claim(LOGIN_CLAIM, user.getUserEmail())
        .claim(ROLE_CLAIM, user.getRole())
        .setExpiration(expirationDate)
        .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
        .compact();
  }

  @Override
  public UserInfo parseToken(String token) {
    Jws<Claims> jwsClaims = Jwts.parser()
        .setSigningKey(JWT_SECRET)
        .parseClaimsJws(token);

    String email = jwsClaims.getBody()
        .getSubject();

    Long userId = jwsClaims.getBody()
        .get(USERID_CLAIM, Long.class);

    String role = jwsClaims.getBody()
        .get(ROLE_CLAIM, String.class);

    return UserInfo.builder()
        .userId(userId)
        .userEmail(email)
        .role(role)
        .build();
  }

  @Override
  public Long getUserId(String token) {
    return Jwts.parser()
        .setSigningKey(JWT_SECRET)
        .parseClaimsJws(token)
        .getBody().get(USERID_CLAIM, Long.class);
  }
}
