package com.temzu.market.msorder.mappers;

import com.temzu.market.msorder.dao.entities.OrderItem;
import com.temzu.market.routinglib.dtos.OrderItemDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderItemMapper {

  private final ModelMapper mapper;

  public OrderItem toOrderItem(OrderItemDto orderItemDto) {
    OrderItem orderItem = mapper.map(orderItemDto, OrderItem.class);
    orderItem.setId(null);
    return orderItem;
  }
}
