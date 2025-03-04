package org.example;

import org.example.BusinessLayer.OrdersBL;
import org.example.BusinessLayer.ProductBL;
import org.example.DataAccessLayer.AbstractDA;
import org.example.BusinessLayer.ClientBL;
import org.example.Model.Client;
import org.example.Model.Orders;
import org.example.Model.Product;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Entry point of App and place to test the implemented methods
 */

public class Start {
    protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

        public static void main(String[] args) throws SQLException {

            ProductBL productsBL = new ProductBL();
            Product p1=null;
            //ordersBL.getColumns();
            productsBL.deleteProduct(100);
            ClientBL clientBL = new ClientBL();
//            System.out.println(productsBL.findAllProducts().toString());

            try {
                //System.out.println(clientBL.getClientsId().toString());
                //System.out.println(productBL.getProductsId().toString());
                //p1 = productsBL.insertProduct(new Product(100, "product", 100, 100));
//                o1=ordersBL.update(100,"client",400);
//                o1=ordersBL.findOrdersById(100);
                 //o1=ordersBL.findOrdersById(101);

            } catch (Exception ex) {
                LOGGER.log(Level.INFO, ex.getMessage());
            }

             //obtain field-value pairs for object through reflection
            ReflectionExample.retrieveProperties(p1);

        }


}
