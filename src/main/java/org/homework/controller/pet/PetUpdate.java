package org.homework.controller.pet;

import lombok.NoArgsConstructor;
import org.homework.config.ScannerConsole;
import org.homework.controller.interfaces.Controller;
import org.homework.controller.EnterCommands;
import org.homework.model.Pet;
import org.homework.service.PetServiceImpl;
import java.util.Scanner;

@NoArgsConstructor
public class PetUpdate implements Controller {

  private static PetUpdate petUpdateCommand;
  private final Scanner scanner = ScannerConsole.getInstance();
  private final EnterCommands enterCommands = EnterCommands.getEnterCommands();
  private final PetServiceImpl petService = PetServiceImpl.getPetServiceImpl();

  public static PetUpdate getPetUpdateCommand() {
    if (petUpdateCommand == null) {
      petUpdateCommand = new PetUpdate();
    }
    return petUpdateCommand;
  }

  private void updateNameAndStatus() {
      Long update =
        petService.update(
            enterCommands.enterId(),
            enterCommands.enterName(),
            enterCommands.enterStatus());
    System.out.println(update);
    if (update != 0) {
      System.out.println(" ✅ Successfully");
    } else {
      System.out.print("\n      ⚠️ Something Wrong ⚠️ \n \uD83D\uDCAC Please, try again \n ");
    }
  }

  private void updatePet() {
      Pet update =
        petService.updatePut(
            enterCommands.enterId(),
            enterCommands.enterName(),
            enterCommands.enterStatus(),
            enterCommands.createCategory(),
            enterCommands.createListImages(),
            enterCommands.createTagList());
    System.out.println(update);
    if (update.getId() != null) {
      System.out.println(" ✅ Successfully");
    } else {
      System.out.print("\n      ⚠️ Something Wrong ⚠️ \n \uD83D\uDCAC Please, try again \n ");
    }
  }

  @Override
  public void start() {
    System.out.print(
        "\n \uD83D\uDC49 updateNameAndStatus\n \uD83D\uDC49 updatePet\n   \uD83D\uDC49 BACK \n   \uD83D\uDC49 STOP\n\uD83D\uDC49 ");
    String next = scanner.next();
    if (next.equalsIgnoreCase("updateNameAndStatus")) {
      updateNameAndStatus();
      start();
    }

    if (next.equalsIgnoreCase("updatePet")) {
      updatePet();
      start();
    }

    if (next.equalsIgnoreCase("back")) {
      new PetControllerImpl().start();
    }
    if (next.equalsIgnoreCase("stop")) {
      stop();
    }
  }

  @Override
  public void stop() {
    System.exit(0);
  }
}
