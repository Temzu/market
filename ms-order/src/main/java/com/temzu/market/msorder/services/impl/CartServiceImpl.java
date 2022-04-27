package com.temzu.market.msorder.services.impl;

import com.temzu.market.corelib.services.RedisService;
import com.temzu.market.msorder.services.CartService;
import com.temzu.market.msorder.util.Cart;
import com.temzu.market.routinglib.clients.ProductClient;
import java.util.UUID;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

  private final ProductClient productClient;

  private final RedisService<Cart> redisService;

  @Override
  public String generateCartUuid() {
    return UUID.randomUUID().toString();
  }

  @Override
  public Cart getCurrentCart(String cartKey) {
    return redisService
        .getOptional(cartKey)
        .orElseGet(
            () -> {
              redisService.set(cartKey, new Cart());
              return redisService.get(cartKey);
            });
  }

  @Override
  public void addToCart(String cartKey, Long productId) {
    execute(
        cartKey,
        cart -> {
          if (!cart.add(productId)) {
            cart.add(productClient.findById(productId));
          }
        });
  }

  @Override
  public void clearCart(String cartKey) {
    execute(cartKey, Cart::clear);
  }

  @Override
  public void decrementQuantity(String cartKey, Long productId) {
    execute(cartKey, cart -> cart.changeQuantity(productId, -1));
  }

  private void execute(String cartKey, Consumer<Cart> action) {
    Cart cart = getCurrentCart(cartKey);
    action.accept(cart);
    redisService.set(cartKey, cart);
  }
}
