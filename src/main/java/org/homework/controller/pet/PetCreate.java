package org.homework.controller.pet;

import lombok.NoArgsConstructor;
import org.homework.controller.interfaces.Controller;
import org.homework.controller.EnterCommands;
import org.homework.model.Pet;
import org.homework.service.PetServiceImpl;

@NoArgsConstructor
public class PetCreate implements Controller {

  private static PetCreate petCreateCommand;
  private final EnterCommands enterCommands = EnterCommands.getEnterCommands();
  private final PetServiceImpl petService = PetServiceImpl.getPetServiceImpl();

  public static PetCreate getPetCreateCommand() {
    if (petCreateCommand == null) {
      petCreateCommand = new PetCreate();
    }
    return petCreateCommand;
  }

  private void create() {
    Pet pet =
        petService.create(
            enterCommands.enterId(),
            enterCommands.enterName(),
            enterCommands.enterStatus(),
            enterCommands.createCategory(),
            enterCommands.createListImages(),
            enterCommands.createTagList());
    System.out.println(pet);
    if (pet.getId() != 0) {
      System.out.println(" ✅ Successfully");
    } else {
      System.out.print("\n      ⚠️ Something Wrong ⚠️ \n \uD83D\uDCAC Please, try again \n ");
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
