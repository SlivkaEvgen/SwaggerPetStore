package org.homework.controller.pet;

import lombok.NoArgsConstructor;
import org.homework.config.ScannerConsole;
import org.homework.controller.interfaces.Controller;
import org.homework.model.Pet;
import org.homework.service.PetServiceImpl;
import org.homework.util.Validator;

import java.util.Scanner;

@NoArgsConstructor
public class PetGet implements Controller {

  private static PetGet petGetCommand;
  private final Scanner scanner = ScannerConsole.getInstance();
  private final PetServiceImpl petService = PetServiceImpl.getPetServiceImpl();

  public static PetGet getPetGetCommand() {
    if (petGetCommand == null) {
      petGetCommand = new PetGet();
    }
    return petGetCommand;
  }

  private void findById() {
    System.out.print(" ENTER ID \n \uD83D\uDC49 ");
    String id = scanner.next();
    if (Validator.validNumber(id)) {
      Pet byId = petService.findById(Long.valueOf(id));
      if (byId.getId() != null) {
        System.out.println(" ✅ Successfully");
        System.out.println(byId);
      } else {
        System.out.print("\n      ⚠️ Wrong ⚠️ \n \uD83D\uDCAC Please, enter again \n");
        findById();
      }
    } else {
      System.out.print("\n      ⚠️ Wrong ⚠️ \n \uD83D\uDCAC Please, enter again \n");
      findById();
    }
  }

  private void findByStatus() {
    System.out.print(
        " ENTER STATUS \n EXAMPLES \uD83D\uDC49 available, pending, sold\n \uD83D\uDC49 ");
    String status = scanner.next();
    if (!Validator.validString(status)
        | !status.equalsIgnoreCase("available")
            & !status.equalsIgnoreCase("pending")
            & !status.equalsIgnoreCase("sold")) {
      System.out.print("\n      ⚠️ Wrong ⚠️ \n \uD83D\uDCAC Please, enter again \n");
      findByStatus();
    } else {
      String petByStatus = petService.findPetByStatus(status);
      if (petByStatus != null) {
        System.out.println(" ✅ Successfully");
        System.out.println(petByStatus);
      }
    }
  }

  @Override
  public void start() {
    System.out.print(
        "\n \uD83D\uDC49 GetByID\n \uD83D\uDC49 GetByStatus\n   \uD83D\uDC49 BACK \n   \uD83D\uDC49 STOP\n\uD83D\uDC49 ");
    String next = scanner.next();
    if (next.equalsIgnoreCase("GetByID")) {
      findById();
      start();
    }
    if (next.equalsIgnoreCase("GetByStatus")) {
      findByStatus();
      start();
    }
    if (next.equalsIgnoreCase("BACK")) {
      new PetControllerImpl().start();
    }
    if (next.equalsIgnoreCase("STOP")) {
      stop();
    } else {
      System.out.print("\n      ⚠️ Wrong ⚠️ \n \uD83D\uDCAC Please, enter again \n");
      start();
    }
  }

  @Override
  public void stop() {
    System.exit(0);
  }
}
