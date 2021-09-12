package org.homework.controller;

import lombok.NoArgsConstructor;
import org.homework.config.ScannerConsole;
import org.homework.controller.interfaces.Controller;
import org.homework.model.Category;
import org.homework.model.Tag;
import org.homework.util.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@NoArgsConstructor
public class EnterCommands implements Controller {

  private static EnterCommands enterCommands;
  private final Scanner scanner = ScannerConsole.getInstance();
  private final List<Tag> tagList = new ArrayList<>();
  private final List<String> imagesList = new ArrayList<>();

  public static EnterCommands getEnterCommands() {
    if (enterCommands == null) {
      enterCommands = new EnterCommands();
    }
    return enterCommands;
  }

  public Long enterId() {
    System.out.print(" ENTER ID \n \uD83D\uDC49 ");
    String id = scanner.next();
    if (!Validator.validNumber(id) | id.length() > 10) {
      System.out.print("\n      ⚠️ Wrong ⚠️ \n \uD83D\uDCAC Please, enter again -> only numbers -> no more than 10  \n");
      return enterId();
    }
    return Long.valueOf(id);
  }

  public String enterName() {
    System.out.print(" ENTER NAME \n \uD83D\uDC49 ");
    String name = scanner.next();
    if (name.length() > 10) {
      System.out.print("\n      ⚠️ Wrong ⚠️ \n \uD83D\uDCAC Please, enter again \n");
      return enterName();
    }
    return name;
  }

  public String enterUserName() {
    System.out.print(" ENTER USER-NAME \n \uD83D\uDC49 ");
    String userName = scanner.next();
    if (userName.length() > 10) {
      System.out.print("\n      ⚠️ Wrong ⚠️ \n \uD83D\uDCAC Please, enter again -> no more than 10 and only letters \n");
      return enterName();
    }
    return userName;
  }

  public String enterFirstName() {
    System.out.print(" ENTER FIRSTNAME \n \uD83D\uDC49 ");
    String firstName = scanner.next();
    if (!Validator.validString(firstName) | firstName.length() > 10) {
      System.out.print("\n      ⚠️ Wrong ⚠️ \n \uD83D\uDCAC Please, enter again -> no more than 10 and only letters \n");
      return enterFirstName();
    }
    return firstName;
  }

  public String enterLastName() {
    System.out.print(" ENTER LASTNAME \n \uD83D\uDC49 ");
    String lastName = scanner.next();
    if (!Validator.validString(lastName) | lastName.length() > 10) {
      System.out.print("\n      ⚠️ Wrong ⚠️ \n \uD83D\uDCAC Please, enter again -> no more than 10 and only letters \n");
      return enterLastName();
    }
    return lastName;
  }

  public String enterPassword() {
    System.out.print(" ENTER PASSWORD \n \uD83D\uDC49 ");
    String password = scanner.next();
    if (password.length() > 10) {
      System.out.print("\n      ⚠️ Wrong ⚠️ \n \uD83D\uDCAC Please, enter again -> no more than 10 \n");
      return enterPassword();
    }
    return password;
  }

  public String enterEmail() {
    System.out.print(" ENTER EMAIL \n \uD83D\uDC49 ");
    String email = scanner.next();
    if (!Validator.validString(email) | email.length() > 20) {
      System.out.print("\n      ⚠️ Wrong ⚠️ \n \uD83D\uDCAC Please, enter again -> no more than 20 \n");
      return enterEmail();
    }
    if (!email.contains("@")) {
      System.out.print("\n      ⚠️ Wrong ⚠️ \n \uD83D\uDCAC Please, enter again -> must have '@' \n");
      return enterEmail();
    }
    return email;
  }

  public String enterPhone() {
    System.out.print(" ENTER NUMBER PHONE \n \uD83D\uDC49 ");
    String number = scanner.next();
    if (!Validator.validNumber(number) | number.length() > 12 | !number.startsWith("09")) {
      System.out.print("\n      ⚠️ Wrong ⚠️ \n \uD83D\uDCAC Please, enter again -> only numbers , no more than 12 and start with '09' \n");
      return enterPhone();
    }
    return number;
  }

  public String enterStatus() {
    System.out.print(" ENTER STATUS \n \uD83D\uDC49 ");
    System.out.print(" EXAMPLES  \uD83D\uDC49 available, pending, sold \n \uD83D\uDC49 ");
    String status = scanner.next();
    if (!Validator.validString(status)
        | !status.equalsIgnoreCase("available")
            & !status.equalsIgnoreCase("pending")
            & !status.equalsIgnoreCase("sold")) {
      System.out.print("\n      ⚠️ Wrong ⚠️ \n \uD83D\uDCAC Please, enter again \n");
      return enterStatus();
    }
    return status;
  }

  public Category createCategory() {
    System.out.print(" ❗ Attention! ❗\n ❗ CREATING CATEGORY ❗\n \uD83D\uDC49 ");
    final Category category = new Category();
    category.setId(enterCategoryId());
    category.setName(enterCategoryName());
    return category;
  }

  private Long enterCategoryId() {
    System.out.print(" ENTER CATEGORY-ID \n \uD83D\uDC49 ");
    String id = scanner.next();
    if (!Validator.validNumber(id) | id.length() > 10) {
      System.out.print("\n      ⚠️ Wrong ⚠️ \n \uD83D\uDCAC Please, enter again -> no more than 10 and only numbers  \n");
      return enterId();
    }
    return Long.valueOf(id);
  }

