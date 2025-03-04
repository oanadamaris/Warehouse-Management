package org.example.Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that connects the app to the database
 */
public class ConnectionFactory {
    // Logger object used for logging errors
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    // JDBC driver used for connecting to the database
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    // URL of the database to connect to
    private static final String DBURL = "jdbc:mysql://localhost:3306/warehouse";
    // Username used to connect to the database
    private static final String USER = "root";
    // Password used to connect to the database
    private static final String PASS = "rootroot";
    private static ConnectionFactory singleInstance = new ConnectionFactory();

    /**
     * Constructor of the class
     * It loads the JDBC driver used for connecting to the database
     */
    private ConnectionFactory() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * Method used for creating a connection to the database
     * @return Connection to the database
     */
    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DBURL, USER, PASS);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "An error occured while trying to connect to the database");
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Creates connection to database
     * @return
     */
    public static Connection getConnection() {
        return singleInstance.createConnection();
    }
    /**
     * Method used for closing a connection to the database
     * @param connection Connection to be closed
     */
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the connection");
            }
        }
    }
    /**
     * Method used for closing a statement
     * @param statement Statement to be closed
     */
    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the statement");
            }
        }
    }

    /**
     * Method used for closing a resultSet
     * @param resultSet
     */
    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the ResultSet");
            }
        }
    }
}
