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

@WebServlet("/customerDashboard")
public class CustomerDashboardServlet extends HttpServlet {
    private CustomerDAO customerDAO;

    public void init() {
        customerDAO = new CustomerDAO();
    }

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("customer") != null) {
            Customer customer = (Customer) session.getAttribute("customer");
            try {
                Customer updatedCustomer = customerDAO.getCustomerById(customer.getCustomerId());
                session.setAttribute("customer", updatedCustomer);
                request.getRequestDispatcher("customerDashboard.jsp").forward(request, response);
            } catch (SQLException e) {
                throw new ServletException(e);
            }
        } else {
            response.sendRedirect("customerLogin.jsp");
        }
    }
}
