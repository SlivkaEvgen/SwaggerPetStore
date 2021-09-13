package org.homework.controller.pet;

import lombok.NoArgsConstructor;
import org.homework.config.ScannerConsole;
import org.homework.controller.interfaces.Controller;
import org.homework.service.PetServiceImpl;
import org.homework.util.Validator;

import java.io.File;
import java.util.Scanner;

@NoArgsConstructor
public class PetUpLoadImage implements Controller {

  private static PetUpLoadImage petUpLoadImageCommand;
  private final Scanner scanner = ScannerConsole.getInstance();
  private final PetServiceImpl petService = PetServiceImpl.getPetServiceImpl();

  public static PetUpLoadImage getPetUpLoadImageCommand() {
    if (petUpLoadImageCommand == null) {
      petUpLoadImageCommand = new PetUpLoadImage();
    }
    return petUpLoadImageCommand;
  }

  private void upload() {
    System.out.print(" ENTER PET-ID \n \uD83D\uDC49 ");
    String petId = scanner.next();
    if (!Validator.validNumber(petId)) {
      System.out.print(
          "\n      ⚠️ Something Wrong ⚠️ \n \uD83D\uDCAC Please, try again ( only numbers ) \n ");
      upload();
    }
    Long status = petService.uploadImage(validPath(), Long.valueOf(petId));
    if (status == 200) {
      System.out.println(" ✅ Successfully");
    }
  }

  private File validPath() {
    System.out.print(
        " Please, enter the path to the file \n EXAMPLE \uD83D\uDC49 /User/DESKTOP/logo.png \n\uD83D\uDC49 ");
    String filePath = scanner.next();
    if (!filePath.contains("/")) {
      System.out.print("\n      ⚠️ Something Wrong ⚠️ \n \uD83D\uDCAC Please, try again ) \n ");
     return validPath();
    }
    if (!filePath.contains(".png") | filePath.contains(".jpg")) {
      System.out.print("\n      ⚠️ Something Wrong ⚠️ \n \uD83D\uDCAC Please, try again ) \n ");
      return validPath();
    }
    File file = new File(filePath);
    if (!file.exists()) {
      System.out.print("\n      ⚠️ Something Wrong ⚠️ \n \uD83D\uDCAC Please, try again ) \n ");
     return validPath();
    }
    return file;
  }

  @Override
  public void start() {
    upload();
  }

  @Override
  public void stop() {
    System.exit(0);
  }
}
