package com.temzu.market.msorder.services;

import com.temzu.market.routinglib.dtos.CartDto;
import java.util.UUID;

public interface CartService {

  CartDto findCartByUuid(UUID cartUuid);

  UUID findCartForUser(String token, UUID cartUuid);

  UUID createCartForUser(String token);

  void addToCart(UUID cartUuid, Long productId);

  void clearCart(UUID cartUuid);
}
