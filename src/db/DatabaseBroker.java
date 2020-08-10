/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domain.GeneralDomainObject;
import domain.Invoice;

import domain.Manufacturer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import storage.database.connection.ConnectionFactory;

/**
 *
 * @author Katica
 */
public class DatabaseBroker implements IDatabaseBroker {

    private Connection connection;

    @Override
    public GeneralDomainObject findById(GeneralDomainObject gdo) throws Exception {
        connection = ConnectionFactory.getInstance().getConnection();
        String query = "SELECT * FROM " + gdo.getClassName()
                + gdo.getJoinCondition()
                + gdo.getWhereCondition();
        System.out.println(query);
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);

        while (rs.next()) {
            return gdo.getNewObj(rs);
        }
        return null;
    }

    @Override
    public ArrayList<GeneralDomainObject> getall(GeneralDomainObject gdo) throws Exception {
        connection = ConnectionFactory.getInstance().getConnection();
        String query = "SELECT * FROM " + gdo.getClassName()
                + gdo.getJoinCondition()
                + gdo.getWhereCondition();
        System.out.println(query);
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);

        ArrayList<GeneralDomainObject> list = new ArrayList<>();
        while (rs.next()) {
            list.add(gdo.getNewObj(rs));
        }
        return list;
    }

    @Override
    public GeneralDomainObject create(GeneralDomainObject entity) throws Exception {
        try {
            connection = ConnectionFactory.getInstance().getConnection();
            
            String query = "INSERT into " + entity.getClassName()
                    + entity.getColumnNames()
                    + " values (" + entity.getAttributes() + ")";

            System.out.println(query);
   

            PreparedStatement ps = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
           int count = ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (count > 0 && rs.next() != false) {
                entity.setPrimaryKey(rs);
            }
           
            return entity;

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBroker.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        throw new Exception();
    }

    @Override
    public boolean update(GeneralDomainObject entity) {
        try {
            connection = ConnectionFactory.getInstance().getConnection();
            String query = "UPDATE " + entity.getClassName()
                    + " SET " + entity.setAttributes()
                    + entity.getWhereCondition();
            System.out.println(query);
            PreparedStatement ps = connection.prepareStatement(query);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(GeneralDomainObject entity) {
        try {
            connection = ConnectionFactory.getInstance().getConnection();
            String query = "DELETE from " + entity.getClassName()
                    + entity.getWhereCondition();
            System.out.println(query);
            executeUpdate(query);
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBroker.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean executeUpdate(String query) {
        PreparedStatement ps = null;
        boolean successful = false;
        try {

            ps = connection.prepareStatement(query);
            int count = ps.executeUpdate(query);

            if (count > 0) {
                successful = true;
            }
        } catch (SQLException ex) {
        }
        return successful;
    }

}
