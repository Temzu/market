package com.temzu.market.msproduct.services;

import com.temzu.market.msproduct.models.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.util.MultiValueMap;

public interface ProductService {

  Page<ProductDto> findPage(MultiValueMap<String, String> params, Integer page, Integer pageSize);
}
