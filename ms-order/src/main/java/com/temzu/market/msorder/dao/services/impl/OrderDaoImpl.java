package com.temzu.market.msorder.dao.services.impl;

import com.temzu.market.msorder.dao.entities.Order;
import com.temzu.market.msorder.dao.repositoriies.OrderRepository;
import com.temzu.market.msorder.dao.services.OrderDao;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderDaoImpl implements OrderDao {

  private final OrderRepository orderRepository;

  @Override
  public Page<Order> findPageByUserId(Long userId, int page, int pageSize) {
    return orderRepository.findAllByUserId(userId, PageRequest.of(page, pageSize));
  }
}
