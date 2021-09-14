package org.homework.repository.interfaces;

import org.homework.model.BaseEntity;

public interface Repository<T extends BaseEntity<ID>, ID> {

  T getById(ID id);

  T create(T t);

  ID delete(ID id);
}
