/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage.memory;

import domain.Order;
import domain.OrderItem;
import java.util.List;

/**
 *
 * @author Katica
 */
public interface StorageOrder {
    Order saveOrder(Order order) throws Exception;

    public List<Order> getAllOrders() throws Exception;

    public List<OrderItem> getOrderItems() throws Exception;
}
