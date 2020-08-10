/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import constant.Constants;
import domain.GeneralDomainObject;
import domain.Manufacturer;
import domain.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Korisnik
 */
public class DBBroker implements IDatabaseBroker{
    Connection connection;
    PropertyReader propertyReader;

    public DBBroker() {
        propertyReader = new PropertyReader();
    }

  public void loadDriver() throws ClassNotFoundException{
      Class.forName(propertyReader.readForKey(Constants.DRIVER));
  }
  
  public void openConnection() throws SQLException{
      connection = DriverManager.getConnection(propertyReader.readForKey(Constants.URL),propertyReader.readForKey(Constants.USERNAME),propertyReader.readForKey(Constants.PASSWORD));
      connection.setAutoCommit(false);
           
  }
   
  public void closeConnection() throws SQLException{
      connection.close();
}
  
  public void commit() throws SQLException{
      connection.commit();
  }
  
  public void rollback() throws SQLException{
      connection.rollback();
  }

    public List<Manufacturer> getAllManufacturer() {
        List<Manufacturer> manufacturers = new ArrayList<>();
        
        
        try {
             String query = "select * from manufacturer";
             Statement statement = connection.createStatement();
           
             ResultSet rs = statement.executeQuery(query);
             while(rs.next()){
                 int id = rs.getInt("manufacturer_id");
                 String name = rs.getString("name");
                 
                 Manufacturer manufacturer = new Manufacturer(id, name);
                 manufacturers.add(manufacturer);
                 
             }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
       return manufacturers;
    }



    public List<User> getAllUsers() {
         List<User> users = new ArrayList<>();
        try {
           
            String query = "select * from user";
            Statement statement = connection.createStatement();
            
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                int id = rs.getInt("user_id");
                String username = rs.getString("username");
                String pass = rs.getString("password");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String email = rs.getString("email");
                
                User user = new User(id, username, pass, firstName, lastName, email);
                users.add(user);
            }
            
           
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
         
            return users;
    }

    @Override
    public GeneralDomainObject findById(GeneralDomainObject gdo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<GeneralDomainObject> getall(GeneralDomainObject gdo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GeneralDomainObject create(GeneralDomainObject entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(GeneralDomainObject entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(GeneralDomainObject entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
