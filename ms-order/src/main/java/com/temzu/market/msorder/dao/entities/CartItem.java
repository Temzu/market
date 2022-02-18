package com.temzu.market.msorder.dao.entities;

import com.temzu.market.routinglib.dtos.ProductDto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "cart_items")
public class CartItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "cart_id")
  private Cart cart;

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

  public CartItem(ProductDto product) {
    this.productId = product.getId();
    this.quantity = 1;
    this.pricePerProduct = product.getPrice();
    this.price = this.pricePerProduct;
  }

  public void incrementQuantity() {
    quantity++;
    price = pricePerProduct.multiply(new BigDecimal(quantity));
  }

  public void incrementQuantity(int amount) {
    quantity += amount;
    price = pricePerProduct.multiply(new BigDecimal(quantity));
  }

  public void decrementQuantity() {
    quantity--;
    price = pricePerProduct.multiply(new BigDecimal(quantity));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    CartItem cartItem = (CartItem) o;
    return id != null && Objects.equals(id, cartItem.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}

