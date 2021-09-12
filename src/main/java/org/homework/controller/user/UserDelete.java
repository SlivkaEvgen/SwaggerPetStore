package org.homework.controller.user;

import lombok.NoArgsConstructor;
import org.homework.config.ScannerConsole;
import org.homework.controller.interfaces.Controller;
import org.homework.service.UserServiceImpl;
import org.homework.util.Validator;

import java.util.Scanner;

@NoArgsConstructor
public class UserDelete implements Controller {

  private final Scanner scanner = ScannerConsole.getInstance();
  private final UserServiceImpl userService = UserServiceImpl.getUserService();
  private static UserDelete userDeleteController;

  public static UserDelete getUserDeleteController() {
    if (userDeleteController == null) {
      userDeleteController = new UserDelete();
    }
    return userDeleteController;
  }

  private void delete() {
    String username = scanner.next();
    if (Validator.validString(username)) {
      if (userService.delete(username) == 200) {
        System.out.println(" ✅ Successfully");
      } else {
        System.out.print("\n      ⚠️ Not found user ⚠️ \n \uD83D\uDCAC Please, enter again \n");
        start();
      }
    } else {
      System.out.print("\n      ⚠️ Not found USER-NAME ⚠️ \n \uD83D\uDCAC Please, enter again \n");
      start();
    }
  }

  @Override
  public void start() {
      System.out.print(" ENTER USER-NAME \n \uD83D\uDC49 ");
      delete();
  }

  @Override
  public void stop() {
    System.exit(0);
  }
}
