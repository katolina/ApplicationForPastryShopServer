/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import domain.Product;
import java.util.List;
import services.ServiceProduct;
import storage.database.DatabaseStorageProduct;
import storage.memory.StorageProduct;

/**
 *
 * @author Katica
 */
public class ServiceProductImpl implements ServiceProduct{
    private final StorageProduct storageProduct;
    
    
     public ServiceProductImpl() {
        storageProduct=new DatabaseStorageProduct();
    }
    
    @Override
    public Product save(Product product) throws Exception {
        return storageProduct.save(product);
    }

    @Override
    public List<Product> getAll() throws Exception{
        return storageProduct.getAll();
    }

    @Override
    public boolean delete(Product product) {
        return storageProduct.delete(product);
    }
    
}
