/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.product;

import domain.GeneralDomainObject;
import domain.Guest;
import domain.Product;
import so.AbstractGenericOperation;

/**
 *
 * @author Katica
 */
public class SaveProduct extends AbstractGenericOperation {

    private GeneralDomainObject object;

    @Override
    protected void preconditions(Object entity) throws Exception {
        if (!(entity instanceof Product)) {
            throw new Exception("Don't valid");
        }
    }

    @Override
    protected void execute(Object[] entity) throws Exception {
       
            object = databaseBroker.create((Product) entity[0]);
       
            
        
    }
    
    public GeneralDomainObject getObject() {
        return object;
    }


}
