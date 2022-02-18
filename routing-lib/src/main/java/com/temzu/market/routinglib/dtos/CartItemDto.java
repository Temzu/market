package com.temzu.market.routinglib.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartItemDto {

  private Long productId;
  private String productTitle;
  private int quantity;
  private int pricePerProduct;
  private int price;
}
