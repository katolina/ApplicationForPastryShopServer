/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage.database;

import domain.Manufacturer;
import domain.MeasurementUnit;
import domain.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.ParseConversionEvent;
import storage.database.connection.ConnectionFactory;
import storage.memory.StorageProduct;

/**
 *
 * @author Katica
 */
public class DatabaseStorageProduct implements StorageProduct{

    @Override
    public Product save(Product product) throws Exception {
         Connection connection=ConnectionFactory.getInstance().getConnection();
        String query="insert into product (name,quantity,measurement_unit,priceWithVat,priceWithoutVat,manufacturer_id) values (?, ?, ?, ?, ?,?)";
        PreparedStatement preparedStatement=connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, product.getName());
        preparedStatement.setInt(2, product.getQuantity());
        preparedStatement.setString(3, product.getMeasurementUnit().toString());
        preparedStatement.setDouble(4, product.getPriceWithVat());
        preparedStatement.setDouble(5,product.getPriceWithoutVAT());
        preparedStatement.setInt(6, product.getManufacturer().getId());
        preparedStatement.executeUpdate();
        ResultSet rs=preparedStatement.getGeneratedKeys();
        if(rs.next()){
            product.setID(rs.getInt(1));
        }
        connection.commit();
        preparedStatement.close();
        return product;
    }

    @Override
    public List<Product> getAll() throws Exception {
        Connection conection = ConnectionFactory.getInstance().getConnection();
        String query = "select p.product_id,p.name,p.quantity,p.measurement_unit,p.priceWithVat,p.priceWithoutVat,m.manufacturer_id as manufacturer_id,m.name as manufacturer_name from product p join manufacturer m on p.manufacturer_id=m.manufacturer_id order by p.name";
        List<Product> products = new ArrayList<>();
        PreparedStatement ps = conection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int id = rs.getInt("product_id");
            String name = rs.getString("name");
            int quantity = rs.getInt("quantity");
            String mu = rs.getString("measurement_unit");
            MeasurementUnit measure = MeasurementUnit.valueOf(mu);
            double price = rs.getDouble("priceWithVat");
            double priceWithout = rs.getDouble("priceWithoutVat");
            String mName = rs.getString("manufacturer_name");
            int mID = rs.getInt("manufacturer_id");
            
            Manufacturer manu = new Manufacturer(mID, mName);
            Product prod = new Product(id, name, quantity, measure, manu,priceWithout, price);
            products.add(prod);
        }
        return products;
    }

    @Override
    public boolean delete(Product product) {
        try {
            Connection connection=ConnectionFactory.getInstance().getConnection();
            String query = "delete from product where product_id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, product.getID());
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }
    
    
}
