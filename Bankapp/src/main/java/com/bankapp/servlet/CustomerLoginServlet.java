package com.bankapp.servlet;

import com.bankapp.dao.CustomerDAO;
import com.bankapp.model.Customer;
import com.bankapp.util.PasswordUtils;

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

    public void init() {
        customerDAO = new CustomerDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNo = request.getParameter("accountNo");
        String password = request.getParameter("password");

        try {
            Customer customer = customerDAO.getCustomerByAccountNo(accountNo);
            System.out.println(password);
            System.out.println(customer.getPassword());
            System.out.print(PasswordUtils.verifyPassword(password, customer.getPassword()));
            if (customer != null && PasswordUtils.verifyPassword(password, customer.getPassword())) {
                HttpSession session = request.getSession();
                session.setAttribute("customer", customer);
                response.sendRedirect("customerDashboard.jsp");
            } else {
                response.sendRedirect("customerLogin.jsp?error=Invalid account number or password");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
