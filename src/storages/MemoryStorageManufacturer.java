/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storages;

import domain.Manufacturer;
import java.util.ArrayList;
import java.util.List;
import storage.memory.StorageManufacturer;

/**
 *
 * @author Katica
 */
public class MemoryStorageManufacturer implements StorageManufacturer{
    private final List<Manufacturer> manufacturers;

    public MemoryStorageManufacturer() {
        manufacturers = new ArrayList() {
            {
                add(new Manufacturer(1, "Manufacturer / 1"));
                add(new Manufacturer(2, "Manufacturer / 2"));
                add(new Manufacturer(3, "Manufacturer / 3"));
                add(new Manufacturer(4, "Manufacturer / 4"));
            }
        };
    }
    @Override
    public List<Manufacturer> getAll() throws Exception{
        return manufacturers;
    }
}
