package com.temzu.market.corelib.exceptions;

public class ResourceNotFoundException extends RuntimeException {

  private static final String ID = "id";
  private static final String LOGIN = "login";
  private static final String NAME = "name";
  private static final String TITLE = "TITLE";

  private static final String NOT_FOUND_BY = "%s with %s %s not found";

  private ResourceNotFoundException(String message) {
    super(message);
  }

  public static ResourceNotFoundException byId(Long id, Class<?> entityClass) {
    return new ResourceNotFoundException(
        String.format(NOT_FOUND_BY, entityClass.getSimpleName(), ID, id));
  }

  public static ResourceNotFoundException byName(String name, Class<?> entityClass) {
    return new ResourceNotFoundException(
        String.format(NOT_FOUND_BY, entityClass.getSimpleName(), NAME, name));
  }

  public static ResourceNotFoundException byTitle(String title, Class<?> entityClass) {
    return new ResourceNotFoundException(
        String.format(NOT_FOUND_BY, entityClass.getSimpleName(), TITLE, title));
  }

  public static ResourceNotFoundException byLogin(String login, Class<?> entityClass) {
    return new ResourceNotFoundException(
        String.format(NOT_FOUND_BY, entityClass.getSimpleName(), LOGIN, login));
  }
}
