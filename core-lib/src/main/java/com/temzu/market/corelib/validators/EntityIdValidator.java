package com.temzu.market.corelib.validators;

import com.temzu.market.corelib.exceptions.EntityBadIdException;

public class EntityIdValidator {

  public static void mustBeGreaterThanZero(Class<?> entityClass, Long id) {
    if (id <= 0) {
      throw EntityBadIdException.mustBeGreaterThanZero(entityClass, id);
    }
  }

  public static void mustBeNull(Class<?> entityClass, Long id) {
    if (id != null) {
      throw EntityBadIdException.mustBeNull(entityClass, id);
    }
  }
}
