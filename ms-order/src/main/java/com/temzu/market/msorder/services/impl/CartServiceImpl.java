package com.temzu.market.msorder.services.impl;

import com.temzu.market.msorder.dao.services.CartDao;
import com.temzu.market.msorder.mappers.CartMapper;
import com.temzu.market.msorder.services.CartService;
import com.temzu.market.routinglib.dtos.CartDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

  private final CartDao cartDao;
  private final CartMapper cartMapper;

  @Override
  public CartDto findCartByUuid(UUID cartUuid) {
    return cartMapper.toCartDto(cartDao.findCartByUuid(cartUuid));
  }
}
