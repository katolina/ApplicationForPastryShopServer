/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage.database;

import domain.Guest;
import domain.Manufacturer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import storage.database.connection.ConnectionFactory;
import storage.memory.StorageGuest;

/**
 *
 * @author Katica
 */
public class DatabaseStorageGuest implements StorageGuest{

    @Override
    public Guest saveGuest(Guest guest) throws Exception {
         Connection connection=ConnectionFactory.getInstance().getConnection();
        String query="insert into guest (firstName,lastName,email,telephone) values ( ?,?, ?, ?)";
        PreparedStatement preparedStatement=connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, guest.getFirstName());
        preparedStatement.setString(2, guest.getLastName());
        preparedStatement.setString(3, guest.getEmail());
        preparedStatement.setString(4, guest.getTelephone());
       
        preparedStatement.executeUpdate();
        ResultSet rs=preparedStatement.getGeneratedKeys();
        if(rs.next()){
            guest.setId(rs.getInt(1));
        }
        connection.commit();
        preparedStatement.close();
        return guest;
    }

    @Override
    public List<Guest> getAllGuest() throws Exception {
          try{
        List<Guest> guests=new ArrayList();
            Connection connection=ConnectionFactory.getInstance().getConnection();
            String query="select * from guest order by firstName";
            Statement statement=connection.createStatement();
            ResultSet rs=statement.executeQuery(query);
            while(rs.next()){
                int id=rs.getInt("id");
                String firstName=rs.getString("firstName");
                String lastName=rs.getString("lastName");
                String email=rs.getString("email");
                String telephone=rs.getString("telephone");
               guests.add(new Guest(id, firstName, lastName, email, telephone));
            }
            rs.close();
            statement.close();
            return guests;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Error in connection");
        }
    }
    
}
