package org.example.DataAccessLayer;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.reflect.Field;

import org.example.Connection.ConnectionFactory;

/**
 * Class that contains the basic CRUD operations
 * @param <T>
 */
public class AbstractDA<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDA.class.getName());

    private final Class<T> type;
    // Get the type of the class
    @SuppressWarnings("unchecked")
    public AbstractDA() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /** Create a select query
     * @param field
     * @return
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        if(!field.equals("ALL")) {
            sb.append(" WHERE " + field + " =?");
        }
        return sb.toString();
    }

    /**
     * Get all the ids from a table by executing SELECT ID FROM table name
     * @param type
     * @return
     */
    public List<Integer> getIds(Class<T> type) {
        List<Integer> ids = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = " SELECT id FROM " + type.getSimpleName();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ids.add(resultSet.getInt("id"));
            }
        }
        catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DA:getIds " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return ids;
    }

    /**
     * Get all the column names from a table by executing SELECT * FROM table name and getting the column names
     * from the ResultSetMetaData
     * @param type
     * @return
     */
    public List<String> getColumnNames(Class<T> type) {
        List<String> columns = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("ALL");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int count=rsmd.getColumnCount();
            for(int i=1;i<=count;i++){
                columns.add(rsmd.getColumnName(i));
            }
        }
        catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DA:getColumns " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return columns;
    }

    /**
     * Get all the objects from a table by executing SELECT * FROM table name
     * @return
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("ALL");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException e) {
        LOGGER.log(Level.WARNING, type.getName() + "DA:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Find an object by id by executing SELECT * FROM table name WHERE id = ? and returning the first object
     * @param id
     * @return
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            List<T> objects = createObjects(resultSet);
            if (!objects.isEmpty()) {
                return objects.get(0);
            } else {
                return null;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DA:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Create a list of objects from a ResultSet
     * @param resultSet
     * @return
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Builds the SQL script for INSERT operation
     * Gets the type's columns and builds the script
     * @return
     */
    public StringBuilder insertQuery(){
        List<String> columns = new ArrayList<>();
        columns= getColumnNames(type);
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO "+type.getSimpleName()+" (");
        for(int i=0;i<columns.size();i++){
            if(i==columns.size()-1){
                sb.append(columns.get(i)+") VALUES (");
            } else {
                sb.append(columns.get(i) + ",");
            }
        }
        for(int i=0;i<columns.size();i++){
            if(i==columns.size()-1){
                sb.append("?)");
            } else {
                sb.append("?,");
            }
        }
        return sb;
    }

    /**
     * Insert an object into the database by executing the INSERT query and setting the values of the columns with
     * the values of the object's fields
     * @param t
     * @return
     */
    public T insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query =insertQuery().toString();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            List<Integer>ids=getIds(type);
            int i=1;

            for (Field field : type.getDeclaredFields()) {
                field.setAccessible(true);
                statement.setObject(i, field.get(t));
                if(field.getName().equals("id")){
                for(int idz: ids){
                    System.out.println(idz);
                    if(idz==(int)field.get(t)){
                        throw new IllegalArgumentException("The id is already in the database!");
                    }
                }}
                i++;
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DA:INSERT " + e.getMessage());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return t;
    }

    /**
     * Builds the SQL script for UPDATE operation
     * @param column
     * @return
     */
    public StringBuilder updateQuery(String column){
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE "+type.getSimpleName()+" SET ");
        sb.append(column + " = ? ");
        sb.append("WHERE id = ?");
        return sb;
    }

    /**
     * Update an object by id by executing the UPDATE query and setting the value of the column with the value
     * of the object's field and an id that is given directly by the user
     * @param column
     * @param value
     * @param id
     * @return
     */
    public T  update(String column, Object value, int id ) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query =updateQuery(column).toString();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setObject(1, value);
            statement.setObject(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DA:UPDATE " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        T t=findById(id);
        return t;
    }

    /**
     * Delete an object by id by executing DELETE FROM table name WHERE id = ? and setting the id with the value
     * given by the user
     * @param id
     */
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "DELETE FROM " + type.getSimpleName() + " WHERE id = " +id;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DA:DELETE " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
}
