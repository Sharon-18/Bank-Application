package com.bankapp.servlet;

import com.bankapp.dao.CustomerDAO;
import com.bankapp.model.Customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/changePassword")
public class ChangePasswordServlet extends HttpServlet {
    private CustomerDAO customerDAO;

    @Override
    public void init() {
        customerDAO = new CustomerDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("customer") == null) {
            response.sendRedirect("customerLogin.jsp?error=Session expired, please login again.");
            return;
        }

        Customer customer = (Customer) session.getAttribute("customer");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        // Validate password and confirmation
        if (newPassword == null || confirmPassword == null || !newPassword.equals(confirmPassword)) {
            request.setAttribute("error", "Passwords do not match or are invalid.");
            request.getRequestDispatcher("changePassword.jsp").forward(request, response);
            return;
        }

        try {
            // Update the password in the database
            customer.setPassword(newPassword); // No hashing needed
            customerDAO.updateCustomerPassword(customer.getCustomerId(), newPassword);

            // Set success message and forward to the changePassword.jsp page
            request.setAttribute("message", "Password changed successfully.");
            request.getRequestDispatcher("changePassword.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error updating password", e);
        }
    }
}
