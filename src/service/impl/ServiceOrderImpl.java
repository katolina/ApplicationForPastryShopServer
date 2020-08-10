/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import domain.Order;
import domain.OrderItem;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.ServiceOrder;
import storage.database.DatabaseStorageOrder;
import storage.memory.StorageOrder;

/**
 *
 * @author Katica
 */
public class ServiceOrderImpl implements ServiceOrder{
    private final StorageOrder storageOrder;

    public ServiceOrderImpl() {
       storageOrder = new DatabaseStorageOrder();
    }

    @Override
    public Order save(Order order) throws Exception{
        validate(order);
        return storageOrder.saveOrder(order);
        
    }

    private void validate(Order order) throws Exception {
        if(order.getOrderItems().isEmpty()){
            throw new Exception("Object is not valid! You need to have one");
        }
    }

    @Override
    public List<Order> gellAllOrders() throws Exception {
        return storageOrder.getAllOrders();
    }

    @Override
    public List<OrderItem> getOrderItems() throws Exception{
        return storageOrder.getOrderItems();
    }

   

   
    
}