  private String enterCategoryName() {
    System.out.print(" ENTER CATEGORY-NAME \n \uD83D\uDC49 ");
    String name = scanner.next();
    if (!Validator.validString(name) | name.length() > 10) {
      System.out.print("\n      ⚠️ Wrong ⚠️ \n \uD83D\uDCAC Please, enter again -> no more than 10 and only letters \n");
      return enterCategoryName();
    }
    return name;
  }

  private Long enterTagId() {
    System.out.print(" ENTER TAG-ID \n \uD83D\uDC49 ");
    String id = scanner.next();
    if (!Validator.validNumber(id) | id.length() > 10) {
      System.out.print("\n      ⚠️ Wrong ⚠️ \n \uD83D\uDCAC Please, enter again -> no more than 10 and only numbers \n");
      return enterTagId();
    }
    return Long.valueOf(id);
  }

  private String enterTagName() {
    System.out.print(" ENTER TAG-NAME \n \uD83D\uDC49 ");
    String name = scanner.next();
    if (!Validator.validString(name) | name.length() > 10) {
      System.out.print("\n      ⚠️ Wrong ⚠️ \n \uD83D\uDCAC Please, enter again -> no more than 10 and only letters \n");
      return enterTagName();
    }
    return name;
  }

  public List<Tag> createTagList() {
    System.out.print(" ❗ Attention! ❗\n ❗ CREATING TAGS-LIST ❗\n \uD83D\uDC49 ");
    tagList.add(createTag());
    return tagList;
  }

  private Tag createTag() {
    Tag tag = new Tag();
    tag.setId(enterTagId());
    tag.setName(enterTagName());
    String yesNo = yesNo();
    if (Validator.validString(yesNo) & yesNo.equalsIgnoreCase("yes")) {
      tagList.add(tag);
      return createTag();
    }
    if (Validator.validString(yesNo) & yesNo.equalsIgnoreCase("no")) {
      return tag;
    } else {
      System.out.print("\n      ⚠️ Wrong ⚠️ \n \uD83D\uDCAC Please, enter again only 'yes' or 'no'\n");
      yesNo();
    }
    return tag;
  }

  private String yesNo() {
    System.out.print(
        " CREATE ANOTHER TAG ? \n \uD83D\uDC49 YES \n \uD83D\uDC49 NO \n \uD83D\uDC49 ");
    return scanner.next();
  }

  public List<String> createListImages() {
    System.out.print(" ❗ Attention! ❗\n ❗ CREATING IMAGES-LIST ❗\n \uD83D\uDC49 ");
    imagesList.add(pathToImage());
    String imageOrNo = addImageOrNo();
    if (Validator.validString(imageOrNo) & imageOrNo.equalsIgnoreCase("yes")) {
      return createListImages();
    }
    return imagesList;
  }

  private String pathToImage() {
    System.out.print(
        " Please, enter the path to the file \n EXAMPLE \uD83D\uDC49 /User/DESKTOP/logo.png \n \uD83D\uDC49 ");
    String next = scanner.next();
    if (!next.startsWith("/")
        & !next.contains("png")
        | !next.contains("jpg")
        & !next.contains("/")) {
      System.out.print("\n      ⚠️ Wrong ⚠️ \n \uD83D\uDCAC Please, enter again \n");
      return pathToImage();
    }
    return next;
  }

  private String addImageOrNo() {
    System.out.print(" ADD PHOTO ? \n \uD83D\uDC49 YES \n \uD83D\uDC49 NO \n \uD83D\uDC49 ");
    return scanner.next();
  }

  public Long enterPetId() {
    System.out.print(" ENTER PET-ID \n \uD83D\uDC49 ");
    String petId = scanner.next();
    if (!Validator.validNumber(petId)|petId.length()>10) {
      System.out.print("\n      ⚠️ Wrong ⚠️ \n \uD83D\uDCAC Please, enter again -> no more than 10 and only numbers \n");
      return enterPetId();
    }
    return Long.valueOf(petId);
  }

  public String enterQuantity() {
    System.out.print(" ENTER QUANTITY \n \uD83D\uDC49 ");
    String quantity = scanner.next();
    if (!Validator.validNumber(quantity) | quantity.length() > 3) {
      System.out.print("\n      ⚠️ Wrong ⚠️ \n \uD83D\uDCAC Please, enter again -> no more than 3 and only numbers\n");
      return enterQuantity();
    }
    return quantity;
  }

  public String enterStatusStore() {
    System.out.print(" ENTER STATUS \n \uD83D\uDC49 ");
    System.out.print(" EXAMPLES  \uD83D\uDC49 placed, approved, delivered \n \uD83D\uDC49 ");
    String status = scanner.next();
    if (!Validator.validString(status)
        | !status.equalsIgnoreCase("placed")
            & !status.equalsIgnoreCase("approved")
            & !status.equalsIgnoreCase("delivered")) {
      System.out.print("\n      ⚠️ Wrong ⚠️ \n \uD83D\uDCAC Please, enter again \n");
      return enterStatus();
    }
    return status;
  }

  @Override
  public void start() {}

  @Override
  public void stop() {
    System.exit(0);
  }
}
