package com.temzu.market.msorder.services.impl;

import com.temzu.market.corelib.services.RedisService;
import com.temzu.market.msorder.dao.entities.Order;
import com.temzu.market.msorder.dao.entities.OrderItem;
import com.temzu.market.msorder.dao.services.OrderDao;
import com.temzu.market.msorder.mappers.OrderItemMapper;
import com.temzu.market.msorder.mappers.OrderMapper;
import com.temzu.market.msorder.services.CartService;
import com.temzu.market.msorder.services.OrderService;
import com.temzu.market.msorder.util.Cart;
import com.temzu.market.routinglib.dtos.OrderCreateDto;
import com.temzu.market.routinglib.dtos.OrderDto;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderDao orderDao;

  private final CartService cartService;

  private final RedisService<Cart> redisService;

  private final OrderItemMapper orderItemMapper;

  private final OrderMapper orderMapper;

  @Override
  public Page<OrderDto> findPageByUserId(Long id, int page, int pageSize) {
    return orderDao.findPageByUserId(id, page, pageSize).map(orderMapper::toOrderDto);
  }

  @Transactional
  @Override
  public void createOrder(Long userId, OrderCreateDto orderCreateDto, String uuid) {
    Cart cart = cartService.getCurrentCart(
        cartService.getCartUuidFromSuffix(String.valueOf(userId)));
    Order order =
        Order.builder()
            .phone(orderCreateDto.getPhone())
            .address(orderCreateDto.getAddress())
            .userId(userId)
            .price(cart.getPrice())
            .build();

    order.setItems(
        cart.getItems().stream()
            .map(
                oid -> {
                  OrderItem orderItem = orderItemMapper.toOrderItem(oid);
                  orderItem.setOrder(order);
                  return orderItem;
                })
            .collect(Collectors.toList()));
    cart.clear();
    orderDao.save(order);
    redisService.set(cartService.getCartUuidFromSuffix(String.valueOf(userId)), cart);
  }
}
