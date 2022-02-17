package com.temzu.market.msorder.dao.services.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.temzu.market.msorder.dao.entities.Order;
import com.temzu.market.msorder.dao.services.OrderDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

@SpringBootTest
class OrderDaoImplTest {

  @Autowired
  private OrderDao orderDao;

  @CsvSource(value = {"1", "2", "3", "4"})
  @ParameterizedTest
  void findPageByUserId(Long userId) {
    Page<Order> pageByUserId = orderDao.findPageByUserId(userId, 1, 3);
    assertTrue(pageByUserId.getTotalElements() > 0);
    assertTrue(pageByUserId.getTotalPages() > 0);
    assertTrue(pageByUserId.getNumberOfElements() > 0);
  }
}