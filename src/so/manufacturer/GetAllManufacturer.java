/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.manufacturer;

import domain.GeneralDomainObject;

import domain.Manufacturer;
import java.util.ArrayList;
import java.util.List;
import so.AbstractGenericOperation;

/**
 *
 * @author Katica
 */
public class GetAllManufacturer extends AbstractGenericOperation{
   
    private List<GeneralDomainObject> object;

   
    public GetAllManufacturer() {
    object = new ArrayList<>();
    }
    
    
    @Override
    protected void preconditions(Object entity) throws Exception {
    if(!(entity instanceof Manufacturer)){
            throw new Exception("Don't valid");
        }
    }
    

    @Override
    protected void execute(Object[] entity) throws Exception {
        object = databaseBroker.getall((Manufacturer) entity[0]);
    }
    
     public List<GeneralDomainObject> getObject() {
        return object;
    }

    
}
