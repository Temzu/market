package com.temzu.market.msorder.services.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.temzu.market.corelib.services.TokenService;
import com.temzu.market.msorder.services.OrderService;
import com.temzu.market.routinglib.dtos.OrderDto;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;

@SpringBootTest
class OrderServiceImplTest {

  @Autowired
  private OrderService orderService;

  @MockBean
  private TokenService tokenService;

  @CsvSource({
      "Token_1,  1",
      "Token_2,  2",
      "Token_3', 3",
      "Token_4,  4"
  })
  @ParameterizedTest
  void findPageByCurrentUserToken(String token, Long userId) {
    Mockito.when(tokenService.getUserId(token)).thenReturn(userId);

    Page<OrderDto> page = orderService.findPageByCurrentUserToken(token, 1, 3);
    assertTrue(page.getTotalElements() > 0);
    assertTrue(page.getTotalPages() > 0);
    assertTrue(page.getNumberOfElements() > 0);
  }
}