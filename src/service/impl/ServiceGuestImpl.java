/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import domain.Guest;
import java.util.List;
import services.ServiceGuest;
import storage.database.DatabaseStorageGuest;
import storage.memory.StorageGuest;
import storage.memory.StorageOrder;

/**
 *
 * @author Katica
 */
public class ServiceGuestImpl implements ServiceGuest{

    private final StorageGuest storageGuest;

    public ServiceGuestImpl() {
        storageGuest = new DatabaseStorageGuest();
    }

    
    @Override
    public Guest saveGuest(Guest guest) throws Exception {
        return storageGuest.saveGuest(guest);
    }

    @Override
    public List<Guest> getAllGuest() throws Exception {
        return storageGuest.getAllGuest();
    }
    
}
