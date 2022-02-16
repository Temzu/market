package com.temzu.market.msauth.dao.services;

import com.temzu.market.msauth.dao.entites.Role;

public interface RoleDao {

  Role findByName(String name);
}
