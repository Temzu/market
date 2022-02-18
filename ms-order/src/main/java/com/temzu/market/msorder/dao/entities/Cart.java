package com.temzu.market.msorder.dao.entities;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "carts")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Cart {

  @Id
  @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
  @GeneratedValue(generator = "UUIDGenerator")
  @Column(name = "id")
  private UUID id;

  @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
  @Exclude
  private List<CartItem> items;

  @Column(name = "price")
  private BigDecimal price;

  @Column
  private long userId;

  public void add(CartItem cartItem) {
    items.stream()
        .filter(ci -> ci.getProductId() == cartItem.getProductId())
        .findFirst()
        .ifPresentOrElse(
            ci -> ci.incrementQuantity(cartItem.getQuantity()),
            () -> {
              this.items.add(cartItem);
              cartItem.setCart(this);
            }
        );
    recalculate();
  }

  public void recalculate() {
    price = BigDecimal.ZERO;
    items.forEach(cartItem -> price = price.add(cartItem.getPrice()));
  }

  public void clear() {
    items.forEach(cartItem -> cartItem.setCart(null));
    items.clear();
    recalculate();
  }

  public CartItem getItemByProductId(Long productId) {
    return items.stream()
        .filter(cartItem -> cartItem.getProductId() == productId)
        .findFirst()
        .orElse(null);
  }

  public void merge(Cart another) {
    another.items.forEach(this::add);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    Cart cart = (Cart) o;
    return id != null && Objects.equals(id, cart.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
