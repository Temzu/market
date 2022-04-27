package com.temzu.market.msproduct.mappers;

import com.temzu.market.msproduct.dao.entities.Product;
import com.temzu.market.routinglib.dtos.ProductCreateDto;
import com.temzu.market.routinglib.dtos.ProductDto;
import com.temzu.market.routinglib.dtos.ProductUpdateDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@RequiredArgsConstructor
public class ProductMapper {

  private final ModelMapper mapper;

  public Product toProduct(ProductDto productDto) {
    return mapper.map(productDto, Product.class);
  }

  public Product toProduct(ProductCreateDto productCreateDto) {
    Product product = mapper.map(productCreateDto, Product.class);
    product.setId(null);
    return product;
  }

  public Product toProduct(ProductUpdateDto productUpdateDto) {
    return mapper.map(productUpdateDto, Product.class);
  }

  public ProductDto toProductDto(Product product) {
    return mapper.map(product, ProductDto.class);
  }
}
