package controller;

//import so.invoice.SaveInvoice;
import so.manufacturer.GetAllManufacturer;
import so.product.SaveProduct;
import domain.GeneralDomainObject;
import domain.Guest;
import domain.Invoice;
import domain.Manufacturer;
import domain.Order;
import domain.Product;
import java.util.List;
import so.AbstractGenericOperation;
import so.guest.GetAllGuest;
import so.guest.SaveGuest;
import so.invoice.SaveInvoice;
import so.order.SaveOrder;
import so.product.DeleteProduct;
import so.product.GetAllProducts;
//import so.guest.GetAllGuest;
//import so.guest.SaveGuest;
//import so.product.DeleteProduct;
//import so.product.UpdateProduct;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Katica
 */
public class Controller {

    private static Controller instance;

    private Controller() {

    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public GeneralDomainObject saveProduct(Product product) throws Exception {
        AbstractGenericOperation so = new SaveProduct();
        Object[] data = new Object[1];
        data[0] = product;
        so.templateExecute(data);
        return ((SaveProduct) so).getObject();

    }

    public GeneralDomainObject saveInvoice(Invoice entity) throws Exception {
        AbstractGenericOperation so = new SaveInvoice();
        Object[] data = new Object[1];
        System.out.println("Invoice" + entity.getAmount()+" "+entity.getId());
        Invoice invoice = entity;
        data[0] = invoice;
        //System.out.println("Invoice" + data[0]);
        so.templateExecute(data);
        return ((SaveInvoice) so).getObject();
    }

    public List<GeneralDomainObject> getAllManufacturer() throws Exception {
        AbstractGenericOperation so = new GetAllManufacturer();
        Object[] data = new Object[1];
        Manufacturer manu = new Manufacturer();
        data[0] = manu;
        so.templateExecute(data);
        return ((GetAllManufacturer) so).getObject();
    }

    public Guest saveGuest(Guest entity) throws Exception {
       AbstractGenericOperation so = new SaveGuest();
        Object[] data = new Object[1];
        data[0] = entity;
        so.templateExecute(data);
        return (Guest) ((SaveGuest)so).getGuest();
    }
    public Order saveOrder(Order entity) throws Exception{
        AbstractGenericOperation so = new SaveOrder();
        Object[] data = new Object[1];
        data[0] = entity;
        so.templateExecute(data);
        return (Order) ((SaveOrder) so).getObject();
    }

//    public void deleteProduct(Product product) throws Exception {
//        AbstractGenericOperation so = new DeleteProduct();
//        so.execute((GeneralDomainObject) product);
//    }
//
//    public void updateProduct(Product product) throws Exception {
//        AbstractGenericOperation so = new UpdateProduct();
//        so.execute((GeneralDomainObject) product);
//    }
//
    public List<GeneralDomainObject> getAllGuest() throws Exception {
        AbstractGenericOperation so = new GetAllGuest();
        Object[] data = new Object[1];
        Guest guest = new Guest();
        data[0] = guest;
        so.templateExecute(data);
        return ((GetAllGuest) so).getList();
    }

    public boolean delete(Product product) throws Exception {
         AbstractGenericOperation so = new DeleteProduct();
        Object[] data = new Object[1];
        data[0] = product;
        so.templateExecute(data);
        return ((DeleteProduct) so).isDeleted();
    }

    public List<GeneralDomainObject> getAllProducts() throws Exception {
        AbstractGenericOperation so = new GetAllProducts();
        Object[] data = new Object[1];
        Product product = new Product();
        data[0] = product;
        so.templateExecute(data);
        return ((GetAllProducts) so).getObject();
    }

   
    

  
}
