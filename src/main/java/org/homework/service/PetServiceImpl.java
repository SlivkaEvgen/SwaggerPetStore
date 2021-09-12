package org.homework.service;

import lombok.NoArgsConstructor;
import org.homework.model.Category;
import org.homework.model.Pet;
import org.homework.model.Tag;
import org.homework.repository.PetRepositoryImpl;
import org.homework.service.interfaces.PetService;

import java.io.File;
import java.util.List;

@NoArgsConstructor
public class PetServiceImpl implements PetService {

  private final PetRepositoryImpl petRepository = PetRepositoryImpl.getPetRepository();
  private static PetServiceImpl petService;

  public static PetServiceImpl getPetServiceImpl() {
    if (petService == null) {
      petService = new PetServiceImpl();
    }
    return petService;
  }

  @Override
  public String findPetByStatus(String status) {
    return petRepository.findPetByStatus(status);
  }

  @Override
  public Pet create(
      Long id,
      String name,
      String status,
      Category category,
      List<String> images,
      List<Tag> tagList) {
    Pet pet = new Pet();
    pet.setId(id);
    pet.setName(name);
    pet.setCategory(category);
    pet.setTags(tagList);
    pet.setPhotoUrls(images);
    return petRepository.create(pet);
  }

  @Override
  public Long uploadImage(File file, Long petId) {
    return petRepository.uploadImage(file, petId);
  }

  @Override
  public Long update(Long id, String name, String status) {
    Pet pet = new Pet();
    pet.setId(id);
    pet.setName(name);
    return petRepository.update(id, name, status);
  }

  @Override
  public Pet updatePut(
      Long id,
      String name,
      String status,
      Category category,
      List<String> images,
      List<Tag> tagList) {
    Pet pet = new Pet();
    pet.setId(id);
    pet.setName(name);
    pet.setCategory(category);
    pet.setTags(tagList);
    pet.setPhotoUrls(images);
    return petRepository.updatePut(pet);
  }

  @Override
  public Long delete(Long petId) {
    return petRepository.delete(petId);
  }

  @Override
  public Pet findById(Long petId) {
    return petRepository.getById(petId);
  }
}
