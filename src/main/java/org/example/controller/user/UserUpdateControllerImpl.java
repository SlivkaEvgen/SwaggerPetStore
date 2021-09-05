package org.example.controller.user;

import org.example.config.ScannerConsole;
import org.example.controller.Controller;
import org.example.service.UserServiceImpl;
import java.util.Scanner;
// done
public class UserUpdateControllerImpl implements Controller {

  private final Scanner scanner = ScannerConsole.getInstance();
  private final EnterCommands enterCommands = new EnterCommands();

  private void update() {
    Integer update =
        new UserServiceImpl()
            .update(
                Integer.valueOf(enterCommands.enterId()),
                enterCommands.enterUserName(),
                enterCommands.enterFirstName(),
                enterCommands.enterLastName(),
                200,
                enterCommands.enterPassword(),
                enterCommands.enterEmail(),
                enterCommands.enterPhone());
    if (update == 200) {
      System.out.println(" ✅ Successfully");
    } else {
      System.out.print("\n      ⚠️ Wrong ⚠️ \n \uD83D\uDCAC Please, enter again \n");
      update();
    }
  }

  @Override
  public void start() {
    update();
  }

  @Override
  public void stop() {
    System.exit(0);
    scanner.close();
  }
}
