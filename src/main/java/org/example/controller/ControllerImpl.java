package org.example.controller;

import okhttp3.internal.http2.ConnectionShutdownException;
import org.example.config.ScannerConsole;
import java.util.Scanner;

public class ControllerImpl implements Controller {

  private final Scanner scanner = ScannerConsole.getInstance();

  @Override
  public void start() throws ConnectionShutdownException, InterruptedException {
    System.out.print(" HELLO!\uD83D\uDC4B\n");
    startConsole();
  }

  private void startConsole() throws ConnectionShutdownException, InterruptedException {
    System.out.print("   \uD83D\uDC49 Start \n   \uD83D\uDC49 Stop\n\uD83D\uDC49 ");
    String start = scanner.next();
    if (start.equalsIgnoreCase("start")) {
      new CommandImpl().start();
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
    scanner.close();
  }
}
