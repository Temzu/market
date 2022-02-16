package com.temzu.market.msauth.dao.repositories;

import com.temzu.market.msauth.dao.entites.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
