/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage.memory;

import domain.Product;
import java.util.List;

/**
 *
 * @author Katica
 */
public interface StorageProduct {
     Product save(Product product)throws Exception;

    public List<Product> getAll() throws Exception;

    public boolean delete(Product product);
}
