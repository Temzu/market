package com.temzu.market.msorder.dao.services.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.temzu.market.msorder.dao.entities.Cart;
import com.temzu.market.msorder.dao.services.CartDao;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CartDaoImplTest {

  @Autowired
  private CartDao cartDao;

  private static final UUID[] cartUuids = new UUID[3];

  @BeforeAll
  void setUp() {
    IntStream
        .range(1, 4)
        .forEach((i) -> {
          Cart cart = new Cart();
          cart.setUserId(i);
          cartDao.save(cart);
          cartUuids[i - 1] = cart.getId();
          System.out.println(cart);
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

  @Order(1)
  @CsvSource(value = {"0", "1", "2"})
  @ParameterizedTest
  void save() {
  }

  @Order(3)
  @CsvSource(value = {"0", "1", "2"})
  @ParameterizedTest
  void delete(int index) {
    assertDoesNotThrow(() -> cartDao.delete(cartDao.findCartByUuid(cartUuids[index])));
  }

  @Order(1)
  @CsvSource(value = {"1", "2", "3"})
  @ParameterizedTest
  void findByUserId(Long userId) {
    Optional<Cart> byUserId = cartDao.findByUserId(userId);
    assertFalse(byUserId.isEmpty());
  }
}