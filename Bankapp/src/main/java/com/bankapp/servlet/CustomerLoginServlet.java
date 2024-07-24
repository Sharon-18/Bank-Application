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

@WebServlet("/customerLogin")
public class CustomerLoginServlet extends HttpServlet {
    private CustomerDAO customerDAO;

    @Override
    public void init() {
        customerDAO = new CustomerDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNo = request.getParameter("accountNo");
        String password = request.getParameter("password");

        try {
            Customer customer = customerDAO.getCustomerByAccountNo(accountNo);

            if (customer != null) {
                // Directly compare the entered password with the stored password
                String storedPassword = customer.getPassword();

                if (password.equals(storedPassword)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("customer", customer);

                    // Redirect based on whether the customer needs to change their initial password
                    if (customer.isInitialPassword()) {
                        response.sendRedirect("changePassword.jsp");
                    } else {
                        response.sendRedirect("customerDashboard.jsp");
                    }
                } else {
                    response.sendRedirect("customerLogin.jsp?error=Invalid account number or password");
                }
            } else {
                response.sendRedirect("customerLogin.jsp?error=Invalid account number or password");
            }
        } catch (SQLException e) {
            throw new ServletException("Database error occurred during login", e);
        }
    }
}
