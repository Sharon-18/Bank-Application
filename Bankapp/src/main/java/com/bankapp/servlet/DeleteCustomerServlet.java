package com.bankapp.servlet;

import com.bankapp.dao.CustomerDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/DeleteCustomerServlet")
public class DeleteCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String customerIdStr = request.getParameter("customerId");

        if (customerIdStr != null) {
            try {
                int customerId = Integer.parseInt(customerIdStr);
                CustomerDAO customerDAO = new CustomerDAO();
                customerDAO.deleteCustomer(customerId);

                response.sendRedirect("viewCustomers");
            } catch (NumberFormatException e) {
                // Handle invalid customer ID format
                response.sendRedirect("error.jsp?message=Invalid customer ID format");
            } catch (SQLException e) {
                // Handle SQL exceptions
                e.printStackTrace();
                response.sendRedirect("error.jsp?message=Error deleting customer");
            }
        } else {
            response.sendRedirect("error.jsp?message=No customer ID provided");
        }
    }
}
