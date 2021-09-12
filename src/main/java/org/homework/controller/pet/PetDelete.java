package org.homework.controller.pet;

import lombok.NoArgsConstructor;
import org.homework.config.ScannerConsole;
import org.homework.controller.interfaces.Controller;
import org.homework.service.PetServiceImpl;
import org.homework.util.Validator;

import java.util.Scanner;

@NoArgsConstructor
public class PetDelete implements Controller {

  private static PetDelete petDeleteCommand;
  private final Scanner scanner = ScannerConsole.getInstance();
  private final PetServiceImpl petService = PetServiceImpl.getPetServiceImpl();

  public static PetDelete getPetDeleteCommand() {
    if (petDeleteCommand == null) {
      petDeleteCommand = new PetDelete();
    }
    return petDeleteCommand;
  }

  private void delete() {
    System.out.print(" ENTER ID \n \uD83D\uDC49 ");
    String id = scanner.next();
    if (Validator.validNumber(id)) {
      if (petService.delete(Long.valueOf(id)) == 200) {
        System.out.println(" ✅ Successfully");
      } else {
        System.out.print("\n      ⚠️ Wrong ⚠️ \n \uD83D\uDCAC Please, enter again \n");
        delete();
      }
    } else {
      System.out.print("\n      ⚠️ Wrong ⚠️ \n \uD83D\uDCAC Please, enter again \n");
      delete();
    }
  }

  @Override
  public void start() {
    delete();
  }

  @Override
  public void stop() {
    System.exit(0);
  }
}
