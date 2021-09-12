package org.homework.service.interfaces;

import org.homework.model.Order;

public interface StoreService extends Service<Order, Long> {

  void returnsPetInventoriesByStatus();

  Order placeAnOrderForAPet(Long orderId, Long petId, Integer quantity, String status);
}
