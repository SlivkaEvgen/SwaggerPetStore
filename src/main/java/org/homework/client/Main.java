package org.homework.client;

import org.homework.controller.Start;
import org.homework.controller.user.UserCreateArray;
import org.homework.model.User;
import org.homework.repository.UserRepositoryImpl;

import java.awt.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {
//      UserRepositoryImpl userRepository = new UserRepositoryImpl();
//      List<User> userList = new ArrayList<>();
//
//
//      User[]users1 = new User[userList.size()];
//      User[]users = {User.builder().build(),User.builder().build(),User.builder().build()};
//      Long arrayUsers = userRepository.createArrayUsers(users);
//      UserCreateArray userCreateArray = new UserCreateArray();
//      userCreateArray.so();
//    System.out.println("arrayUsers");
      Start.getController().start();
  }
}
