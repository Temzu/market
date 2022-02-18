package com.temzu.market.msorder.dao.services;

import com.temzu.market.msorder.dao.entities.Cart;
import java.util.UUID;

public interface CartDao {

  Cart findCartByUuid(UUID cartUuid);
}
