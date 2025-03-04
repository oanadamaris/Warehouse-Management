package org.example.Model;

/**
 * Client class that stores the client's information
 */
public class Client {
    private int id;
    private String name;
    private String email;
    private String address;
    public Client() {
    }

    /**
     * Constructor with parameters for the Client class
     * @param id
     * @param name
     * @param address
     * @param email
     */
    public Client(int id, String name, String address, String email) {
        super();
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
    }
    //Getters and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString() {
        return "Student [ id=" + id + ", name=" + name + ", address=" + address + ", email=" + email+" ]";
    }

}
