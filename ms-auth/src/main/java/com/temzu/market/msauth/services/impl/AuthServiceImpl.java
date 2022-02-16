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
  public void signUp(SignUpRequestDto signUpRequestDto) {
    userDao.save(userMapper.toUser(signUpRequestDto));
  }

  @Override
  public AuthResponseDto login(AuthRequestDto request) {
    User user = userDao.findByLoginAndPassword(request.getLogin(), request.getPassword());

    UserInfo userInfo = UserInfo.builder()
        .userId(user.getId())
        .userEmail(user.getLogin())
        .role(user.getRole().getName())
        .build();
    String token = tokenService.generateToken(userInfo);
    return new AuthResponseDto(token);
  }
}
