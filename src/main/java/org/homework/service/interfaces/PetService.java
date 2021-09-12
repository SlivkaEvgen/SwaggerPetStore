package org.homework.service.interfaces;

import org.homework.model.Category;
import org.homework.model.Pet;
import org.homework.model.Tag;

import java.io.File;
import java.util.List;

public interface PetService extends Service<Pet, Long> {

  String findPetByStatus(String status);

  Pet create(
      Long id,
      String name,
      String status,
      Category category,
      List<String> images,
      List<Tag> tagList);

  Long uploadImage(File file, Long petId);

  Long update(Long id, String name, String status);

  Pet updatePut(
      Long id,
      String name,
      String status,
      Category category,
      List<String> images,
      List<Tag> tagList);
}
