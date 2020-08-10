/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.guest;

import domain.GeneralDomainObject;
import domain.Guest;
import domain.Manufacturer;
import java.util.ArrayList;
import java.util.List;
import so.AbstractGenericOperation;

/**
 *
 * @author Katica
 */
public class GetAllGuest extends AbstractGenericOperation {

    List<GeneralDomainObject> guest;

    public GetAllGuest() {
        guest = new ArrayList<>();
    }

    @Override
    protected void preconditions(Object entity) throws Exception {
        if (!(entity instanceof Guest)) {
            throw new Exception("Don't valid");
        }
    }


    public List<GeneralDomainObject> getList(){
        return guest;
}

    @Override
    protected void execute(Object[] entity) throws Exception {
        guest = databaseBroker.getall((Guest) entity[0]);
    }
}