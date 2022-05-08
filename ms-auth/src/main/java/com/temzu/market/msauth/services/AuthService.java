package com.temzu.market.msauth.services;

import com.temzu.market.routinglib.dtos.AuthRequestDto;
import com.temzu.market.routinglib.dtos.AuthResponseDto;
import com.temzu.market.routinglib.dtos.SignUpRequestDto;

public interface AuthService {

  AuthResponseDto signUp(SignUpRequestDto signUpRequestDto);

  AuthResponseDto login(AuthRequestDto authRequestDto);

  void logout(String token);
}
