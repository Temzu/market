package com.temzu.market.msauth.dao.services.iml;

import static org.junit.jupiter.api.Assertions.*;

import com.temzu.market.corelib.exceptions.ResourceNotFoundException;
import com.temzu.market.msauth.dao.entites.Role;
import com.temzu.market.msauth.dao.services.RoleDao;
import com.temzu.market.msauth.enums.UserRoles;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RoleDaoImplTest {

  @Autowired
  private RoleDao roleDao;

  @EnumSource(UserRoles.class)
  @ParameterizedTest
  void findByName_CORRECT(UserRoles role) {
    assertDoesNotThrow(() -> {
      Role byName = roleDao.findByName(role.name());
      assertEquals(role.name(), byName.getName());
    });
  }

  @CsvSource(value = {"R1", "123", "Role2"})
  @ParameterizedTest
  void findByName_WRONG(String roleName) {
    assertThrows(ResourceNotFoundException.class, () -> roleDao.findByName(roleName));
  }
}