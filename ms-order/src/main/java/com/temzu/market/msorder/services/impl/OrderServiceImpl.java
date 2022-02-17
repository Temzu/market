package com.temzu.market.msorder.services.impl;

import com.temzu.market.corelib.services.TokenService;
import com.temzu.market.msorder.dao.services.OrderDao;
import com.temzu.market.msorder.mappers.OrderMapper;
import com.temzu.market.msorder.services.OrderService;
import com.temzu.market.routinglib.dtos.CreateOrderDto;
import com.temzu.market.routinglib.dtos.OrderDto;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final TokenService tokenService;
  private final OrderMapper orderMapper;
  private final OrderDao orderDao;

  @Override
  @Transactional
  public Page<OrderDto> findPageByCurrentUserToken(String token, int page, int pageSize) {
    Long userId = tokenService.getUserId(token);
    return orderDao
        .findPageByUserId(userId, page, pageSize)
        .map(orderMapper::toOrderDto);
  }

  @Override
  @Transactional
  public OrderDto createFromCart(String token, CreateOrderDto createOrderDto) {
    return null;
  }

}
