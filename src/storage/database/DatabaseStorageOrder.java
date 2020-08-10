/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage.database;

import domain.Guest;
import domain.Manufacturer;
import domain.MeasurementUnit;
import domain.Order;
import domain.OrderItem;
import domain.Product;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.naming.spi.DirStateFactory;
import storage.database.connection.ConnectionFactory;
import storage.memory.StorageOrder;

/**
 *
 * @author Katica
 */
public class DatabaseStorageOrder implements StorageOrder {

    @Override
    public Order saveOrder(Order order) throws Exception {
        
        Connection connection = ConnectionFactory.getInstance().getConnection();
        String query = "insert into order_order(date_order,guest_id) values (?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setDate(1, new Date(order.getOrderDate().getTime()));
        preparedStatement.setInt(2, order.getGuest().getId());

        preparedStatement.executeUpdate();

        ResultSet rs = preparedStatement.getGeneratedKeys();

        if (rs.next()) {

            order.setId(rs.getInt(1));

        } else {
            throw new Exception("Nothing");
        }

        query = "insert into order_item(order_id,numberOrder,quantity,product_id) values (?,?,?,?)";

        preparedStatement = connection.prepareStatement(query);
        for (OrderItem item : order.getOrderItems()) {
           
            preparedStatement.setInt(1, order.getId());
            preparedStatement.setInt(2, item.getNumberOrderItem());
            preparedStatement.setInt(3, item.getQuantityOrder());
            preparedStatement.setInt(4, item.getProduct().getID());

            preparedStatement.executeUpdate();

        }
        connection.commit();
        preparedStatement.close();
        return order;

    }

    @Override
    public List<Order> getAllOrders() throws Exception {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        String query = "SELECT o.order_id, o.date_order,guest_id,oi.id,oi.numberOrder,oi.quantity,oi.product_id,p.name,p.quantity as product_quantity,p.measurement_unit,p.priceWithVat,p.manufacturer_id,m.name as m_name FROM order_order o JOIN order_item oi ON o.order_id = oi.order_id JOIN product p ON p.product_id = oi.product_id JOIN manufacturer m ON p.manufacturer_id = m.manufacturer_id ";
        Statement s = connection.createStatement();
        List<Order> orders = new ArrayList<>();
        ResultSet rs = s.executeQuery(query);
        while (rs.next()) {
            int id = rs.getInt("order_id");
            Date date = rs.getDate("date_order");
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
            int guestID = rs.getInt("guest_id");
            int idId = rs.getInt("id");
            int numberOrder = rs.getInt("numberOrder");
            int quantity = rs.getInt("quantity");
            int productId = rs.getInt("product_id");
            String productName = rs.getString("name");
            int quanti = rs.getInt("product_quantity");
            String mu = rs.getString("measurement_unit");
            int mId = rs.getInt("manufacturer_id");
            String mName = rs.getString("m_name");
            Double price = rs.getDouble("priceWithVat");

            Order order = new Order(id, date,new Guest(guestID, "Nesto", "nesto", "nesto", "nesto"));
            Manufacturer manufacturer = new Manufacturer(mId, mName);
            Product product = new Product(id, productName, quantity, MeasurementUnit.PCS, manufacturer,0, price);
            OrderItem orderItem = new OrderItem(order,numberOrder, quantity, product);
            ArrayList<OrderItem> orderItems = new ArrayList<>();
            orderItems.add(orderItem);
            order.setOrderItems(orderItems);
            orders.add(order);
        }
        return orders;
    }

//    @Override
//     public List<OrderItem> getOrderItems() throws Exception{
//         List<OrderItem> orderItems = new ArrayList<>();
//         Connection connection = ConnectionFactory.getInstance().getConnection();
//         String query = "select oi.order_id,oi.numberOrder,oi.quantity,oi.product_id, p.name,p.quantity as quantityProduct,p.measurement_unit,p.priceWithVat,p.manufacturer_id,m.name as mName from order_item oi join product p on oi.product_id = p.product_id join manufacturer m on p.manufacturer_id=m.manufacturer_id";
//         Statement s = connection.createStatement();
//         ResultSet rs = s.executeQuery(query);
//         while(rs.next()){
//             int id = rs.getInt("order_id");
//             int number = rs.getInt("numberOrder");
//             int quantity = rs.getInt("quantity");
//             int productId = rs.getInt("product_id");
//             String name = rs.getString("name");
//             int quantit = rs.getInt("quantityProduct");
//             double price = rs.getDouble("priceWithVat");
//             String muu = rs.getString("measurement_unit");
//             MeasurementUnit mu = MeasurementUnit.valueOf(muu);
//             int manufacturerId = rs.getInt("manufacturer_id");
//             String manufacturere = rs.getString("mName");
//             
//             Manufacturer manufacturer = new Manufacturer(manufacturerId,manufacturere);
//             Product product = new Product(id, name, quantit, mu, manufacturer,0,price);
//             
//             OrderItem orderItem = new OrderItem(number, quantity, product);
//             orderItems.add(orderItem);
//         }
//        return orderItems;
//     }

    @Override
    public List<OrderItem> getOrderItems() throws Exception {
        System.out.println("Nista");
        return null;
    }
    
}
