/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.product;

import domain.GeneralDomainObject;
import domain.Product;
import so.AbstractGenericOperation;

/**
 *
 * @author Katica
 */
//public class UpdateProduct extends AbstractGenericOperation{
//
//    @Override
//    protected void preconditions(Object entity) throws Exception {
//        if(!(entity instanceof Product)){
//            throw new Exception("Don't valid");
//        }
//    }
//    
//
//    @Override
//    protected void executeOperation(Object entity) throws Exception {
//        Product p = (Product) entity;
//        Product product = new Product(p.getID(), p.getName(), p.getQuantity(), p.getMeasurementUnit(), p.getManufacturer(),p.getPriceWithVat(), p.getPriceWithoutVAT());
//        
//        databaseBroker.update((GeneralDomainObject) entity);
//        databaseBroker.update((GeneralDomainObject) product);
//    }
//
//  
//}
