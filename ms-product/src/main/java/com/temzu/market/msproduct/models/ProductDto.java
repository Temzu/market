package com.temzu.market.msproduct.models;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ProductDto {

  private Long id;
  private String title;
  private BigDecimal price;
}
