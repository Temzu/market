package com.temzu.market.msproduct.dao.impl;

import com.temzu.market.corelib.exceptions.EntityBadIdException;
import com.temzu.market.corelib.exceptions.ResourceNotFoundException;
import com.temzu.market.corelib.validators.EntityIdValidator;
import com.temzu.market.msproduct.dao.ProductDao;
import com.temzu.market.msproduct.models.Product;
import com.temzu.market.msproduct.repositories.ProductRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductDaoImpl implements ProductDao {

  private final ProductRepository productRepository;

  @Override
  public Page<Product> getAll(@NonNull Specification<Product> spec, int page, int pageSize) {
    return productRepository.findAll(spec, PageRequest.of(page - 1, pageSize));
  }

  @Override
  public Product findById(@NonNull Long id) {
    EntityIdValidator.mustBeGreaterThanZero(Product.class, id);
    return productRepository.findById(id)
        .orElseThrow(() -> ResourceNotFoundException.byId(id, Product.class));
  }

  @Override
  public void deleteById(@NonNull Long id) {
    EntityIdValidator.mustBeGreaterThanZero(Product.class, id);
    productRepository.delete(findById(id));
  }

  @Override
  public Product add(@NonNull Product product) {
    EntityIdValidator.mustBeNull(Product.class, product.getId());

    return productRepository.save(product);
  }
}
