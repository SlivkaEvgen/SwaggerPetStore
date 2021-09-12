package org.homework.controller;

import org.homework.config.ScannerConsole;
import org.homework.controller.interfaces.Controller;
import org.homework.controller.pet.PetControllerImpl;
import org.homework.controller.store.StoreControllerImpl;
import org.homework.controller.user.UserControllerImpl;

import java.util.Scanner;

public class ControllerImpl implements Controller {

  private final Scanner scanner = ScannerConsole.getInstance();
  private static ControllerImpl command;

  public static ControllerImpl getCommand() {
    if (command == null) {
      command = new ControllerImpl();
    }
    return command;
  }

  @Override
  public void start() {
    System.out.print(
        "\n \uD83D\uDC49 User\n \uD83D\uDC49 Pet\n \uD83D\uDC49 Store\n   \uD83D\uDC49 BACK\n   \uD83D\uDC49 STOP\n\uD83D\uDC49 ");
    String next = scanner.next();
    if (next.equalsIgnoreCase("user")) {
      UserControllerImpl.getUserController().start();
      start();
    }
    if (next.equalsIgnoreCase("pet")) {
      PetControllerImpl.getPetControllerImpl().start();
      start();
    }
    if (next.equalsIgnoreCase("store")) {
      StoreControllerImpl.getStoreController().start();
      start();
    }
    if (next.equalsIgnoreCase("back")) {
      Start.getController().start();
    }
    if (next.equalsIgnoreCase("stop")) {
      stop();
    } else {
      System.out.print("\n      ⚠️ Wrong ⚠️ \n \uD83D\uDCAC Please, enter again ");
      start();
    }
  }

  @Override
  public void stop() {
    System.exit(0);
  }
}
