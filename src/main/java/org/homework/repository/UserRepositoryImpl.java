package org.homework.repository;

import com.google.gson.Gson;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import okhttp3.*;
import org.homework.config.HttpConnect;
import org.homework.model.User;
import org.homework.repository.interfaces.UserRepository;
import org.homework.util.PropertiesLoader;

import java.net.URI;
import java.util.List;

@NoArgsConstructor
public class UserRepositoryImpl implements UserRepository {

  private final OkHttpClient OK_CLIENT = HttpConnect.getInstance();
  private final Gson GSON = new Gson();
  private final String URI_USER = PropertiesLoader.getProperties("uriUser");
  private static UserRepositoryImpl userRepository;

  public static UserRepositoryImpl getUserRepository() {
    if (userRepository == null) {
      userRepository = new UserRepositoryImpl();
    }
    return userRepository;
  }

  @SneakyThrows
  @Override
  public Long loginUser(String username, String password) {
    Request request =
        new Request.Builder()
            .url(
                HttpUrl.get(
                    URI.create(URI_USER + "/login?username=" + username + "&password=" + password)))
            .build();
    Response response = OK_CLIENT.newCall(request).execute();
    return (long) response.code();
  }

  @SneakyThrows
  @Override
  public Long logOutUser() {
    Request request =
        new Request.Builder().url(HttpUrl.get(URI.create(URI_USER + "/logout"))).get().build();
    Response response = OK_CLIENT.newCall(request).execute();
    return (long) response.code();
  }

  @SneakyThrows
  @Override
  public Long create(User user) {
    RequestBody requestBody =
        new Request.Builder()
            .url(HttpUrl.get(URI.create(URI_USER)))
            .post(
                RequestBody.create(
                    MediaType.parse("application/json; charset=utf-8"), GSON.toJson(user)))
            .header("Content-type", "application/json")
            .build()
            .body();
    Response response =
        OK_CLIENT
            .newCall(
                new Request.Builder()
                    .url(HttpUrl.get(URI.create(URI_USER)))
                    .post(requestBody)
                    .build())
            .execute();
    return (long) response.code();
  }

  @SneakyThrows
  @Override
  public User getByUserName(String userName) {
    Request request =
        new Request.Builder().url(HttpUrl.get(URI.create(URI_USER + "/" + userName))).get().build();
    Response response = OK_CLIENT.newCall(request).execute();
    return GSON.fromJson(response.body().string(), User.class);
  }

  @SneakyThrows
  @Override
  public Long createListUsers(List<User> usersList) {
    RequestBody requestBody =
        new Request.Builder()
            .url(HttpUrl.get(URI.create(URI_USER + "/createWithList")))
            .post(
                RequestBody.create(
                    MediaType.parse("application/json; charset=utf-8"), GSON.toJson(usersList)))
            .header("Content-type", "application/json")
            .build()
            .body();
    Response response =
        OK_CLIENT
            .newCall(
                new Request.Builder()
                    .url(HttpUrl.get(URI.create(URI_USER + "/createWithList")))
                    .post(requestBody)
                    .build())
            .execute();
    return (long) response.code();
  }

  @SneakyThrows
  @Override
  public Long update(User user, String userName) {
    RequestBody requestBody =
        new Request.Builder()
            .url(HttpUrl.get(URI.create(URI_USER + "/" + userName)))
            .put(
                RequestBody.create(
                    MediaType.parse("application/json; charset=utf-8"), GSON.toJson(user)))
            .header("Content-type", "application/json")
            .build()
            .body();
    Response response =
        OK_CLIENT
            .newCall(
                new Request.Builder()
                    .url(HttpUrl.get(URI.create(URI_USER + "/" + userName)))
                    .put(requestBody)
                    .build())
            .execute();
    return (long) response.code();
  }

  @SneakyThrows
  @Override
  public Long delete(String userName) {
    Request request =
        new Request.Builder()
            .url(HttpUrl.get(URI.create(URI_USER + "/" + userName)))
            .delete()
            .build();
    Response response = OK_CLIENT.newCall(request).execute();
    return (long) response.code();
  }
}
