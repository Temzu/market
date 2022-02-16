package com.temzu.market.msauth.services.impl;

import com.temzu.market.corelib.models.UserInfo;
import com.temzu.market.corelib.services.TokenService;
import com.temzu.market.msauth.dao.entites.User;
import com.temzu.market.msauth.dao.services.UserDao;
import com.temzu.market.msauth.mappers.UserMapper;
import com.temzu.market.msauth.services.AuthService;
import com.temzu.market.routinglib.dtos.AuthRequestDto;
import com.temzu.market.routinglib.dtos.AuthResponseDto;
import com.temzu.market.routinglib.dtos.SignUpRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final UserDao userDao;
  private final TokenService tokenService;
  private final UserMapper userMapper;

  @Override
  public AuthResponseDto signUp(SignUpRequestDto signUpRequestDto) {
    User user = userDao.save(userMapper.toUser(signUpRequestDto));

    String token = returnToken(user);
    return new AuthResponseDto(token);
  }

  @Override
  public AuthResponseDto login(AuthRequestDto request) {
    User user = userDao.findByLoginAndPassword(request.getLogin(), request.getPassword());

    String token = returnToken(user);
    return new AuthResponseDto(token);
  }

  private String returnToken(User user) {
    UserInfo userInfo = UserInfo.builder()
        .userId(user.getId())
        .userEmail(user.getLogin())
        .role(user.getRole().getName())
        .build();
    return tokenService.generateToken(userInfo);
  }
}
