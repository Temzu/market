package com.temzu.market.msorder.controllers;

import com.temzu.market.msorder.services.OrderService;
import com.temzu.market.routinglib.dtos.CreateOrderDto;
import com.temzu.market.routinglib.dtos.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  @GetMapping("/current")
  public Page<OrderDto> findPageByCurrentUser(
      @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
      @RequestParam(name = "page", defaultValue = "1") Integer page,
      @RequestParam(name = "size", defaultValue = "10") Integer pageSize
  ) {
    if (page < 1 || pageSize < 1) {
      page = 1;
      pageSize = 10;
    }
    return orderService.findPageByCurrentUserToken(token, page, pageSize);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public OrderDto createOrderFromCart(
      @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
      @RequestBody CreateOrderDto createOrderDto
  ) {
    return orderService.createFromCart(token, createOrderDto);
  }
}
