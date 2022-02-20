package com.temzu.market.msorder.dao.repositories;

import com.temzu.market.msorder.dao.entities.Cart;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, UUID> {

  Optional<Cart> findByUserId(Long userId);

}
