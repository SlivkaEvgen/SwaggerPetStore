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
public class StoreRepositoryImpl extends Parser<Order,Response,Request> implements StoreRepository {

  private final OkHttpClient OK_CLIENT = HttpConnect.getInstance();
  private final String URI_STORE = PropertiesLoader.getProperties("uriStore");
  private final Gson GSON = new Gson();
  private static StoreRepositoryImpl storeRepository;
  private final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");

  public static StoreRepositoryImpl getStoreRepository() {
    if (storeRepository == null) {
      storeRepository = new StoreRepositoryImpl();
    }
    return storeRepository;
  }

  @SneakyThrows
  @Override
  public Long delete(Long orderId) {
    return (long) getResponse(getRequestBuilder("/order/" + orderId).delete().build()).code();
  }

  @SneakyThrows
  @Override
  public Order getById(Long orderId) {
    return gsonGet("/order/" + orderId);
  }

  @SneakyThrows
  @Override
  public Order create(Order order) {
    return gsonWithBody(
        getResponse(
            getRequestBuilder("/order")
                .post(RequestBody.create(MEDIA_TYPE, GSON.toJson(order)))
                .build()));
  }

  @SneakyThrows
  @Override
  public String getInventory() {
    return getResponse(getRequestBuilder("/inventory").build()).body().string();
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
    return HttpUrl.get(URI.create(URI_STORE + urlUrl));
  }

  @SneakyThrows
  @Override
  protected Order gsonGet(String urls) {
    return GSON.fromJson(getResponse(getRequestBuilder(urls).build()).body().string(), Order.class);
  }

  @SneakyThrows
  @Override
  protected Order gsonWithBody(Response response) {
    return GSON.fromJson(response.body().string(), Order.class);
  }
}
