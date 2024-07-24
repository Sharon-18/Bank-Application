package com.bankapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/BankDB?autoReconnect=true&maxReconnects=10&initialTimeout=5";
    private static final String USER = "root";
    private static final String PASSWORD = "sh123";

    public static Connection getConnection() {
    	Connection con = null;
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		 con = DriverManager.getConnection(URL, USER, PASSWORD);
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    		System.out.println("Error in Database Connection");
    	}catch(ClassNotFoundException e) {
    		e.printStackTrace();
    		System.out.println("Class not found in Database");
    	}
    	return con;
    }
}
