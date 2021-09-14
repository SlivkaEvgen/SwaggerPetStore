package org.homework.service.interfaces;

import org.homework.model.User;

import java.util.List;

public interface UserService {

  Long loginUser(String username, String password);

  Long logOutUser();

  User getByUserName(String userName);

  Long createListUsers(List<User> usersList);

  Long createArrayUsers(User[] arrayUsers);

  Long createNewUser(
      Long id,
      String userName,
      String firstName,
      String lastName,
      String password,
      String email,
      String phone);

  Long update(
      Long id,
      String userName,
      String firstName,
      String lastName,
      String password,
      String email,
      String phone);

  Long delete(String userName);
}
