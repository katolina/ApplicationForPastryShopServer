/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.order;

import domain.GeneralDomainObject;
import domain.Invoice;
import domain.InvoiceItem;
import domain.Order;
import domain.OrderItem;
import so.AbstractGenericOperation;

/**
 *
 * @author Katica
 */
public class SaveOrder extends AbstractGenericOperation{

    private GeneralDomainObject object;
    
    @Override
    protected void preconditions(Object entity) throws Exception {
       if (!(entity instanceof Order)) {
            throw new Exception("Don't valid.");
        }
    }

    @Override
    protected void execute(Object[] entity) throws Exception {
         object = databaseBroker.create((Order) entity[0]);
        Order order = (Order) entity[0];
        for (OrderItem item : order.getOrderItems()) {
            databaseBroker.create(item);
        }
    }

    public GeneralDomainObject getObject() {
        return object;
    }
    
    

    
}
