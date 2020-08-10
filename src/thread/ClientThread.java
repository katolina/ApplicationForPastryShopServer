/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import controller.Controller;
import db.DatabaseBroker;
import domain.GeneralDomainObject;
import domain.Guest;
import domain.Invoice;
import domain.Manufacturer;
import domain.Order;
import domain.OrderItem;
import domain.Product;
import domain.User;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import service.impl.ServiceGuestImpl;
import service.impl.ServiceInvoiceImpl;
import service.impl.ServiceManufacturerImpl;
import service.impl.ServiceOrderImpl;
import service.impl.ServiceProductImpl;
import service.impl.ServiceUserImpl;
import services.ServiceGuest;
import services.ServiceInvoice;
import services.ServiceManufacturer;
import services.ServiceOrder;
import services.ServiceProduct;
import services.ServiceUser;
import transfer.RequestObject;
import transfer.ResponseObject;
import util.Operation;
import util.ResponseStatus;

/**
 *
 * @author Katica
 */
public class ClientThread extends Thread {

    private final Socket socket;
    private final ObjectInputStream objectInputStream;
    private final ObjectOutputStream objectOutputStream;

    private final ServiceUser serviceUser;
    private final ServiceManufacturer serviceManufacturer;
    private final ServiceProduct serviceProduct;
    private final ServiceOrder serviceOrder;
    private final ServiceInvoice serviceInvoice;
    private final ServiceGuest serviceGuest;

    private User loginUser;

    public ClientThread(Socket socket) throws IOException {
        this.socket = socket;
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        serviceUser = new ServiceUserImpl();
        serviceManufacturer = new ServiceManufacturerImpl();
        serviceProduct = new ServiceProductImpl();
        serviceOrder = new ServiceOrderImpl();
        serviceInvoice = new ServiceInvoiceImpl();
        serviceGuest = new ServiceGuestImpl();
    }

