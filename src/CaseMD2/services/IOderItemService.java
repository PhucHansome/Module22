package CaseMD2.services;

import CaseMD2.model.OrderItem;

import java.util.List;

public interface IOderItemService {
    List<OrderItem> getOrderItems();
    void add(OrderItem newOrderItem);
    void update();
    OrderItem getOrderItemById(int id);
}
