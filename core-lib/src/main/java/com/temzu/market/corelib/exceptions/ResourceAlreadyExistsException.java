package com.temzu.market.corelib.exceptions;

import com.temzu.market.corelib.enums.ExceptionTypes;

public class ResourceAlreadyExistsException extends RuntimeException {

  private static final String ALREADY_EXISTS_BY = "%s already exists with [%s]: %s";

  private ResourceAlreadyExistsException(Class<?> entityClass, ExceptionTypes type, String reason) {
    super(String.format(ALREADY_EXISTS_BY, entityClass.getSimpleName(), type.getType(), reason));
  }

  public static ResourceAlreadyExistsException byLogin(String login, Class<?> entityClass) {
    return new ResourceAlreadyExistsException(entityClass, ExceptionTypes.LOGIN, login);
  }
}
