package com.temzu.market.corelib.exceptions;

public class UserWrongLoginException extends RuntimeException {

  private static final String WRONG_LOGIN = "Wrong login entered: %s";

  public UserWrongLoginException(String login) {
    super(String.format(WRONG_LOGIN, login));
  }
}
