package com.temzu.market.msorder.controllers;

import com.temzu.market.msorder.services.CartService;
import com.temzu.market.routinglib.dtos.CartDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

  private final CartService cartService;

  @GetMapping("/find/{uuid}")
  public CartDto findCurrentCart(@PathVariable UUID uuid) {
    return cartService.findCartByUuid(uuid);
  }
}
