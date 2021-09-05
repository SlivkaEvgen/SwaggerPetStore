package org.example.repository;import com.google.gson.Gson;import lombok.SneakyThrows;import okhttp3.*;import org.example.config.HttpConnect;import org.example.model.Pet;import org.example.repository.interfaces.PetRepository;import org.example.util.PropertiesLoader;import java.io.File;import java.net.URI;public class PetRepositoryImpl implements PetRepository<Pet, String> {  private final OkHttpClient OK_CLIENT = HttpConnect.getInstance();  private final String URI_PET = PropertiesLoader.getProperties("uriPet");  private final Gson GSON = new Gson();  @SneakyThrows  @Override  public Integer uploadImage(File file, Integer petId) {    RequestBody requestBody =        new MultipartBody.Builder()            .setType(MultipartBody.FORM)            .addFormDataPart(                "file", file.getName(), RequestBody.create(MediaType.parse("image/png"), file))            .build();    Response response =        OK_CLIENT            .newCall(                new Request.Builder()                    .post(requestBody)                    .url(HttpUrl.get(URI.create(URI_PET + "/" + petId + "/uploadImage")))                    .build())            .execute();    assert response.body() != null;    System.out.println("uploadFile $  " + response.body().string());    return response.code();  }  @SneakyThrows  @Override  public Integer delete(String petId) {    Request request =        new Request.Builder().url(HttpUrl.get(URI.create(URI_PET + "/" + petId))).delete().build();    Response response = OK_CLIENT.newCall(request).execute();    return response.code();  }  @SneakyThrows  @Override  public Integer findPetByStatus(String status) {    Request request =        new Request.Builder()            .url(HttpUrl.get(URI.create(URI_PET + "/findByStatus?status=" + status)))            .get()            .build();    Response response = OK_CLIENT.newCall(request).execute();    assert response.body() != null;    System.out.println(response.body().string());    return response.code();  }  @SneakyThrows  @Override  public Integer create(Pet pet) {    RequestBody requestBody =        new Request.Builder()            .url(HttpUrl.get(URI.create(URI_PET)))            .post(                RequestBody.create(                    MediaType.parse("application/json; charset=utf-8"), GSON.toJson(pet)))            .header("Content-type", "application/json")            .build()            .body();    Response response =        OK_CLIENT            .newCall(                new Request.Builder()                    .url(HttpUrl.get(URI.create(URI_PET)))                    .post(requestBody)                    .build())            .execute();    assert response.body() != null;    System.out.println(GSON.fromJson(response.body().string(), Pet.class));    return response.code();  }  @SneakyThrows  @Override  public Integer update(Pet pet, String petName) {    RequestBody requestBody =        new Request.Builder()            .url(HttpUrl.get(URI.create(URI_PET)))            .post(                RequestBody.create(                    MediaType.parse("application/json; charset=utf-8"), GSON.toJson(pet)))            .header("Content-type", "application/json")            .build()            .body();    Response response =        OK_CLIENT            .newCall(                new Request.Builder()                    .url(HttpUrl.get(URI.create(URI_PET)))                    .post(requestBody)                    .build())            .execute();    return response.code();  }  @SneakyThrows  @Override  public Integer get(String petId) {    Request request =        new Request.Builder().url(HttpUrl.get(URI.create(URI_PET + "/" + petId))).get().build();    Response response = OK_CLIENT.newCall(request).execute();    assert response.body() != null;    System.out.println(GSON.fromJson(response.body().string(), Pet.class));    return response.code();  }  @SneakyThrows  @Override  public Integer updatePut(Pet pet) {    RequestBody requestBody =        new Request.Builder()            .url(HttpUrl.get(URI.create(URI_PET)))            .put(                RequestBody.create(                    MediaType.parse("application/json; charset=utf-8"), GSON.toJson(pet)))            .build()            .body();    Response response =        OK_CLIENT            .newCall(                new Request.Builder()                    .url(HttpUrl.get(URI.create(URI_PET)))                    .post(requestBody)                    .build())            .execute();    return response.code();  }}