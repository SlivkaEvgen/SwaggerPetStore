package org.example.repository;import com.google.gson.Gson;import lombok.SneakyThrows;import okhttp3.*;import org.example.config.HttpConnect;import org.example.model.Pet;import org.example.repository.interfaces.PetRepository;import org.example.util.PropertiesLoader;import java.io.File;import java.net.URI;import java.util.Objects;public class PetRepositoryImpl implements PetRepository<Pet, Long> {  private final OkHttpClient OK_CLIENT = HttpConnect.getInstance();  private final String URI_PET = PropertiesLoader.getProperties("uriPet");  private final Gson GSON = new Gson();  @SneakyThrows  @Override  public Long uploadImage(File file, Long petId) {    RequestBody requestBody =        new MultipartBody.Builder()            .setType(MultipartBody.FORM)            .addFormDataPart(                "file", file.getName(), RequestBody.create(MediaType.parse("image/png"), file))            .build();    Response response =        OK_CLIENT            .newCall(                new Request.Builder()                    .post(requestBody)                    .url(                        Objects.requireNonNull(                            HttpUrl.get(URI.create(URI_PET + "/" + petId + "/uploadImage"))))                    .build())            .execute();    System.out.println("uploadFile $  " + response.body().string());    return Long.valueOf(response.code());  }  @SneakyThrows  @Override  public Long delete(Long petId) {    Request request =        new Request.Builder().url(HttpUrl.get(URI.create(URI_PET + "/" + petId))).delete().build();    Response response = OK_CLIENT.newCall(request).execute();    return Long.valueOf(response.code());  }  @SneakyThrows  public String findPetByStatus(String status) {    Response response =        new OkHttpClient()            .newCall(                new Request.Builder()                    .url(HttpUrl.get(URI.create(URI_PET + "/findByStatus?status=" + status)))                    .get()                    .build())            .execute();    //  return GSON.fromJson(response.body().string(), (Type) Pet.class);    // System.out.println(response.body().string());    String string = response.body().string();    return string;  }  @SneakyThrows  @Override  public Pet create(Pet pet) {    RequestBody requestBody =        new Request.Builder()            .url(Objects.requireNonNull(HttpUrl.get(URI.create(URI_PET))))            .post(                RequestBody.create(                    MediaType.parse("application/json; charset=utf-8"), GSON.toJson(pet)))            .header("Content-type", "application/json")            .build()            .body();    Response response =        OK_CLIENT            .newCall(                new Request.Builder()                    .url(Objects.requireNonNull(HttpUrl.get(URI.create(URI_PET))))                    .post(Objects.requireNonNull(requestBody))                    .build())            .execute();    return GSON.fromJson(Objects.requireNonNull(response.body()).string(), Pet.class);  }  @SneakyThrows  @Override  public Long update(Long id, String petName, String status) {    FormBody formBody =        new FormBody.Builder()            .addEncoded("id", String.valueOf(id))            .addEncoded("name", petName)            .addEncoded("status", status)            .build();    Request request =        new Request.Builder()            .url(HttpUrl.get(URI.create(URI_PET + "/" + id)))            .post(formBody)            .build();    Response response = OK_CLIENT.newCall(request).execute();    return Long.valueOf(response.code());  }  @SneakyThrows  @Override  public Pet get(Long petId) {    Request request =        new Request.Builder().url(HttpUrl.get(URI.create(URI_PET + "/" + petId))).get().build();    Response response = OK_CLIENT.newCall(request).execute();    return GSON.fromJson(response.body().string(), Pet.class);  }  @SneakyThrows  @Override  public Long updatePut(Pet pet) {    RequestBody requestBody =        new Request.Builder()            .url(Objects.requireNonNull(HttpUrl.get(URI.create(URI_PET))))            .put(                RequestBody.create(                    MediaType.parse("application/json; charset=utf-8"), GSON.toJson(pet)))            .build()            .body();    Response response =        OK_CLIENT            .newCall(                new Request.Builder()                    .url(Objects.requireNonNull(HttpUrl.get(URI.create(URI_PET))))                    .post(Objects.requireNonNull(requestBody))                    .build())            .execute();    return Long.valueOf(response.code());  }}