package com.temzu.market.msproduct.dao.impl;

import com.temzu.market.corelib.exceptions.ResourceNotFoundException;
import com.temzu.market.msproduct.dao.ProductDao;
import com.temzu.market.msproduct.models.Product;
import com.temzu.market.msproduct.repositories.ProductRepository;
import com.temzu.market.msproduct.services.ProductService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductDaoImpl implements ProductDao {

  private final ProductRepository productRepository;

  @Override
  public Product findById(Long id) {
    return productRepository.findById(id)
        .orElseThrow(() -> ResourceNotFoundException.byId(id, Product.class));
  }

  @Override
  public void deleteById(Long id) {
    productRepository.deleteById(id);
  }

  @Override
  public Product save(Product product) {
    return productRepository.save(product);
  }
}
