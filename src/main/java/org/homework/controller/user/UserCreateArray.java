package org.homework.controller.user;

import org.homework.controller.interfaces.Controller;
import org.homework.model.User;
import org.homework.service.UserServiceImpl;
import java.util.List;

public class UserCreateArray implements Controller {

  private final UserServiceImpl userService = UserServiceImpl.getUserService();
  private final UserCreateList userCreateList = UserCreateList.getUserCreateListController();
  private static UserCreateArray userCreateArray;

  public static UserCreateArray getUserCreateArray() {
    if (userCreateArray == null) {
      userCreateArray = new UserCreateArray();
    }
    return userCreateArray;
  }

  private User[] getArrayUsers() {
    List<User> users = userCreateList.getUserList();
    User[] arrayUsers = new User[users.size()];
    for (int i = 0; i < users.size() ; i++) {
      User user = users.get(i);
      arrayUsers[i] = user;
      i++;
    }
    return arrayUsers;
  }

  private void sendArray() {
     userService.createArrayUsers(getArrayUsers());
  }

  @Override
  public void start() {
      sendArray();
  }

  @Override
  public void stop() {
    System.exit(0);
  }
}
