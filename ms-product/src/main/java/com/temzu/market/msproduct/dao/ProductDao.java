package com.temzu.market.msproduct.dao;

import com.temzu.market.msproduct.models.Product;

public interface ProductDao {

  Product findById(Long id);

  void deleteById(Long id);

  Product save(Product product);

}
