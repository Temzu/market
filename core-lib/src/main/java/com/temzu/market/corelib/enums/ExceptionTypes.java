package com.temzu.market.corelib.enums;

public enum ExceptionTypes {
  ID("ID"),
  LOGIN("LOGIN"),
  NAME("NAME"),
  TITLE("TITLE"),
  UUID("UUID");

  private final String type;

  ExceptionTypes(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }
}
