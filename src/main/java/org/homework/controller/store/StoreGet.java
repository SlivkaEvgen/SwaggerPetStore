package org.homework.controller.store;

import lombok.NoArgsConstructor;
import org.homework.config.ScannerConsole;
import org.homework.controller.interfaces.Controller;
import org.homework.model.Order;
import org.homework.service.StoreServiceImpl;
import org.homework.util.Validator;

import java.util.Scanner;

@NoArgsConstructor
public class StoreGet implements Controller {

  private final Scanner scanner = ScannerConsole.getInstance();
  private final StoreServiceImpl storeService = StoreServiceImpl.getStoreService();
  private static StoreGet storeFindCommand;

  public static StoreGet getStoreFindCommand() {
    if (storeFindCommand == null) {
      storeFindCommand = new StoreGet();
    }
    return storeFindCommand;
  }

  private void getById() {
    String orderId = scanner.next();
    if (Validator.validNumber(orderId)) {
      Order byId = storeService.findById(Long.valueOf(orderId));
      if (byId.getId() != null) {
        System.out.println(byId);
        System.out.println(" ✅ Successfully");
      } else {
        System.out.print("\n      ⚠️ Wrong ⚠️ \n \uD83D\uDCAC Please, enter again \n");
        start();
      }
    } else {
      System.out.print("\n      ⚠️ Wrong ⚠️ \n \uD83D\uDCAC Please, enter again \n");
      start();
    }
  }

  @Override
  public void start() {
    System.out.print(" ENTER ORDER-ID \n \uD83D\uDC49 ");
    getById();
  }

  @Override
  public void stop() {
    System.exit(0);
  }
}
