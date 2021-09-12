package org.homework.service.interfaces;

public interface Service<T, ID> {

  ID delete(ID id);

  T findById(ID petId);
}
