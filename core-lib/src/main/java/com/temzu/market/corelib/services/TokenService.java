package com.temzu.market.corelib.services;

import com.temzu.market.corelib.models.UserInfo;

public interface TokenService {

  String generateTokenWithExpirationTime(UserInfo user);

  void expireToken(String token);

  UserInfo parseToken(String token);

  Long getUserId(String token);
}