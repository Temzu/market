package com.temzu.market.routinglib.dtos;

import lombok.Data;
import lombok.NonNull;

@Data
public class AuthRequestDto {

  @NonNull
  private String login;
  @NonNull
  private String password;
}
