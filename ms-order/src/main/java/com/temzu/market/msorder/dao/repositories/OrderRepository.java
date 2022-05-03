package com.temzu.market.msorder.dao.repositories;

import com.temzu.market.msorder.dao.entities.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

  Page<Order> findAllByUserId(Long userId, Pageable pageable);
}
