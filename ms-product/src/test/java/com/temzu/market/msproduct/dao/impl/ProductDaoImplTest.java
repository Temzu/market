package com.temzu.market.msproduct.dao.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.temzu.market.corelib.exceptions.ResourceNotFoundException;
import com.temzu.market.msproduct.dao.ProductDao;
import com.temzu.market.msproduct.models.Product;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductDaoImplTest {

  @Autowired
  private ProductDao productDao;

  @BeforeEach
  void setUp() {
  }

  @Order(1)
  @CsvSource(value = {"1", "4"})
  @ParameterizedTest
  void findById_CORRECT(Long id) {
    Product product = productDao.findById(id);
    assertNotNull(product);
    assertEquals(id, product.getId());
    System.out.println(product);
  }

  @Order(2)
  @CsvSource(value = {"0", "-1"})
  @ParameterizedTest
  void findById_WRONG(Long id) {
    assertThrows(ResourceNotFoundException.class, () -> productDao.findById(id));
  }

  @Order(3)
  @Test
  void save() {
  }

  @Order(4)
  @Test
  void deleteById() {
  }
}