/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import domain.Manufacturer;
import domain.Product;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import service.impl.ServiceManufacturerImpl;
import service.impl.ServiceProductImpl;
import services.ServiceManufacturer;
import services.ServiceProduct;
import transfer.RequestObject;
import transfer.ResponseObject;
import util.Operation;
import util.ResponseStatus;

/**
 *
 * @author Katica
 */
public class Server {
    private ServerSocket serverSocket;
    private Socket socket;
    private ServiceManufacturer serviceManufacturer;
    private ServiceProduct serviceProduct;

    public Server() {
        serviceManufacturer = new ServiceManufacturerImpl();
        serviceProduct = new ServiceProductImpl();
    }

    public void start() throws IOException, ClassNotFoundException {
        serverSocket = new ServerSocket(9000);
        System.out.println("Server is up and running");
        socket = serverSocket.accept();
        System.out.println("New client connection has been established");
        handleClient();
    }

    private void handleClient() throws IOException, ClassNotFoundException {
        while (true) {
            ObjectInputStream inSocket = new ObjectInputStream(socket.getInputStream());
            RequestObject requestObject = (RequestObject) inSocket.readObject();

            ResponseObject responseObject = handleRequest(requestObject);
            ObjectOutputStream outSocket = new ObjectOutputStream(socket.getOutputStream());
            outSocket.writeObject(responseObject);
        }
    }

    private ResponseObject handleRequest(RequestObject requestObject) {
        ResponseObject responseObject = new ResponseObject();
        try {
            System.out.println("O: " + requestObject.getOperation());
            switch (requestObject.getOperation()) {
                case Operation.OPERATION_LOGIN:
                    break;
                case Operation.OPERATION_GET_ALL_MANUFACTURERS:
                    List<Manufacturer> list = serviceManufacturer.getAll();
                    responseObject.setData(list);
                    responseObject.setStatus(ResponseStatus.SUCCESS);
                    break;
                case Operation.OPERATION_SAVE_PRODUCT:
                    Product product = serviceProduct.save((Product) requestObject.getData());
                    responseObject.setData(product);
                    responseObject.setStatus(ResponseStatus.SUCCESS);
                    break;
                case Operation.OPERATION_GET_ALL_PRODUCTS:
                    List<Product> products = serviceProduct.getAll();
                    responseObject.setData(products);
                    responseObject.setStatus(ResponseStatus.SUCCESS);
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            responseObject.setStatus(ResponseStatus.ERROR);
            responseObject.setErrorMessage(ex.getMessage());
        }
        return responseObject;
    }
}
