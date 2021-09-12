package org.homework.controller.store;

import lombok.NoArgsConstructor;
import org.homework.config.ScannerConsole;
import org.homework.controller.interfaces.Controller;
import org.homework.service.StoreServiceImpl;
import org.homework.util.Validator;

import java.util.Scanner;

@NoArgsConstructor
public class StoreDelete implements Controller {

  private final Scanner scanner = ScannerConsole.getInstance();
  private final StoreServiceImpl storeService = StoreServiceImpl.getStoreService();
  private static StoreDelete storeDeleteCommand;

  public static StoreDelete getStoreDeleteCommand() {
    if (storeDeleteCommand == null) {
      storeDeleteCommand = new StoreDelete();
    }
    return storeDeleteCommand;
  }

  @Override
  public void start() {
    System.out.print(" ENTER ORDER-ID \n \uD83D\uDC49 ");
    delete();
  }

  private void delete() {
    String orderId = scanner.next();
    if (Validator.validNumber(orderId)) {
      if (storeService.delete(Long.valueOf(orderId)) == 200) {
        System.out.println(" ✅ Successfully");
      } else {
        System.out.print("\n      ⚠️ Not found ⚠️ \n \uD83D\uDCAC Please, enter again \n");
        start();
      }
    } else {
      System.out.print("\n      ⚠️ Not found ⚠️ \n \uD83D\uDCAC Please, enter again \n");
      start();
    }
  }

  @Override
  public void stop() {
    System.exit(0);
  }
}
