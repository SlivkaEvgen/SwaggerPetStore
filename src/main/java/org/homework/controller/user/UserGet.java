package org.homework.controller.user;

import lombok.NoArgsConstructor;
import org.homework.config.ScannerConsole;
import org.homework.controller.interfaces.Controller;
import org.homework.model.User;
import org.homework.service.UserServiceImpl;
import org.homework.util.Validator;

import java.util.Scanner;

@NoArgsConstructor
public class UserGet implements Controller {

  private final Scanner scanner = ScannerConsole.getInstance();
  private final UserServiceImpl userService = UserServiceImpl.getUserService();
  private static UserGet userGetController;

  public static UserGet getUserGetController() {
    if (userGetController == null) {
      userGetController = new UserGet();
    }
    return userGetController;
  }

  private void getByName() {
    String userName = scanner.next();
    if (Validator.validString(userName)) {
      User user = userService.getByUserName(userName);
      if (user.getId() != null) {
        System.out.println(user);
        System.out.println(" ✅ Successfully");
      } else {
        System.out.print("\n   ⚠️ User not found ⚠️ \n \uD83D\uDCAC Please, enter again \n ");
        start();
      }
    } else {
      System.out.print("\n   ⚠️ User not found ⚠️ \n \uD83D\uDCAC Please, enter again \n ");
      start();
    }
  }

  @Override
  public void start() {
      System.out.print(" ENTER USERNAME \n \uD83D\uDC49 ");
      getByName();
  }

  @Override
  public void stop() {
    System.exit(0);
  }
}
