package org.homework.controller.store;

import lombok.NoArgsConstructor;
import org.homework.controller.interfaces.Controller;
import org.homework.controller.EnterCommands;
import org.homework.model.Order;
import org.homework.service.StoreServiceImpl;

@NoArgsConstructor
public class StoreCreate implements Controller {

  private final EnterCommands enterCommands = EnterCommands.getEnterCommands();
  private final StoreServiceImpl storeService = StoreServiceImpl.getStoreService();
  private static StoreCreate storeCreateCommand;

  public static StoreCreate getStoreCreateCommand() {
    if (storeCreateCommand == null) {
      storeCreateCommand = new StoreCreate();
    }
    return storeCreateCommand;
  }

  private void create(){
      Order order =
              storeService.placeAnOrderForAPet(
                      enterCommands.enterId(),
                      enterCommands.enterPetId(),
                      Integer.valueOf(enterCommands.enterQuantity()),
                      enterCommands.enterStatusStore());
      if (order.getId() != null) {
          System.out.println(" ✅ Successfully");
          System.out.println(order);
      } else {
          System.out.print("\n      ⚠️ Wrong ⚠️ \n \uD83D\uDCAC Please, enter again \n");
          start();
      }
  }
  @Override
  public void start() {
    create();
  }

  @Override
  public void stop() {
    System.exit(0);
  }
}
