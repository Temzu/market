package com.temzu.market.msorder.services;

import com.temzu.market.routinglib.dtos.CartDto;
import java.util.UUID;

public interface CartService {

  CartDto findCartByUuid(UUID cartUuid);
}
