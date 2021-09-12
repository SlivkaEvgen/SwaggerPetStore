package org.homework.controller.user;

import lombok.NoArgsConstructor;
import org.homework.controller.interfaces.Controller;
import org.homework.service.UserServiceImpl;

@NoArgsConstructor
public class UserLogOut implements Controller {

  private final UserServiceImpl userService = UserServiceImpl.getUserService();
  private static UserLogOut userLogOutController;

  public static UserLogOut getUserLogOutController() {
    if (userLogOutController == null) {
      userLogOutController = new UserLogOut();
    }
    return userLogOutController;
  }

  private void logOut() {
    if (userService.logOutUser() == 200) {
      System.out.println(" ✅ Successfully");
    } else {
      System.out.print("\n      ⚠️ Wrong ⚠️ \n \uD83D\uDCAC Please, enter again \n");
      logOut();
    }
  }

  @Override
  public void start() {
    logOut();
  }

  @Override
  public void stop() {
    System.exit(0);
  }
}
