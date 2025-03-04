package org.example.BusinessLayer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.example.DataAccessLayer.OrdersDA;
import org.example.DataAccessLayer.ProductDA;
import org.example.Model.Bill;
import org.example.Model.Orders;
import org.example.Model.Product;

/**
 * The class contains the business logic of the Orders
 */

public class OrdersBL {
    private List<Validator<Orders>> validators;
    private OrdersDA ordersDA;
    /**
     * Constructor that initializes the validators and the OrdersDA
     */
    public OrdersBL() {
        validators = new ArrayList<Validator<Orders>>();
        ordersDA = new OrdersDA();
    }
    /**
     * Method that finds an order by id
     * @param id is the id of the order we're looking for
     * @return st is the order we're looking for
     */
    public Orders findOrdersById(int id) {
        Orders st = ordersDA.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The order with id= " + id + " was not found!");
        }
        return st;
    }
    /**
     * Method that inserts an order
     * @param order is the order we want to insert
     * checks if the quantity of the product is enough, if not throws an exception, then inserts the order and calculates
     * the bill for the order and inserts it
     * @return orderDA.insert(order) is the inserted order
     */
    public Orders insertOrders(Orders order) {
        for (Validator<Orders> v : validators) {
            v.validate(order);
        }

        Product prod=new ProductBL().findProductById(order.getProduct());
        if(prod.getQuantity()<order.getQuantityO()){
            throw new IllegalArgumentException("The quantity of the product is not enough!");
        }
        else{
            prod.setQuantity(prod.getQuantity()-order.getQuantityO());
            new ProductBL().update(prod.getId(),"quantity",prod.getQuantity());
        }
        Orders o=ordersDA.insert(order);
        Bill b=calculateBill(o);
        b.insertBill(b);
        return o;
    }
    /**
     * Method that updates an order
     * @param id is the id of the order we want to update
     * @param column is the column we want to update
     * @param value is the new value of the column
     * @return orderDA.update(column,value,id) is the updated order
     */
    public Orders update(int id,String column, Object value) {
        return ordersDA.update(column,value,id);
    }
    /**
     * Method that deletes an order by id
     * @param id is the id of the order we want to delete
     */
    public void deleteOrders(int id) {
        ordersDA.delete(id);
    }
    /**
     * Method that calculates the bill for an order
     * @param order is the order we want to calculate the bill for
     * it calculates the bill by multiplying the quantity of the product with the price of the product
     * @return result is the bill for the order
     */
    public Bill calculateBill(Orders order){
        int bill=0;
        Product prod=new ProductBL().findProductById(order.getProduct());
        bill= order.getQuantityO()*prod.getPrice();
        Bill result=new Bill(order.getId(),order.getId(),bill);
        return result;
    }
    /**
     * Method that gets all the columns from the table named orders
     * @return orderDA.findAll() is the list of names of the columns
     */
    public List<String> getColumns() throws SQLException {
        return ordersDA.getColumnNames(Orders.class);
    }
    /**
     * Method that gets all the orders
     * @return orderDA.findAll() is the list of all orders
     */
    public List<Orders> findAllOrders() {
        return ordersDA.findAll();
    }
}

