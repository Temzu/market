package com.temzu.market.msorder.dao.services;

import com.temzu.market.msorder.dao.entities.Order;
import org.springframework.data.domain.Page;

public interface OrderDao {

  Page<Order> findPageByUserId(Long userId, int page, int pageSize);

  Order save(Order order);
}
