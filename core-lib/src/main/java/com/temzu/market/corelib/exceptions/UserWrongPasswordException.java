package com.temzu.market.corelib.exceptions;

public class UserWrongPasswordException extends RuntimeException {

  private static final String WRONG_PASSWORD = "Wrong password entered";

  public UserWrongPasswordException() {
    super(WRONG_PASSWORD);
  }
}
