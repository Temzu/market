package com.temzu.market.corelib.services.impl;

import com.temzu.market.corelib.models.UserInfo;
import com.temzu.market.corelib.services.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

  @Override
  public String generateToken(UserInfo user) {
    return null;
  }

  @Override
  public UserInfo parseToken(String token) {
    return null;
  }

  @Override
  public Long getUserId(String token) {
    return null;
  }
}
