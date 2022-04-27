package com.temzu.market.msproduct.dao.services;

import com.temzu.market.msproduct.dao.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

public interface ProductDao {

  Page<Product> findPage(Specification<Product> spec, int page, int pageSize);

  Product findById(Long id);

  boolean existById(Long id);

  Product saveOrUpdate(Product product);

  void deleteById(Long id);

}
