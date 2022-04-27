package com.temzu.market.msauth.dao.services.iml;

import static org.junit.jupiter.api.Assertions.*;

import com.temzu.market.msauth.dao.entites.User;
import com.temzu.market.msauth.dao.services.UserDao;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class UserDaoImplTest {

  @Autowired
  private UserDao userDao;

  @Order(1)
  @CsvSource(value = {"ivan", "petr", "andrey"})
  @ParameterizedTest
  void findByLogin_CORRECT(String login) {
    User byLogin = userDao.findByLogin(login);
    assertEquals(login, byLogin.getLogin());
  }

  @Order(2)
  @CsvSource(value = {"ivan1", "petr1", "andrey1"})
  @ParameterizedTest
  void findByLogin_WRONG(String login) {
    assertThrows(ResourceNotFoundException.class, () -> userDao.findByLogin(login));
  }

  @Order(3)
  @CsvSource(value = {"ivan", "petr", "andrey"})
  @ParameterizedTest
  void findByLoginAndPassword_CORRECT(String login) {
    String correctPass = "123";
    User user = userDao.findByLoginAndPassword(login, correctPass);
    assertEquals(login, user.getLogin());
  }

  @Order(4)
  @CsvSource(value = {"ivan", "petr", "andrey"})
  @ParameterizedTest
  void findByLoginAndPassword_WRONG(String login) {
    String wrongPass = "123234245sfdg";
    assertThrows(UserWrongPasswordException.class,
        () -> userDao.findByLoginAndPassword(login, wrongPass));
  }

  @Order(5)
  @CsvSource(value = {"us er", "use r", "u ser"})
  @EmptySource
  @ParameterizedTest
  void save_when_wrong_login(String login) {
    User user = new User();
    user.setLogin(login);
    user.setPassword(login);

    assertThrows(UserWrongLoginException.class, () -> userDao.save(user));
  }

  @Order(6)
  @CsvSource(value = {"ivan", "petr", "andrey"})
  @ParameterizedTest
  void save_when_login_already_exists(String login) {
    User user = new User();
    user.setLogin(login);
    user.setPassword(login);

    assertThrows(ResourceAlreadyExistsException.class, () -> userDao.save(user));
  }

  @Order(7)
  @CsvSource(value = {"ivan", "petr", "andrey"})
  @ParameterizedTest
  void save_CORRECT(String login) {

  }

}