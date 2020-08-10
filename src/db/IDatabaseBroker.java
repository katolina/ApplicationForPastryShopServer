/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domain.GeneralDomainObject;
import java.util.ArrayList;

/**
 *
 * @author Katica
 */
public interface IDatabaseBroker {
    public GeneralDomainObject findById(GeneralDomainObject gdo) throws Exception;
    public ArrayList<GeneralDomainObject> getall(GeneralDomainObject gdo) throws  Exception;
    GeneralDomainObject create(GeneralDomainObject entity) throws Exception;
    boolean update(GeneralDomainObject entity);
    boolean delete(GeneralDomainObject entity);
}
