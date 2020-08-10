/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage.memory;

import domain.Invoice;
import domain.Order;
import domain.OrderItem;

/**
 *
 * @author Katica
 */
public interface StorageInvoice {
   

    public Invoice saveInvoice(Invoice invoice) throws Exception;
}
