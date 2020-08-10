/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import domain.Order;
import domain.OrderItem;
import java.util.List;

/**
 *
 * @author Katica
 */
public interface ServiceOrder {

    
    public Order save(Order order) throws Exception;

    public List<Order> gellAllOrders() throws Exception;

    public List<OrderItem> getOrderItems() throws Exception;
    //public  Order save(Order order) throws Exception;
}
