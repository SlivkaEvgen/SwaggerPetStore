package org.homework.repository.interfaces;

import org.homework.model.Order;

public interface StoreRepository extends Repository<Order, Long> {

  String getInventory();
}
