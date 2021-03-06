/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.invoice;

import domain.GeneralDomainObject;
import domain.Guest;
import domain.Invoice;
import domain.InvoiceItem;
import domain.Product;
import so.AbstractGenericOperation;

/**
 *
 * @author Katica
 */
public class SaveInvoice extends AbstractGenericOperation {

    private GeneralDomainObject object;
    
    @Override
    protected void preconditions(Object entity) throws Exception {
        if(!(entity instanceof Invoice)){
            throw new Exception("Don't valid");
        }
    }

    @Override
    protected void execute(Object[] entity) throws Exception {
        object = databaseBroker.create((Invoice) entity[0]);
        Invoice invoice =  (Invoice) object;
        System.out.println("invoice" + invoice.getAmount()+ " ");
        for (InvoiceItem item : invoice.getItems()) {
            System.out.println("item "+item.getPrice());
            databaseBroker.create(item);
        }
     
    }

    public GeneralDomainObject getObject() {
        return object;
    }

    
    
    
}
