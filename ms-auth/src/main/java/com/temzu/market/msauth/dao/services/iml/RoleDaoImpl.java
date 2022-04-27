package com.temzu.market.msauth.dao.services.iml;

import com.temzu.market.corelib.exceptions.ResourceNotFoundException;
import com.temzu.market.msauth.dao.entites.Role;
import com.temzu.market.msauth.dao.repositories.RoleRepository;
import com.temzu.market.msauth.dao.services.RoleDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleDaoImpl implements RoleDao {

  private final RoleRepository roleRepository;

  @Override
  public Role findByName(String name) {
    return roleRepository
        .findByName(name)
        .orElseThrow(() -> ResourceNotFoundException.byName(name, Role.class));
  }
}
