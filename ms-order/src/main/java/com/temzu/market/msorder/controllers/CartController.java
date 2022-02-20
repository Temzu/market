package com.temzu.market.msorder.controllers;

import com.temzu.market.msorder.services.CartService;
import com.temzu.market.routinglib.dtos.CartDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class CartController {

  private final CartService cartService;

  @GetMapping("/find/{uuid}")
  public CartDto findCurrentCart(@PathVariable UUID uuid) {
    return cartService.findCartByUuid(uuid);
  }

  @PostMapping("/create")
  public UUID createNewCart(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token) {
    return cartService.createCartForUser(token);
  }

  @PostMapping("/add")
  public void addProductToCart(@RequestParam UUID uuid, @RequestParam(name = "product_id") Long productId) {
    cartService.addToCart(uuid, productId);
  }

  @PostMapping("/clear")
  public void clearCart(@RequestParam UUID uuid) {
    cartService.clearCart(uuid);
  }
}
