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
public class DeleteProduct extends AbstractGenericOperation {

    private boolean deleted;

    @Override
    protected void preconditions(Object entity) throws Exception {
        if (!(entity instanceof Product)) {
            throw new Exception("Don't valid");
        }
    }

    @Override
    protected void execute(Object[] entity) throws Exception {
        Product product = (Product) entity[0];
        deleted = databaseBroker.delete((Product) entity[0]);
    }

    public boolean isDeleted() {
        return deleted;
    }
}
