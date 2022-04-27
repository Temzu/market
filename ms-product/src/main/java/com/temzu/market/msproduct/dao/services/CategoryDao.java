package com.temzu.market.msproduct.dao.services;

import com.temzu.market.msproduct.dao.entities.Category;

public interface CategoryDao {

  Category findById(Long id);

  Category findByTitle(String title);

}
