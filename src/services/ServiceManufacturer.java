/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import domain.Manufacturer;
import java.util.ArrayList;

/**
 *
 * @author Katica
 */
public interface ServiceManufacturer {
    
    
    ArrayList<Manufacturer> getAll()throws Exception;
    

}
