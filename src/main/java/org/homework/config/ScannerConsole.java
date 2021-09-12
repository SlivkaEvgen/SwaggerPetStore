package org.homework.config;

import java.io.Closeable;
import java.util.Scanner;

public class ScannerConsole implements Closeable {

  private static Scanner scanner;

  public static Scanner getInstance() {
    if (scanner == null) {
      scanner = new Scanner(System.in);
    }
    return scanner;
  }

  @Override
  public void close() {
    scanner.close();
  }
}
