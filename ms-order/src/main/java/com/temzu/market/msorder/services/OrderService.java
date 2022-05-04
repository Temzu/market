package com.temzu.market.msorder.services;

import com.temzu.market.routinglib.dtos.OrderCreateDto;
import com.temzu.market.routinglib.dtos.OrderDto;
import org.springframework.data.domain.Page;

public interface OrderService {

  Page<OrderDto> findPageByUserId(Long id, int page, int pageSize);

  void createOrder(Long userId, OrderCreateDto orderCreateDto, String uuid);

}
