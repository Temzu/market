package com.temzu.market.msorder.dao.services;

import com.temzu.market.msorder.dao.entities.Cart;
import java.util.Optional;
import java.util.UUID;

public interface CartDao {

  Cart findCartByUuid(UUID cartUuid);

  Optional<Cart> findByUserId(Long userId);

  Cart save(Cart cart);

  void delete(Cart cart);


}
