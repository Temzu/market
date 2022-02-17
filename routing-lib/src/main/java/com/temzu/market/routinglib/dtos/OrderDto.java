package com.temzu.market.routinglib.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDto {

  private Long id;
  private BigDecimal price;
  private LocalDateTime createdAt;
  private List<OrderItemDto> items;
}
