package com.temzu.market.msorder.dao.services.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.temzu.market.msorder.dao.entities.Cart;
import com.temzu.market.msorder.dao.services.CartDao;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;
import org.hibernate.annotations.Source;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class CartDaoImplTest {

  @Autowired
  private CartDaoImpl cartDao;

  private static final UUID[] cartUuids = new UUID[3];

  @BeforeEach
  void setUp() {
    IntStream
        .range(1, 4)
        .forEach((i) -> {
          Cart cart = new Cart();
          cart.setUserId(i);
          cartDao.save(cart);
          cartUuids[i - 1] = cart.getId();
        });
  }

  @Order(1)
  @CsvSource(value = {"0", "1", "2"})
  @ParameterizedTest
  void findCartByUuid(int index) {
    assertDoesNotThrow(() -> {
      cartDao.findCartByUuid(cartUuids[index]);
    });
  }

  @Test
  void save() {
  }

  @Test
  void delete() {
  }

  @Test
  void findByUserId() {
  }
}