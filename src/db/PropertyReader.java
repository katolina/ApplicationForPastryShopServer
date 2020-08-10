/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Korisnik
 */
public class PropertyReader {
    Properties properties;

    public PropertyReader() {
        properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream("property.properties");
            properties.load(fis);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
           
        }
    }
    public String readForKey(String key){
        return properties.getProperty(key);
    }
    
}
