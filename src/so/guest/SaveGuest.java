/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.guest;

import domain.GeneralDomainObject;
import domain.Guest;
import domain.Product;
import so.AbstractGenericOperation;

/**
 *
 * @author Katica
 */
public class SaveGuest extends AbstractGenericOperation {

    private GeneralDomainObject guest;
     
    @Override
    protected void preconditions(Object entity) throws Exception {
         if (!(entity instanceof Guest)) {
            throw new Exception("Don't valid");
        }
        }

    @Override
    protected void execute(Object[] entity) throws Exception {
            guest = databaseBroker.create((Guest) entity[0]);
    }

    public GeneralDomainObject getGuest() {
        return guest;
    }
    
}
