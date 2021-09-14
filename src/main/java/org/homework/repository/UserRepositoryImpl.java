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
public class UserRepositoryImpl extends Parser<User,Response,Request> implements UserRepository {

  private final OkHttpClient OK_CLIENT = HttpConnect.getInstance();
  private final Gson GSON = new Gson();
  private final String URI_USER = PropertiesLoader.getProperties("uriUser");
  private static UserRepositoryImpl userRepository;
  private final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");

  public static UserRepositoryImpl getUserRepository() {
    if (userRepository == null) {
      userRepository = new UserRepositoryImpl();
    }
    return userRepository;
  }

  @SneakyThrows
  @Override
  public Long loginUser(String username, String password) {
    return (long)
        getResponse(
                getRequestBuilder("/login?username=" + username + "&password=" + password).build())
            .code();
  }

  @SneakyThrows
  @Override
  public Long logOutUser() {
    return (long) getResponse(getRequestBuilder("/logout").get().build()).code();
  }

  @SneakyThrows
  @Override
  public Long create(User user) {
    return (long)
        getResponse(
                getRequestBuilder("")
                    .post(RequestBody.create(MEDIA_TYPE, GSON.toJson(user)))
                    .build())
            .code();
  }

  @SneakyThrows
  @Override
  public User getByUserName(String userName) {
    return gsonGet("/" + userName);
  }

  @SneakyThrows
  @Override
  public Long createListUsers(List<User> usersList) {
    return (long)
        getResponse(
                getRequestBuilder("/createWithList")
                    .post(RequestBody.create(MEDIA_TYPE, GSON.toJson(usersList)))
                    .build())
            .code();
  }

  @SneakyThrows
  @Override
  public Long createArrayUsers(User[] arrayUsers) {
    return (long)
        getResponse(
                getRequestBuilder("/createWithArray")
                    .post(RequestBody.create(MEDIA_TYPE, GSON.toJson(arrayUsers)))
                    .build())
            .code();
  }

  @SneakyThrows
  @Override
  public Long update(User user, String userName) {
    return (long)
        getResponse(
                getRequestBuilder("/" + userName)
                    .put(RequestBody.create(MEDIA_TYPE, GSON.toJson(user)))
                    .build())
            .code();
  }

  @SneakyThrows
  @Override
  public Long delete(String userName) {
    return (long) getResponse(getRequestBuilder("/" + userName).delete().build()).code();
  }

  @SneakyThrows
  @Override
  protected Response getResponse(Request request) {
    return OK_CLIENT.newCall(request).execute();
  }

  @Override
  protected Request.Builder getRequestBuilder(String url) {
    return new Request.Builder().url(getUrl(url));
  }

  @Override
  protected HttpUrl getUrl(String urlUrl) {
    return HttpUrl.get(URI.create(URI_USER + urlUrl));
  }

  @SneakyThrows
  @Override
  protected User gsonGet(String urls) {
    return GSON.fromJson(getResponse(getRequestBuilder(urls).build()).body().string(), User.class);
  }

  @SneakyThrows
  @Override
  User gsonWithBody(Response response) {
    return GSON.fromJson(response.body().string(), User.class);
  }

  //  @SneakyThrows
  //  private Order gsonWithBody(Response response) {
  //    return GSON.fromJson(response.body().string(), Order.class);
  //  }
}
