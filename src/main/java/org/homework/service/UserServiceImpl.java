package org.homework.service;

import lombok.NoArgsConstructor;
import org.homework.model.User;
import org.homework.repository.UserRepositoryImpl;
import org.homework.service.interfaces.UserService;

import java.util.List;

@NoArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepositoryImpl userRepository = UserRepositoryImpl.getUserRepository();
  private static UserServiceImpl userService;

  public static UserServiceImpl getUserService() {
    if (userService == null) {
      userService = new UserServiceImpl();
    }
    return userService;
  }

  @Override
  public Long loginUser(String username, String password) {
    return userRepository.loginUser(username, password);
  }

  @Override
  public Long logOutUser() {
    return userRepository.logOutUser();
  }

  @Override
  public User getByUserName(String userName) {
    return userRepository.getByUserName(userName);
  }

  @Override
  public Long createListUsers(List<User> usersList) {
    return userRepository.createListUsers(usersList);
  }

  @Override
  public Long createArrayUsers(User[]arrayUsers) {
    return userRepository.createArrayUsers(arrayUsers);
  }

  @Override
  public Long createNewUser(
      Long id,
      String userName,
      String firstName,
      String lastName,
      String password,
      String email,
      String phone) {
    User user = new User();
    user.setId(id);
    user.setUsername(userName);
    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setUserStatus(200);
    user.setPassword(password);
    user.setEmail(email);
    user.setPhone(phone);
    return userRepository.create(user);
  }

  @Override
  public Long update(
      Long id,
      String userName,
      String firstName,
      String lastName,
      String password,
      String email,
      String phone) {
    User user = new User();
    user.setId(id);
    user.setUsername(userName);
    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setPassword(password);
    user.setEmail(email);
    user.setPhone(phone);
    return userRepository.update(user, userName);
  }

  public Long delete(String userName) {
    return userRepository.delete(userName);
  }
}
