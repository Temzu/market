package com.temzu.market.routinglib.dtos;

import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItemDto {

  private Long productId;

  private String productTitle;

  private BigDecimal pricePerProduct;

  private BigDecimal price;

  private int quantity;

  public OrderItemDto(ProductDto productDto) {
    this.productId = productDto.getId();
    this.quantity = 1;
    this.pricePerProduct = productDto.getPrice();
    this.price = productDto.getPrice();
    this.productTitle = productDto.getTitle();
  }

  public void changeQuantity(int amount) {
    quantity += amount;
    price = pricePerProduct.multiply(BigDecimal.valueOf(quantity));
  }
}
