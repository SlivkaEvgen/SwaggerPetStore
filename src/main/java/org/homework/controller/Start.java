package org.homework.controller;

import lombok.NoArgsConstructor;
import org.homework.config.ScannerConsole;
import org.homework.controller.interfaces.Controller;

import java.util.Scanner;

@NoArgsConstructor
public class Start implements Controller {

  private static Start controller;
  private final Scanner scanner = ScannerConsole.getInstance();

  public static Start getController() {
    if (controller == null) {
      controller = new Start();
    }
    return controller;
  }

  @Override
  public void start() {
    System.out.print(" HELLO!\uD83D\uDC4B\n");
    startConsole();
  }

  private void startConsole() {
    System.out.print("   \uD83D\uDC49 Start \n   \uD83D\uDC49 Stop\n\uD83D\uDC49 ");
    String start = scanner.next();
    if (start.equalsIgnoreCase("start")) {
      ControllerImpl.getCommand().start();
    }
    if (start.equalsIgnoreCase("stop")) {
      stop();
    } else {
      System.out.print("\n      ⚠️ Wrong ⚠️ \n \uD83D\uDCAC Please, enter again \n");
      startConsole();
    }
  }

  @Override
  public void stop() {
    System.exit(0);
  }
}