    @Override
    public void run() {
        while (!socket.isClosed()) {
            try {
                RequestObject requestObject = (RequestObject) objectInputStream.readObject();
                ResponseObject responseObject = handleRequest(requestObject);
                objectOutputStream.writeObject(responseObject);
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private ResponseObject handleRequest(RequestObject requestObject) {
        int operation = requestObject.getOperation();
        switch (operation) {
            case Operation.OPERATION_LOGIN:
                return login((Map) requestObject.getData());
            case Operation.OPERATION_GET_ALL_MANUFACTURERS:
                return getAllManufactures();
            case Operation.OPERATION_SAVE_PRODUCT:
                return saveProduct((Product) requestObject.getData());
            case Operation.OPERATION_GET_ALL_PRODUCTS:
                return getAllProducts();
            case Operation.OPERATION_SAVE_ORDER:
                return saveOrder((Order) requestObject.getData());
            case Operation.OPERATION_SAVE_INVOICE:
                return saveInvoice((Invoice) requestObject.getData());
            case Operation.OPERATION_GET_ALL_ORDERS:
                return gellAllOrders();
            case Operation.OPERATION_DELETE_PRODUCT:
                return deleteProduct((Product) requestObject.getData());
            case Operation.OPERATION_SAVE_GUEST:
                return saveGuest((Guest) requestObject.getData());
            case Operation.OPERATION_GET_ALL_GUESTS:
                return getAllGuests();
        }
        return null;
    }

    private ResponseObject login(Map data) {
        String username = (String) data.get("username");
        String password = (String) data.get("password");
        ResponseObject responseObject = new ResponseObject();
        try {
            User user = serviceUser.login(username, password);
            responseObject.setData(user);
            responseObject.setStatus(ResponseStatus.SUCCESS);
            loginUser = user;
        } catch (Exception ex) {
            responseObject.setStatus(ResponseStatus.ERROR);
            responseObject.setErrorMessage(ex.getMessage());
        }

        return responseObject;
    }

    public Socket getSocket() {
        return socket;
    }

    private ResponseObject getAllManufactures() {
        ResponseObject responseObject = new ResponseObject();
        try {
                
            List<GeneralDomainObject> manufacturers = Controller.getInstance().getAllManufacturer();
            
            List<Manufacturer> manufacturersTwo = new ArrayList<>();
            for(GeneralDomainObject gdo: manufacturers){
                manufacturersTwo.add((Manufacturer) gdo);
            }
            responseObject.setData(manufacturersTwo);
            responseObject.setStatus(ResponseStatus.SUCCESS);
        } catch (Exception ex) {
            responseObject.setStatus(ResponseStatus.ERROR);
            responseObject.setErrorMessage(ex.getMessage());
        }

        return responseObject;

    }
     private ResponseObject saveInvoice(Invoice invoice) {
        ResponseObject responseObject = new ResponseObject();
        try {
            Invoice i =(Invoice) Controller.getInstance().saveInvoice(invoice);
            responseObject.setStatus(ResponseStatus.SUCCESS);
            responseObject.setData(i);

        } catch (Exception ex) {
            ex.printStackTrace();
            responseObject.setStatus(ResponseStatus.ERROR);
            responseObject.setErrorMessage(ex.getMessage());
        }
        return responseObject;
    }

    private ResponseObject saveProduct(Product product) {
        //servis za proizvod
        ResponseObject responseObject = new ResponseObject();
        try {
            GeneralDomainObject gdo = Controller.getInstance().saveProduct(product);
           
            responseObject.setStatus(ResponseStatus.SUCCESS);
            responseObject.setData(product);

        } catch (Exception ex) {
            ex.printStackTrace();
            responseObject.setStatus(ResponseStatus.ERROR);
            responseObject.setErrorMessage(ex.getMessage());
        }
        return responseObject;
    }

    public User getLoginUser() {
        return loginUser;
    }

    private ResponseObject getAllProducts() {
        ResponseObject responseObject = new ResponseObject();
        try {
                
            List<GeneralDomainObject> products = Controller.getInstance().getAllProducts();
            
            List<Product> manufacturersTwo = new ArrayList<>();
            for(GeneralDomainObject gdo: products){
                manufacturersTwo.add((Product) gdo);
            }
            responseObject.setData(manufacturersTwo);
            responseObject.setStatus(ResponseStatus.SUCCESS);
        } catch (Exception ex) {
            responseObject.setStatus(ResponseStatus.ERROR);
            responseObject.setErrorMessage(ex.getMessage());
        }

        return responseObject;

    }

    private ResponseObject saveOrder(Order order) {
        ResponseObject responseObject = new ResponseObject();
        try {
            Order o = Controller.getInstance().saveOrder(order);
            responseObject.setStatus(ResponseStatus.SUCCESS);
            responseObject.setData(o);

        } catch (Exception ex) {
            ex.printStackTrace();
            responseObject.setStatus(ResponseStatus.ERROR);
            responseObject.setErrorMessage(ex.getMessage());
        }
        return responseObject;
    }

   

    private ResponseObject getOrderItems() {
        ResponseObject responseObject = new ResponseObject();
        try {
            List<OrderItem> orderItems = serviceOrder.getOrderItems();
            responseObject.setData(orderItems);
            responseObject.setStatus(ResponseStatus.SUCCESS);
        } catch (Exception ex) {
            responseObject.setStatus(ResponseStatus.ERROR);
            responseObject.setErrorMessage(ex.getMessage());
        }

        return responseObject;
    }

    private ResponseObject gellAllOrders() {
        ResponseObject responseObject = new ResponseObject();
        try {
            List<Order> orders = serviceOrder.gellAllOrders();
            //System.out.println("Print");
            responseObject.setData(orders);
            responseObject.setStatus(ResponseStatus.SUCCESS);
            
        } catch (Exception ex) {
            responseObject.setStatus(ResponseStatus.ERROR);
            responseObject.setErrorMessage(ex.getMessage());
        }

        return responseObject;
    }

    private ResponseObject deleteProduct(Product product) {
        ResponseObject responseObject = new ResponseObject();
        try {
            boolean delete = Controller.getInstance().delete(product);
            responseObject.setStatus(ResponseStatus.SUCCESS);
            responseObject.setData(delete);

        } catch (Exception ex) {
            ex.printStackTrace();
            responseObject.setStatus(ResponseStatus.ERROR);
            responseObject.setErrorMessage(ex.getMessage());
        }
        return responseObject;
    }

    private ResponseObject saveGuest(Guest guest) {
        ResponseObject responseObject = new ResponseObject();
        try {
            Guest guest1 = Controller.getInstance().saveGuest(guest);
            responseObject.setStatus(ResponseStatus.SUCCESS);
            responseObject.setData(guest1);

        } catch (Exception ex) {
            ex.printStackTrace();
            responseObject.setStatus(ResponseStatus.ERROR);
            responseObject.setErrorMessage(ex.getMessage());
        }
        return responseObject;
    }

    private ResponseObject getAllGuests() {
         ResponseObject responseObject = new ResponseObject();
        try {
            List<GeneralDomainObject> guests = Controller.getInstance().getAllGuest();
            responseObject.setData(guests);
            responseObject.setStatus(ResponseStatus.SUCCESS);
        } catch (Exception ex) {
            responseObject.setStatus(ResponseStatus.ERROR);
            responseObject.setErrorMessage(ex.getMessage());
        }

        return responseObject;
    }
//    
//}
}