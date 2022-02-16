package com.temzu.market.msauth.dao.services;

import com.temzu.market.msauth.dao.entites.User;

public interface UserDao {

  User save(User user);

  User findByLogin(String login);

  User findByLoginAndPassword(String login, String password);

}
