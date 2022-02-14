package com.temzu.market.corelib.exceptions;

public class ResourceNotFoundException extends RuntimeException {

  private static final String NOT_FOUND_BY = "%s not found by [%s]: %s";

  private ResourceNotFoundException(Class<?> entityClass, String type, String reason) {
    super(String.format(NOT_FOUND_BY, entityClass.getSimpleName(), type, reason));
  }

  public static ResourceNotFoundException byId(Long id, Class<?> entityClass) {
    return new ResourceNotFoundException(entityClass, Type.ID.type, id.toString());
  }

  public static ResourceNotFoundException byName(String name, Class<?> entityClass) {
    return new ResourceNotFoundException(entityClass, Type.NAME.type, name);
  }

  public static ResourceNotFoundException byTitle(String title, Class<?> entityClass) {
    return new ResourceNotFoundException(entityClass, Type.TITLE.type, title);
  }

  public static ResourceNotFoundException byLogin(String login, Class<?> entityClass) {
    return new ResourceNotFoundException(entityClass, Type.LOGIN.type, login);
  }

  private enum Type {
    ID("ID"),
    LOGIN("LOGIN"),
    NAME("NAME"),
    TITLE("TITLE");

    private final String type;

    Type(String type) {
      this.type = type;
    }
  }
}
