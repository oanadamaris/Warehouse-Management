package org.example.BusinessLayer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.example.DataAccessLayer.ProductDA;
import org.example.Model.Product;

/**
 * The class contains the business logic of the product
 */

public class ProductBL {
    private List<Validator<Product>> validators;
    private ProductDA productDA;

    /**
     * Constructor that initializes the validators and the productDA
     */
    public ProductBL() {
        validators = new ArrayList<Validator<Product>>();
        productDA = new ProductDA();
    }

    /**
     * Method that finds a product by its id
     * @param id is the id of the product we're looking for
     * @return st is the product we're looking for
     */
    public Product findProductById(int id) {
        Product st = productDA.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The product with id= " + id + " was not found!");
        }
        return st;
    }

    /**
     * Method that inserts a product
     * @param product
     * @return
     */
    public Product insertProduct(Product product) {
        for (Validator<Product> v : validators) {
            v.validate(product);
        }
        return productDA.insert(product);
    }
    /**
     * Method that updates a product
     * @param id is the id of the product we want to update
     * @param column is the column we want to update
     * @param value is the new value of the column
     * @return productDA.update(column,value,id) is the updated product
     */
    public Product update(int id,String column, Object value) {
        return productDA.update(column,value,id);
    }
    /**
     * Method that deletes a product
     * @param id is the id of the product we want to delete
     */
    public void deleteProduct(int id) {
        productDA.delete(id);
        System.out.println("Deleted!");
    }
    /**
     * Method that gets all the names of the products table
     * @return productDA.findAll() is a list of the names we were looking for
     */
    public List<String> getColumns() throws SQLException {
        return productDA.getColumnNames(Product.class);
    }
    /**
     * Method that gets all the id s of the products
     * @return productDA.getIds(Product.class) is a list of the ids we were looking for
     */
    public List<Integer> getProductsId() {
        return productDA.getIds(Product.class);
    }
    /**
     * Method that gets all the data from product table
     * @return productDA.findAll() is a list of all products
     */
    public List<Product> findAllProducts() {
        return productDA.findAll();
    }
}

