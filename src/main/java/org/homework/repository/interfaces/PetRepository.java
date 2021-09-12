package org.homework.repository.interfaces;

import org.homework.model.Pet;

import java.io.File;

public interface PetRepository extends Repository<Pet, Long> {

  Long uploadImage(File file, Long petId);

  String findPetByStatus(String status);

  Long update(Long id, String petName, String status);

  Pet updatePut(Pet pet);
}
