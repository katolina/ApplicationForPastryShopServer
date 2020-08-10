/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage.database;

import domain.Invoice;
import domain.InvoiceItem;
import domain.Order;
import domain.OrderItem;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import storage.database.connection.ConnectionFactory;
import storage.memory.StorageInvoice;

/**
 *
 * @author Katica
 */
public class DatabaseStorageInvoice implements StorageInvoice{

    

    @Override
    public Invoice saveInvoice(Invoice invoice) throws SQLException, Exception {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        String query = "insert into invoice(amount) values (?)";
        PreparedStatement ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
        ps.setDouble(1, invoice.getAmount());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if(rs.next()){
            invoice.setId(rs.getInt(1));
        }
        else{
            throw new Exception();
        }
        
        
        query = "insert into invoice_item(invoice_id,item_number,price,quantity,amount,product_id) values(?,?,?,?,?,?)";

        ps = connection.prepareStatement(query);
        for(InvoiceItem ii:invoice.getItems()){
            ps.setInt(1, ii.getInvoice().getId());
            ps.setInt(2, ii.getItemNumber());
            ps.setDouble(3, ii.getPrice());
            ps.setInt(4, ii.getQuantity());
            ps.setDouble(5, ii.getAmount());
            ps.setInt(6, ii.getProduct().getID());
            ps.executeUpdate();
    
        }
        connection.commit();
        ps.close();
        return invoice;
    }

    
    
}
