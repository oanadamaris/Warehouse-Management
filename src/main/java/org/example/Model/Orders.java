package org.example.Model;

/**
 * Orders class that stores the order's information
 */
public class Orders {
    private int id;
    private int client;
    private int product;
    private int quantityO;
    public Orders(){
    }
    // Constructor with parameters for the Orders class
    public Orders(int id, int client, int product, int quantityO) {
        super();
        this.id = id;
        this.client = client;
        this.product = product;
        this.quantityO = quantityO;
    }
    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClient() {
        return client;
    }

    public void setClient(int client) {
        this.client = client;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public int getQuantityO() {
        return quantityO;
    }

    public void setQuantityO(int quantityO) {
        this.quantityO = quantityO;
    }

}
