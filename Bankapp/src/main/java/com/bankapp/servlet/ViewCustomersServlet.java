package com.bankapp.servlet;

import java.io.IOException;
import java.util.List;

import com.bankapp.dao.CustomerDAO;
import com.bankapp.model.Customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/viewCustomers")
public class ViewCustomersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

   
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CustomerDAO customerDAO = new CustomerDAO();
        try {
            List<Customer> customers = customerDAO.getAllCustomers();
            // Debugging information
            System.out.println("Number of customers fetched: " + customers.size());
            request.setAttribute("customers", customers);
            request.getRequestDispatcher("viewCustomers.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}