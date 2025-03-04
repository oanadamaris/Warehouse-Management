package org.example.Model;
import org.example.BusinessLayer.OrdersBL;
import org.example.Connection.ConnectionFactory;
import org.example.DataAccessLayer.AbstractDA;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Immutable Bill class that generates a bill for each order and is stored in a table in the database
 * @param id
 * @param idOrder
 * @param total
 */
public record Bill(int id, int idOrder, int total) {

    /**
     * Method that inserts a bill in the database table by using a prepared statement
     * @param b
     * @return
     */
    public Bill insertBill( Bill b){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = " INSERT INTO Bill (id,idOrder,total) VALUES(?,?,?)";
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, b.id());
            statement.setInt(2, b.idOrder());
            statement.setInt(3, b.total());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error inserting bill: " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return b;
    }

    /**
     * Method that finds all the bills in the database table by using a prepared statement
     * @return
     */
    public List<Bill> findAll() {
        List<Bill> bills = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM Bill";
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                bills.add(new Bill(resultSet.getInt("id"), resultSet.getInt("idOrder"), resultSet.getInt("total")));
            }
        } catch (SQLException e) {
            System.out.println("Bill:findAll"+ e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return bills;
    }

    /**
     * Method that finds a bill by its id in the database table by using a prepared statement
     * @param idOrder
     * @return
     */
    public Bill findByIdOrder(int idOrder) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM Bill WHERE idOrder = ?";
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, idOrder);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Bill(resultSet.getInt("id"), resultSet.getInt("idOrder"), resultSet.getInt("total"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Method that deletes a bill by its id in the database table by using a prepared statement
     * @param id
     */
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "DELETE FROM Bill WHERE id = ?";
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
}
