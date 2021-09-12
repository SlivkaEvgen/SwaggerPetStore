package org.homework.repository;

import com.google.gson.Gson;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import okhttp3.*;
import org.homework.config.HttpConnect;
import org.homework.model.Pet;
import org.homework.repository.interfaces.PetRepository;
import org.homework.util.PropertiesLoader;

import java.io.File;
import java.net.URI;
import java.util.Objects;

@NoArgsConstructor
public class PetRepositoryImpl implements PetRepository {

  private final OkHttpClient OK_CLIENT = HttpConnect.getInstance();
  private final String URI_PET = PropertiesLoader.getProperties("uriPet");
  private final Gson GSON = new Gson();
  private static PetRepositoryImpl petRepository;

  public static PetRepositoryImpl getPetRepository() {
    if (petRepository == null) {
      petRepository = new PetRepositoryImpl();
    }
    return petRepository;
  }

  @SneakyThrows
  @Override
  public Long uploadImage(File file, Long petId) {
    RequestBody requestBody =
        new MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart(
                "file", file.getName(), RequestBody.create(MediaType.parse("image/png"), file))
            .build();
    Response response =
        OK_CLIENT
            .newCall(
                new Request.Builder()
                    .post(requestBody)
                    .url(
                        Objects.requireNonNull(
                            HttpUrl.get(URI.create(URI_PET + "/" + petId + "/uploadImage"))))
                    .build())
            .execute();
    return (long) response.code();
  }

  @SneakyThrows
  @Override
  public Long delete(Long petId) {
    Request request =
        new Request.Builder().url(HttpUrl.get(URI.create(URI_PET + "/" + petId))).delete().build();
    Response response = OK_CLIENT.newCall(request).execute();
    return (long) response.code();
  }

  @SneakyThrows
  public String findPetByStatus(String status) {
    Response response =
        new OkHttpClient()
            .newCall(
                new Request.Builder()
                    .url(HttpUrl.get(URI.create(URI_PET + "/findByStatus?status=" + status)))
                    .get()
                    .build())
            .execute();
    return response.body().string();
  }

  @SneakyThrows
  @Override
  public Pet create(Pet pet) {
    RequestBody requestBody =
        new Request.Builder()
            .url(Objects.requireNonNull(HttpUrl.get(URI.create(URI_PET))))
            .post(
                RequestBody.create(
                    MediaType.parse("application/json; charset=utf-8"), GSON.toJson(pet)))
            .header("Content-type", "application/json")
            .build()
            .body();
    Response response =
        OK_CLIENT
            .newCall(
                new Request.Builder()
                    .url(Objects.requireNonNull(HttpUrl.get(URI.create(URI_PET))))
                    .post(Objects.requireNonNull(requestBody))
                    .build())
            .execute();
    return GSON.fromJson(Objects.requireNonNull(response.body()).string(), Pet.class);
  }

  @SneakyThrows
  @Override
  public Long update(Long id, String petName, String status) {
    FormBody formBody =
        new FormBody.Builder().addEncoded("name", petName).addEncoded("status", status).build();
    Request request =
        new Request.Builder()
            .url(HttpUrl.get(URI.create(URI_PET + "/" + id)))
            .post(formBody)
            .build();
    Response response = OK_CLIENT.newCall(request).execute();
    return (long) response.code();
  }

  @SneakyThrows
  @Override
  public Pet getById(Long petId) {
    Request request =
        new Request.Builder().url(HttpUrl.get(URI.create(URI_PET + "/" + petId))).get().build();
    Response response = OK_CLIENT.newCall(request).execute();
    return GSON.fromJson(response.body().string(), Pet.class);
  }

  @SneakyThrows
  @Override
  public Pet updatePut(Pet pet) {
    RequestBody requestBody =
        new Request.Builder()
            .url(Objects.requireNonNull(HttpUrl.get(URI.create(URI_PET))))
            .put(
                RequestBody.create(
                    MediaType.parse("application/json; charset=utf-8"), GSON.toJson(pet)))
            .build()
            .body();
    Response response =
        OK_CLIENT
            .newCall(
                new Request.Builder()
                    .url(Objects.requireNonNull(HttpUrl.get(URI.create(URI_PET))))
                    .post(Objects.requireNonNull(requestBody))
                    .build())
            .execute();
    return GSON.fromJson(response.body().string(), Pet.class);
  }
}
