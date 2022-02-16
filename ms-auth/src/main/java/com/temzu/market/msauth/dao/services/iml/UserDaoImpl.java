package com.temzu.market.msauth.dao.services.iml;

import com.temzu.market.corelib.exceptions.ResourceAlreadyExistsException;
import com.temzu.market.corelib.exceptions.ResourceNotFoundException;
import com.temzu.market.corelib.exceptions.UserWrongLoginException;
import com.temzu.market.corelib.exceptions.UserWrongPasswordException;
import com.temzu.market.msauth.dao.entites.Role;
import com.temzu.market.msauth.dao.entites.User;
import com.temzu.market.msauth.dao.repositories.UserRepository;
import com.temzu.market.msauth.dao.services.RoleDao;
import com.temzu.market.msauth.dao.services.UserDao;
import com.temzu.market.msauth.enums.UserRoles;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {

  private final UserRepository userRepository;
  private final RoleDao roleDao;
  private final PasswordEncoder passwordEncoder;

  @Override
  public User save(User user) {
    String login = user.getLogin();
    if (login.isBlank() || login.contains(" ")) {
      throw new UserWrongLoginException(login);
    }
    if (userRepository.existsByLogin(login)) {
      throw ResourceAlreadyExistsException.byLogin(login, User.class);
    }

    Role role = roleDao.findByName(UserRoles.ROLE_USER.name());
    user.setRole(role);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  @Override
  public User findByLogin(String login) {
    return userRepository.findByLogin(login)
        .orElseThrow(() -> ResourceNotFoundException.byLogin(login, User.class));
  }

  @Override
  public User findByLoginAndPassword(String login, String password) {
    return Optional.of(findByLogin(login))
        .filter(user -> passwordEncoder.matches(password, user.getPassword()))
        .orElseThrow(UserWrongPasswordException::new);
  }
}
