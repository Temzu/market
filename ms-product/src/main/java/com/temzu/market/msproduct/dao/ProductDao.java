package com.temzu.market.msproduct.dao;

import com.temzu.market.msproduct.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface ProductDao {

  Page<Product> getAll(Specification<Product> spec, int page, int pageSize);

  Product findById(Long id);

  void deleteById(Long id);

  Product add(Product product);

}
