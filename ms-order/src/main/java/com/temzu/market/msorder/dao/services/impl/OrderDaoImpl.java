package com.temzu.market.msorder.dao.services.impl;

import com.temzu.market.msorder.dao.entities.Order;
import com.temzu.market.msorder.dao.repositories.OrderRepository;
import com.temzu.market.msorder.dao.services.OrderDao;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderDaoImpl implements OrderDao {

  private final OrderRepository orderRepository;

  @Override
  public Page<Order> findPageByUserId(Long id, int page, int pageSize) {
    return orderRepository.findAllByUserId(id, PageRequest.of(page - 1, pageSize));
  }

  @Override
  public Order save(Order order) {
    return orderRepository.save(order);
  }
}
