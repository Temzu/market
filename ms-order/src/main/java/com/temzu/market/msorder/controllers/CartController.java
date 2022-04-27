package com.temzu.market.msorder.controllers;

import com.temzu.market.msorder.services.CartService;
import com.temzu.market.msorder.util.Cart;
import com.temzu.market.routinglib.dtos.StringResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@Slf4j
public class CartController {

  private final CartService cartService;

  @GetMapping("/{uuid}")
  public Cart getCart(@PathVariable String uuid) {
    return cartService.getCurrentCart(uuid);
  }

  @GetMapping("/generate")
  public StringResponse getCart() {
    return new StringResponse(cartService.generateCartUuid());
  }

  @GetMapping("/{uuid}/add/{productId}")
  public void add(@PathVariable String uuid, @PathVariable Long productId) {
    log.info("Product added in " + uuid);
    cartService.addToCart(uuid, productId);
  }

  @GetMapping("/{uuid}/decrement/{productId}")
  public void decrement(@PathVariable String uuid, @PathVariable Long productId) {
    cartService.decrementQuantity(uuid, productId);
  }

  @GetMapping("/remove/{productId}")
  public void remove(@PathVariable Long productId) {

  }

  @GetMapping("/{uuid}/clear")
  public void clear(@PathVariable String uuid) {
    cartService.clearCart(uuid);
  }
}
