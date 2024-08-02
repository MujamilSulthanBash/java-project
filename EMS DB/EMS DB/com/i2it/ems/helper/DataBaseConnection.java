package com.i2it.ems.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * <p>
 * This class is responsible for connect the database and create a single instance for it. 
 * </p>
 * 
 * @author 
 *     - Mujamil
 * @version 1.0
 */
public class DataBaseConnection {

    private static DataBaseConnection instance;
    private Connection connection;
    private String url = "jdbc:postgresql://localhost:5432/ManageEmployee";
    private String userName = "postgres";
    private String password = "tiger";

    private DataBaseConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(url, userName, password);
        } catch(ClassNotFoundException e) {
            System.out.println("DataBase Connection failed " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }
    
    /**
     * <p>
     * This is used to check wheather instance is null or not, if its null 
     * then it will create a new instance for it 
     * </p>
     * 
     * @return instance 
     *     - If its null return new instance else return instance
     */
    public static DataBaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DataBaseConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DataBaseConnection();
        }
        return instance;
    }
    
}