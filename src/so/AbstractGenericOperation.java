/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import db.DatabaseBroker;
import db.IDatabaseBroker;
import domain.GeneralDomainObject;
import java.sql.SQLException;
import storage.database.connection.ConnectionFactory;

/**
 *
 * @author Katica
 */
public abstract class AbstractGenericOperation {
     protected IDatabaseBroker databaseBroker;

    public AbstractGenericOperation() {
        databaseBroker = new DatabaseBroker();
    }

    public final void templateExecute(Object[] entity) throws Exception {
        try {
            preconditions(entity[0]);
            startTransaction();
            execute(entity);
            commitTransaction();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            rollbackTransaction();
            throw ex;
        }
    }

    protected abstract void preconditions(Object entity) throws Exception;

    private void startTransaction() throws SQLException {
        ConnectionFactory.getInstance().getConnection().setAutoCommit(false);
    }

    protected abstract void execute(Object[] entity) throws Exception;

    private void commitTransaction() throws SQLException {
        ConnectionFactory.getInstance().getConnection().commit();
    }

    private void rollbackTransaction() throws SQLException {
        ConnectionFactory.getInstance().getConnection().rollback();
    }
}
