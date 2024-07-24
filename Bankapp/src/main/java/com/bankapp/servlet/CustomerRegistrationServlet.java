package com.bankapp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.bankapp.util.DatabaseConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/registerCustomer")
public class CustomerRegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("fullName");
        String address = request.getParameter("address");
        String mobileNo = request.getParameter("mobileNo");
        String emailId = request.getParameter("emailId");
        String accountType = request.getParameter("accountType");
        double initialBalance = Double.parseDouble(request.getParameter("initialBalance"));
        String dobStr = request.getParameter("dob");
        String idProof = request.getParameter("idProof");

        // Convert String to Date
        Date dob = null;
        try {
            dob = new SimpleDateFormat("yyyy-MM-dd").parse(dobStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Insert customer into the database
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO Customer (full_name, address, mobile_no, email_id, account_type, initial_balance, dob, id_proof) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, fullName);
            stmt.setString(2, address);
            stmt.setString(3, mobileNo);
            stmt.setString(4, emailId);
            stmt.setString(5, accountType);
            stmt.setDouble(6, initialBalance);
            stmt.setDate(7, new java.sql.Date(dob.getTime()));
            stmt.setString(8, idProof);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("viewCustomers");
    }
}
