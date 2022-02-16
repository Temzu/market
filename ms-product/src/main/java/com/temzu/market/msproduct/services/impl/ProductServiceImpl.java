package com.temzu.market.msproduct.services.impl;

import com.temzu.market.msproduct.dao.services.ProductDao;
import com.temzu.market.msproduct.dao.repositories.specification.ProductSpecifications;
import com.temzu.market.msproduct.dao.entities.Product;
import com.temzu.market.routinglib.dtos.ProductDto;
import com.temzu.market.msproduct.mappers.ProductMapper;
import com.temzu.market.msproduct.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductDao productDao;
  private final ProductMapper productMapper;

  @Override
  public Page<ProductDto> findPage(
      MultiValueMap<String, String> params,
      Integer page,
      Integer pageSize
  ) {
    Specification<Product> specification = ProductSpecifications.build(params);
    return productDao
        .findPage(specification, page, pageSize)
        .map(productMapper::toProductDto);
  }
}
