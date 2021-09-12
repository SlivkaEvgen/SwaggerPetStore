package org.homework.controller.store;

import lombok.NoArgsConstructor;
import org.homework.controller.interfaces.Controller;
import org.homework.service.StoreServiceImpl;

@NoArgsConstructor
public class StoreInventory implements Controller {

  private final StoreServiceImpl storeService = StoreServiceImpl.getStoreService();
  private static StoreInventory storeInventoryCommand;

  public static StoreInventory getStoreInventoryCommand() {
    if (storeInventoryCommand == null) {
      storeInventoryCommand = new StoreInventory();
    }
    return storeInventoryCommand;
  }

  private void inventory(){
       storeService.returnsPetInventoriesByStatus();
  }
  @Override
  public  void start() {
      inventory();
  }

  @Override
  public void stop() {
    System.exit(0);
  }
}
