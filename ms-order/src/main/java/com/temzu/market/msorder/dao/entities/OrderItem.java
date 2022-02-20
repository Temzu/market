package com.temzu.market.msorder.dao.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "order_items")
public class OrderItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "order_id")
  private Order order;

  @Column(name = "product_id")
  private long productId;

  @Column(name = "quantity")
  private int quantity;

  @Column(name = "price_per_product")
  private BigDecimal pricePerProduct;

  @Column(name = "price")
  private BigDecimal price;

  @CreationTimestamp
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  public OrderItem(CartItem cartItem) {
    this.productId = cartItem.getProductId();
    this.quantity = cartItem.getQuantity();
    this.pricePerProduct = cartItem.getPricePerProduct();
    this.price = cartItem.getPrice();
  }

}
