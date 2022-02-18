package com.temzu.market.msorder.mappers;

import com.temzu.market.msorder.dao.entities.Cart;
import com.temzu.market.routinglib.dtos.CartDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartMapper {

  private final ModelMapper mapper;

  public CartDto toCartDto(Cart cart) {
    return mapper.map(cart, CartDto.class);
  }

  public Cart toCart(CartDto cartDto) {
    return mapper.map(cartDto, Cart.class);
  }

}