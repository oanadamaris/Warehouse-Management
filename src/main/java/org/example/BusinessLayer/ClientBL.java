package org.example.BusinessLayer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.example.DataAccessLayer.ClientDA;
import org.example.Model.Client;
/**
 * The class contains the business logic of the client
 */
public class ClientBL {
    private List<Validator<Client>> validators;
    private ClientDA clientDA;
    /**
     * Constructor that initializes the validators and the clientDA
     */
    public ClientBL() {
        validators = new ArrayList<Validator<Client>>();
        validators.add(new EmailValidator());
        clientDA = new ClientDA();
    }
    /**
     * Method that finds a client by id
     * @param id is the id of the client we're looking for
     * @return st is the client we're looking for
     */
    public Client findClientById(int id) {
        Client st = clientDA.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The client with id= " + id + " was not found!");
        }
        return st;
    }
    /**
     * Method that inserts a client
     * @param client is the client we want to insert
     * @return clientDA.insert(client) is the inserted client
     */
    public Client insertClient(Client client) {
        for (Validator<Client> v : validators) {
            v.validate(client);
        }
        return clientDA.insert(client);
    }
    /**
     * Method that updates a client
     * @param id is the id of the client we want to update
     * @param column is the column we want to update
     * @param value is the new value of the column
     * @return clientDA.update(column,value,id) is the updated client
     */
    public Client update(int id,String column, Object value) {
        if(column.equals("email")){
            EmailValidator emailValidator = new EmailValidator();
            emailValidator.validate(findClientById(id));
        }
         return clientDA.update(column,value,id);
    }
    /**
     * Method that deletes a client
     * @param id is the id of the client we want to delete
     */
    public void deleteClient(int id) {
        clientDA.delete(id);

    }
    /**
     * Method that gets all the clients
     * @return clientDA.findAll() is the list of all clients
     */
    public List<Client> findAllClients() {
        return clientDA.findAll();
    }
    /**
     * Method that gets the ids of all the clients
     * @return clientDA.getIds(Client.class) is the list of all clients' ids
     */
    public List<Integer> getClientsId() {
        return clientDA.getIds(Client.class);
    }
    /**
     * Method that gets the columns of the client table
     * @return clientDA.getColumnNames(Client.class) is the list of all columns of the client
     */
    public List<String> getColumns() throws SQLException {
        return clientDA.getColumnNames(Client.class);
    }

}

