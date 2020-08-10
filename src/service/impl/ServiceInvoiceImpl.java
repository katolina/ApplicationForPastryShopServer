/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import domain.Invoice;
import domain.Order;
import domain.OrderItem;
import services.ServiceInvoice;
import storage.database.DatabaseStorageInvoice;
import storage.memory.StorageInvoice;

/**
 *
 * @author Katica
 */
public class ServiceInvoiceImpl implements ServiceInvoice{

    private final StorageInvoice storageInvoice;

    public ServiceInvoiceImpl() {
        storageInvoice = new DatabaseStorageInvoice();
    }

    @Override
    public Invoice saveInvoice(Invoice invoice) throws Exception {
        return storageInvoice.saveInvoice(invoice);
    }
    
   
    
}
