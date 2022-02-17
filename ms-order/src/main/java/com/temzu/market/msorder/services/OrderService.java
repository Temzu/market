package com.temzu.market.msorder.services;

import com.temzu.market.routinglib.dtos.CreateOrderDto;
import com.temzu.market.routinglib.dtos.OrderDto;
import java.util.List;
import org.springframework.data.domain.Page;

public interface OrderService {

  Page<OrderDto> findPageByUserId(String token, int page, int pageSize);

  OrderDto createFromCart(String token, CreateOrderDto createOrderDto);
}
