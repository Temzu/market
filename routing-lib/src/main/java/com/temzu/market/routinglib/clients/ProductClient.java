package com.temzu.market.routinglib.clients;

import com.temzu.market.routinglib.dtos.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("ms-product")
public interface ProductClient {

  @GetMapping("/api/v1/{id}")
  ProductDto findProductById(@PathVariable Long id);

}
