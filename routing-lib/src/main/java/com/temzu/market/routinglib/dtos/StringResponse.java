package com.temzu.market.routinglib.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StringResponse {
  private String value;

  public StringResponse(String value) {
    this.value = value;
  }
}
