package com.temzu.market.routinglib.dtos;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartDto {

  private List<CartItemDto> items;
  private int totalPrice;
}
