package com.temzu.market.corelib.exceptions;

import java.util.List;

public class InvalidInputDataException extends RuntimeException {

  private final List<String> messages;

  public List<String> getMessages() {
    return messages;
  }

  public InvalidInputDataException(List<String> messages) {
    super(String.join(", ", messages));
    this.messages = messages;
  }

  public InvalidInputDataException(String message) {
    this(List.of(message));
  }
}
