package org.homework.repository.interfaces;

public interface Repository<T, ID> {

  T getById(ID id);

  T create(T t);

  ID delete(ID id);
}
