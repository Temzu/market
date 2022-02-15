package com.temzu.market.msproduct.models.mappers;

import com.temzu.market.msproduct.models.Product;
import com.temzu.market.routinglib.dtos.ProductDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper {

  private final ModelMapper mapper;

  public Product toProduct(ProductDto productDto) {
    return mapper.map(productDto, Product.class);
  }

  public ProductDto toProductDto(Product product) {
    return mapper.map(product, ProductDto.class);
  }
}
