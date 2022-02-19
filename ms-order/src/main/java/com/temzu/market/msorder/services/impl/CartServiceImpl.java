package com.temzu.market.msorder.services.impl;

import com.temzu.market.corelib.services.TokenService;
import com.temzu.market.msorder.dao.entities.Cart;
import com.temzu.market.msorder.dao.entities.CartItem;
import com.temzu.market.msorder.dao.services.CartDao;
import com.temzu.market.msorder.mappers.CartMapper;
import com.temzu.market.msorder.services.CartService;
import com.temzu.market.routinglib.clients.ProductClient;
import com.temzu.market.routinglib.dtos.CartDto;
import com.temzu.market.routinglib.dtos.ProductDto;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {

  private final TokenService tokenService;

  private final CartDao cartDao;

  private final CartMapper cartMapper;

  private final ProductClient productClient;

  @Override
  public CartDto findCartByUuid(UUID cartUuid) {
    return cartMapper.toCartDto(cartDao.findCartByUuid(cartUuid));
  }

  @Override
  public UUID findCartForUser(String token, UUID cartUuid) {
    Long userId = tokenService.getUserId(token);
    if (userId == null) {
      Cart cart = cartDao.save(new Cart());
      return cart.getId();
    }

    if (cartUuid != null) {
      Cart cart = cartDao.findCartByUuid(cartUuid);
      cartDao.findByUserId(userId).ifPresent(oldCart -> {
        cart.merge(oldCart);
        cartDao.delete(oldCart);
      });
      cart.setUserId(userId);
    }

    Optional<Cart> cart = cartDao.findByUserId(userId);
    if (cart.isPresent()) {
      return cart.get().getId();
    }
    Cart newCart = new Cart();
    newCart.setUserId(userId);
    cartDao.save(newCart);
    return newCart.getId();
  }

  @Override
  public void addToCart(UUID cartUuid, Long productId) {
    Cart cart = cartDao.findCartByUuid(cartUuid);
    Optional.ofNullable(cart.getItemByProductId(productId))
        .ifPresentOrElse(
            ci -> {
              ci.incrementQuantity();
              cart.recalculate();
            },
            () -> {
              ProductDto productDto = productClient.findProductById(productId);
              cart.add(new CartItem(productDto));
            }
        );
  }

  @Override
  public void clearCart(UUID cartUuid) {
    cartDao.findCartByUuid(cartUuid).clear();
  }
}
