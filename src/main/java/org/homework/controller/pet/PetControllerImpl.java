package org.homework.controller.pet;

import lombok.NoArgsConstructor;
import org.homework.config.ScannerConsole;
import org.homework.controller.ControllerImpl;
import org.homework.controller.interfaces.PetController;

import java.util.Scanner;

@NoArgsConstructor
public class PetControllerImpl implements PetController {

  private static PetControllerImpl petControllerImpl;
  private final Scanner scanner = ScannerConsole.getInstance();

  public static PetControllerImpl getPetControllerImpl() {
    if (petControllerImpl == null) {
      petControllerImpl = new PetControllerImpl();
    }
    return petControllerImpl;
  }

  public void start() {
    System.out.print(
        "\n \uD83D\uDC49 GET\n \uD83D\uDC49 CREATE\n \uD83D\uDC49 UPDATE\n \uD83D\uDC49 UPLOAD-IMAGE\n \uD83D\uDC49 DELETE \n   \uD83D\uDC49 BACK \n   \uD83D\uDC49 STOP\n\uD83D\uDC49 ");
    String next = scanner.next();
    if (next.equalsIgnoreCase("get")) {
      get();
      start();
    }
    if (next.equalsIgnoreCase("create")) {
      create();
      start();
    }
    if (next.equalsIgnoreCase("update")) {
      update();
      start();
    }
    if (next.equalsIgnoreCase("upload-image")) {
      uploadImage();
      start();
    }
    if (next.equalsIgnoreCase("delete")) {
      delete();
      start();
    }
    if (next.equalsIgnoreCase("back")) {
      new ControllerImpl().start();
    }
    if (next.equalsIgnoreCase("stop")) {
      stop();
    } else {
      System.out.print("\n      ⚠️ Wrong ⚠️ \n \uD83D\uDCAC Please, enter again \n");
      start();
    }
  }

  public void stop() {
    System.exit(0);
  }

  @Override
  public void get() {
    PetGet.getPetGetCommand().start();
  }

  @Override
  public void create() {
    PetCreate.getPetCreateCommand().start();
  }

  @Override
  public void update() {
    PetUpdate.getPetUpdateCommand().start();
  }

  @Override
  public void delete() {
    PetDelete.getPetDeleteCommand().start();
  }

  @Override
  public void uploadImage() {
    PetUpLoadImage.getPetUpLoadImageCommand().start();
  }
}
