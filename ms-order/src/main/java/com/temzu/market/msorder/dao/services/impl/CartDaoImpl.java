package com.temzu.market.msorder.dao.services.impl;

import com.temzu.market.corelib.exceptions.ResourceNotFoundException;
import com.temzu.market.msorder.dao.entities.Cart;
import com.temzu.market.msorder.dao.repositories.CartRepository;
import com.temzu.market.msorder.dao.services.CartDao;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartDaoImpl implements CartDao {

  private final CartRepository cartRepository;

  @Override
  public Cart findCartByUuid(UUID cartUuid) {
    return cartRepository.findById(cartUuid)
        .orElseThrow(() -> ResourceNotFoundException.byUuid(cartUuid, Cart.class));
  }

  @Override
  public Cart save(Cart cart) {
    return cartRepository.save(cart);
  }

  @Override
  public void delete(Cart cart) {
    cartRepository.delete(cart);
  }

  @Override
  public Optional<Cart> findByUserId(Long userId) {
    return cartRepository.findByUserId(userId);
  }
}
