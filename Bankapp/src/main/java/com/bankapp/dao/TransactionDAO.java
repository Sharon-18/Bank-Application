package com.bankapp.dao;

import com.bankapp.model.Transaction;
import com.bankapp.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {
    public void addTransaction(Transaction transaction) throws SQLException {
        String query = "INSERT INTO Transaction (account_no, amount, transaction_type) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, transaction.getAccountNo());
            stmt.setDouble(2, transaction.getAmount());
            stmt.setString(3, transaction.getTransactionType());
            stmt.executeUpdate();
        }
    }

    public List<Transaction> getTransactionsByAccountNo(String accountNo) throws SQLException {
        String query = "SELECT * FROM Transaction WHERE account_no = ? ORDER BY transaction_date DESC LIMIT 10";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, accountNo);
            ResultSet rs = stmt.executeQuery();
            List<Transaction> transactions = new ArrayList<>();
            while (rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setTransactionId(rs.getInt("transaction_id"));
                transaction.setAccountNo(rs.getString("account_no"));
                transaction.setAmount(rs.getDouble("amount"));
                transaction.setTransactionType(rs.getString("transaction_type"));
                transaction.setTransactionDate(rs.getTimestamp("transaction_date"));
                transactions.add(transaction);
            }
            return transactions;
        }
    }
}
