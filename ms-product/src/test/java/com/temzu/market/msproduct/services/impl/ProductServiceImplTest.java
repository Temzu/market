package com.temzu.market.msproduct.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.temzu.market.msproduct.models.ProductDto;
import com.temzu.market.msproduct.services.ProductService;
import java.util.HashMap;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;

@SpringBootTest
class ProductServiceImplTest {

  @Autowired
  private ProductService productService;

  @Order(7)
  @Test
  void findPage() {
    MultiValueMap<String, String> params = new MultiValueMapAdapter<>(new HashMap<>());
    Page<ProductDto> page = productService.findPage(params, 1, 5);
    assertEquals(5, page.getNumberOfElements());
  }
}