package com.bankapp.dao;

import com.bankapp.model.Transaction;
import com.bankapp.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {

    // Method to add a transaction
    public void addTransaction(Transaction transaction) throws SQLException {
        String query = "INSERT INTO transaction (account_no, amount, transaction_type, transaction_date) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, transaction.getAccountNo());
            stmt.setDouble(2, transaction.getAmount());
            stmt.setString(3, transaction.getTransactionType());
            stmt.executeUpdate();
        }
    }

    // Method to get transactions by account number
    public List<Transaction> getTransactionsByAccountNo(String accountNo) throws SQLException {
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM transaction WHERE account_no = ? ORDER BY transaction_date DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, accountNo);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Transaction transaction = new Transaction();
                    transaction.setAccountNo(rs.getString("account_no"));
                    transaction.setAmount(rs.getDouble("amount"));
                    transaction.setTransactionType(rs.getString("transaction_type"));
                    transaction.setTransactionDate(rs.getTimestamp("transaction_date")); // Use getTimestamp to retrieve Date
                    transactions.add(transaction);
                }
            }
        }
        return transactions;
    }
}
