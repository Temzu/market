package com.temzu.market.corelib.exceptions;

public class EntityBadIdException extends RuntimeException {

  private static final String MUST_BE_GREATER_THAN_ZERO = "%s: id %d must be > 0";
  private static final String MUST_BE_NULL = "%s: id %d must be null";

  private EntityBadIdException(String message) {
    super(message);
  }

  public static EntityBadIdException mustBeNull(Class<?> entityClass, Long id) {
    return new EntityBadIdException(String.format(MUST_BE_NULL, entityClass.getSimpleName(), id));
  }

  public static EntityBadIdException mustBeGreaterThanZero(Class<?> entityClass, Long id) {
    return new EntityBadIdException(String.format(MUST_BE_GREATER_THAN_ZERO, entityClass.getSimpleName(), id));
  }
}
