package org.homework.repository.interfaces;

import org.homework.model.User;
import java.util.List;

public interface UserRepository {

  Long loginUser(String userName, String password);

  Long logOutUser();

  Long create(User user);

  User getByUserName(String userName);

  Long createListUsers(List<User> usersList);

  Long update(User user, String userName);

  Long delete(String userName);
}
