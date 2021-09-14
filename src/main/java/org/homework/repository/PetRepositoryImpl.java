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

@NoArgsConstructor
public class PetRepositoryImpl extends Parser<Pet, Response, Request> implements PetRepository {

  private final OkHttpClient OK_CLIENT = HttpConnect.getInstance();
  private final String URI_PET = PropertiesLoader.getProperties("uriPet");
  private final Gson GSON = new Gson();
  private static PetRepositoryImpl petRepository;
  private final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");

  public static PetRepositoryImpl getPetRepository() {
    if (petRepository == null) {
      petRepository = new PetRepositoryImpl();
    }
    return petRepository;
  }

  @SneakyThrows
  @Override
  public Long uploadImage(File file, Long petId) {
    return (long)
        getResponse(
                getRequestBuilder(petId + "/uploadImage")
                    .post(
                        new MultipartBody.Builder()
                            .setType(MultipartBody.FORM)
                            .addFormDataPart(
                                "file",
                                file.getName(),
                                RequestBody.create(MediaType.parse("image/png"), file))
                            .build())
                    .url(getUrl("/" + petId + "/uploadImage"))
                    .build())
            .code();
  }

  @SneakyThrows
  @Override
  public Long delete(Long petId) {
    return (long) getResponse(getRequestBuilder("/" + petId).delete().build()).code();
  }

  @SneakyThrows
  public String findPetByStatus(String status) {
    return getResponse(getRequestBuilder("/findByStatus?status=" + status).get().build())
        .body()
        .string();
  }

  @SneakyThrows
  @Override
  public Pet create(Pet pet) {
    return gsonWithBody(
        getResponse(
            getRequestBuilder("").post(RequestBody.create(MEDIA_TYPE, GSON.toJson(pet))).build()));
  }

  @SneakyThrows
  @Override
  public Long update(Long id, String petName, String status) {
    return (long)
        getResponse(
                getRequestBuilder("/" + id)
                    .post(
                        new FormBody.Builder()
                            .addEncoded("name", petName)
                            .addEncoded("status", status)
                            .build())
                    .build())
            .code();
  }

  @SneakyThrows
  @Override
  public Pet getById(Long petId) {
    return gsonGet("/" + petId);
  }

  @SneakyThrows
  @Override
  public Pet updatePut(Pet pet) {
    return gsonWithBody(
        getResponse(
            getRequestBuilder("").post(RequestBody.create(MEDIA_TYPE, GSON.toJson(pet))).build()));
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
    return HttpUrl.get(URI.create(URI_PET + urlUrl));
  }

  @SneakyThrows
  @Override
  protected Pet gsonGet(String urls) {
    return GSON.fromJson(getResponse(getRequestBuilder(urls).build()).body().string(), Pet.class);
  }

  @SneakyThrows
  @Override
  protected Pet gsonWithBody(Response response) {
    return GSON.fromJson(response.body().string(), Pet.class);
  }
}
