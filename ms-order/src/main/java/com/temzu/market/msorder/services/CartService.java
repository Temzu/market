package com.temzu.market.msorder.services;

import com.temzu.market.msorder.util.Cart;

public interface CartService {

  String generateCartUuid();

  Cart getCurrentCart(String cartKey);

  void addToCart(String cartKey, Long productId);

  void clearCart(String cartKey);

  void decrementQuantity(String cartKey, Long productId);

}
