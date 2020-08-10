/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import domain.Manufacturer;
import java.util.ArrayList;
import services.ServiceManufacturer;
import storage.memory.StorageManufacturer;
import storage.database.DatabaseStorageManufacturer;

/**
 *
 * @author Katica
 */
public class ServiceManufacturerImpl implements ServiceManufacturer{
private final StorageManufacturer storageManufacturer;

    public ServiceManufacturerImpl() {
        storageManufacturer=new DatabaseStorageManufacturer();
    }
    @Override
    public ArrayList<Manufacturer> getAll() throws Exception {
    return (ArrayList<Manufacturer>) storageManufacturer.getAll();
    }
    
}
