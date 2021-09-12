package org.homework.controller.user;

import lombok.NoArgsConstructor;
import org.homework.controller.interfaces.Controller;
import org.homework.controller.EnterCommands;
import org.homework.service.UserServiceImpl;

@NoArgsConstructor
public class UserUpdate implements Controller {

  private final UserServiceImpl userService = UserServiceImpl.getUserService();
  private final EnterCommands enterCommands = EnterCommands.getEnterCommands();
  private static UserUpdate userUpdateController;

  public static UserUpdate getUserUpdateController() {
    if (userUpdateController == null) {
      userUpdateController = new UserUpdate();
    }
    return userUpdateController;
  }

  private void update() {
    Long update =
        userService.update(
            enterCommands.enterId(),
            enterCommands.enterUserName(),
            enterCommands.enterFirstName(),
            enterCommands.enterLastName(),
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
  }
}
