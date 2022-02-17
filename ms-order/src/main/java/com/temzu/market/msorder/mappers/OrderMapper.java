package com.temzu.market.msorder.mappers;

import com.temzu.market.msorder.dao.entities.Order;
import com.temzu.market.routinglib.dtos.OrderDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderMapper {

  private final ModelMapper mapper;

  public OrderDto toOrderDto(Order order) {
    return mapper.map(order, OrderDto.class);
  }

  public Order toOrder(OrderDto orderDto) {
    return mapper.map(orderDto, Order.class);
  }
}
