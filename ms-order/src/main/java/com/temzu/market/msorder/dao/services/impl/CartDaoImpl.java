package com.temzu.market.msorder.dao.services.impl;

import com.temzu.market.corelib.exceptions.ResourceNotFoundException;
import com.temzu.market.msorder.dao.entities.Cart;
import com.temzu.market.msorder.dao.repositoriies.CartRepository;
import com.temzu.market.msorder.dao.services.CartDao;
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
}
