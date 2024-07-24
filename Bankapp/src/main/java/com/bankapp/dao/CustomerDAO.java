package com.bankapp.dao;

import com.bankapp.model.Customer;
import com.bankapp.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    // Add a customer to the database
    public void addCustomer(Customer customer) throws SQLException {
        String query = "INSERT INTO Customer (full_name, address, mobile_no, email_id, account_type, balance, dob, id_proof, account_no, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, customer.getFullName());
            stmt.setString(2, customer.getAddress());
            stmt.setString(3, customer.getMobileNo());
            stmt.setString(4, customer.getEmailId());
            stmt.setString(5, customer.getAccountType());
            stmt.setDouble(6, customer.getBalance());
            stmt.setDate(7, new java.sql.Date(customer.getDob().getTime()));
            stmt.setString(8, customer.getIdProof());
            stmt.setString(9, customer.getAccountNo());
            stmt.setString(10, customer.getPassword()); // Store plain text password
            stmt.executeUpdate();
        }
    }

    // Update a customer's details in the database
    public void updateCustomer(Customer customer) throws SQLException {
        String query = "UPDATE Customer SET full_name = ?, address = ?, mobile_no = ?, email_id = ?, account_type = ?, dob = ?, id_proof = ? WHERE customer_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, customer.getFullName());
            stmt.setString(2, customer.getAddress());
            stmt.setString(3, customer.getMobileNo());
            stmt.setString(4, customer.getEmailId());
            stmt.setString(5, customer.getAccountType());
            stmt.setDate(6, new java.sql.Date(customer.getDob().getTime()));
            stmt.setString(7, customer.getIdProof());
            stmt.setInt(8, customer.getCustomerId());
            stmt.executeUpdate();
        }
    }

    // Delete a customer from the database
    public void deleteCustomer(int customerId) throws SQLException {
        String query = "DELETE FROM Customer WHERE customer_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, customerId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No customer found with ID: " + customerId);
            }
        }
    }

    // Get a customer's details by their ID
    public Customer getCustomerById(int customerId) throws SQLException {
        String query = "SELECT * FROM Customer WHERE customer_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("customer_id"));
                customer.setFullName(rs.getString("full_name"));
                customer.setAddress(rs.getString("address"));
                customer.setMobileNo(rs.getString("mobile_no"));
                customer.setEmailId(rs.getString("email_id"));
                customer.setAccountType(rs.getString("account_type"));
                customer.setBalance(rs.getDouble("balance"));
                customer.setDob(rs.getDate("dob"));
                customer.setIdProof(rs.getString("id_proof"));
                customer.setAccountNo(rs.getString("account_no"));
                // Retrieve and set the plain text password
                customer.setPassword(rs.getString("password"));
                
                return customer;
            } else {
                return null;
            }
        }
    }

    // Get a customer's details by their account number
    public Customer getCustomerByAccountNo(String accountNo) throws SQLException {
        String query = "SELECT * FROM Customer WHERE account_no = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, accountNo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("customer_id"));
                customer.setFullName(rs.getString("full_name"));
                customer.setAddress(rs.getString("address"));
                customer.setMobileNo(rs.getString("mobile_no"));
                customer.setEmailId(rs.getString("email_id"));
                customer.setAccountType(rs.getString("account_type"));
                customer.setBalance(rs.getDouble("balance"));
                customer.setDob(rs.getDate("dob"));
                customer.setIdProof(rs.getString("id_proof"));
                customer.setAccountNo(rs.getString("account_no"));
                // Retrieve and set the plain text password
                customer.setPassword(rs.getString("password"));
                
                return customer;
            } else {
                return null;
            }
        }
    }

    // Get all customers from the database
    public List<Customer> getAllCustomers() throws SQLException {
        String query = "SELECT * FROM Customer";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            List<Customer> customers = new ArrayList<>();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("customer_id"));
                customer.setFullName(rs.getString("full_name"));
                customer.setAddress(rs.getString("address"));
                customer.setMobileNo(rs.getString("mobile_no"));
                customer.setEmailId(rs.getString("email_id"));
                customer.setAccountType(rs.getString("account_type"));
                customer.setBalance(rs.getDouble("balance"));
                customer.setDob(rs.getDate("dob"));
                customer.setIdProof(rs.getString("id_proof"));
                customer.setAccountNo(rs.getString("account_no"));
                // Retrieve and set the plain text password
                customer.setPassword(rs.getString("password"));
                customers.add(customer);
            }
            return customers;
        }
    }

    // Update a customer's balance
    public void updateCustomerBalance(String accountNo, double newBalance) throws SQLException {
        String query = "UPDATE Customer SET balance = ? WHERE account_no = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, newBalance);
            stmt.setString(2, accountNo);
            stmt.executeUpdate();
        }
    }

    // Update a customer's password
    public void updateCustomerPassword(int customerId, String newPassword) throws SQLException {
        String query = "UPDATE Customer SET password = ? WHERE customer_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, newPassword); // Store plain text new password
            stmt.setInt(2, customerId);
            stmt.executeUpdate();
        }
    }
}
