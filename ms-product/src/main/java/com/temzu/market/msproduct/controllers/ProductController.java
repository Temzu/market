package com.temzu.market.msproduct.controllers;

import com.temzu.market.msproduct.models.ProductDto;
import com.temzu.market.msproduct.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @GetMapping
  public Page<ProductDto> findPage(
      @RequestParam MultiValueMap<String, String> params,
      @RequestParam(name = "page", defaultValue = "1") Integer page,
      @RequestParam(name = "size", defaultValue = "10") Integer pageSize
  ) {
    if (page < 1 || pageSize < 1) {
      page = 1;
      pageSize = 10;
    }
    return productService.findPage(params, page, pageSize);
  }

}
