package org.homework.repository;

import com.google.gson.Gson;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import okhttp3.*;
import org.homework.config.HttpConnect;
import org.homework.model.Order;
import org.homework.repository.interfaces.StoreRepository;
import org.homework.util.PropertiesLoader;
import java.net.URI;

@NoArgsConstructor
public class StoreRepositoryImpl implements StoreRepository {

  private final OkHttpClient OK_CLIENT = HttpConnect.getInstance();
  private final String URI_STORE = PropertiesLoader.getProperties("uriStore");
  private final Gson GSON = new Gson();
  private static StoreRepositoryImpl storeRepository;

  public static StoreRepositoryImpl getStoreRepository() {
    if (storeRepository == null) {
      storeRepository = new StoreRepositoryImpl();
    }
    return storeRepository;
  }

  @SneakyThrows
  @Override
  public Long delete(Long orderId) {
    Request request =
        new Request.Builder()
            .url(HttpUrl.get(URI.create(URI_STORE + "/order/" + orderId)))
            .delete()
            .build();
    Response response = OK_CLIENT.newCall(request).execute();
    return (long) response.code();
  }

  @SneakyThrows
  @Override
  public Order getById(Long orderId) {
    Request request =
        new Request.Builder().url(HttpUrl.get(URI.create(URI_STORE + "/order/" + orderId))).build();
    Response response = OK_CLIENT.newCall(request).execute();
    assert response.body() != null;
    return GSON.fromJson(response.body().string(), Order.class);
  }

  @SneakyThrows
  @Override
  public Order create(Order order) {
    RequestBody requestBody =
        new Request.Builder()
            .url(HttpUrl.get(URI.create(URI_STORE + "/order")))
            .post(
                RequestBody.create(
                    MediaType.parse("application/json; charset=utf-8"), GSON.toJson(order)))
            .header("Content-type", "application/json")
            .build()
            .body();
    Response response =
        OK_CLIENT
            .newCall(
                new Request.Builder()
                    .url(HttpUrl.get(URI.create(URI_STORE + "/order")))
                    .post(requestBody)
                    .build())
            .execute();
    return GSON.fromJson(response.body().string(), Order.class);
  }

  @SneakyThrows
  @Override
  public String getInventory() {
    Request request =
        new Request.Builder().url(HttpUrl.get(URI.create(URI_STORE + "/inventory"))).get().build();
    Response response = OK_CLIENT.newCall(request).execute();
    return response.body().string();
  }
}
