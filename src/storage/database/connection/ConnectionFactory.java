/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage.database.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Katica
 */
public class ConnectionFactory {
    public static ConnectionFactory instance;
    private final Connection connection;
    
    private ConnectionFactory() throws SQLException{
       try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("property.properties"));
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            throw new SQLException("Connection is not created!");
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
    
    public static ConnectionFactory getInstance() throws SQLException {
    if(instance == null){
        instance = new ConnectionFactory();
        
    }
        return instance;
    }
    public Connection getConnection(){
        return connection;
    }
   
    
    
    
}
