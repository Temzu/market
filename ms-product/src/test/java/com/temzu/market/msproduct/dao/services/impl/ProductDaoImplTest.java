package com.temzu.market.msproduct.dao.services.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.temzu.market.corelib.exceptions.EntityBadIdException;
import com.temzu.market.corelib.exceptions.ResourceNotFoundException;
import com.temzu.market.msproduct.dao.services.ProductDao;
import com.temzu.market.msproduct.dao.entities.Product;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
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
    assertEquals(id, product.getId());
  }

  @Order(2)
  @CsvSource(value = {"0", "-1", "100000"})
  @ParameterizedTest
  void findById_WRONG(Long id) {
    if (id <= 0) {
      assertThrows(EntityBadIdException.class, () -> productDao.findById(id));
    } else {
      assertThrows(ResourceNotFoundException.class, () -> productDao.findById(id));
    }
  }

  @Order(3)
  @NullSource
  @ParameterizedTest
  void add_CORRECT(Long id) {
    Product product = new Product();
    product.setId(id);
    product.setTitle("Title");
    product.setPrice(BigDecimal.TEN);

    Product addedProduct = productDao.add(product);
    assertDoesNotThrow(() -> productDao.findById(addedProduct.getId()));
  }

  @Order(4)
  @CsvSource(value = {"1", "0", "-1", "100"})
  @ParameterizedTest
  void add_WRONG(Long id) {
    Product product = new Product();
    product.setId(id);
    product.setTitle("Title");
    product.setPrice(BigDecimal.TEN);

    assertThrows(EntityBadIdException.class, () -> productDao.add(product));
  }

  @Order(5)
  @CsvSource(value = {"0", "-1", "100000"})
  @ParameterizedTest
  void deleteById_WRONG(Long id) {
    if (id <= 0) {
      assertThrows(EntityBadIdException.class, () -> productDao.deleteById(id));
    } else {
      assertThrows(ResourceNotFoundException.class, () -> productDao.deleteById(id));
    }
  }

  @Order(6)
  @CsvSource(value = {"1", "4"})
  @ParameterizedTest
  void deleteById_CORRECT(Long id) {
    assertNotNull(productDao.findById(id));

    productDao.deleteById(id);

    assertThrows(ResourceNotFoundException.class, () -> productDao.findById(id));
  }

  @Order(7)
  @Test
  void findPage() {
    Specification<Product> spec = Specification.where(null);
    Page<Product> page = productDao.findPage(spec, 1, 5);
    assertEquals(5, page.getNumberOfElements());
  }
}