package com.bankapp.servlet;

import com.bankapp.dao.CustomerDAO;
import com.bankapp.dao.TransactionDAO;
import com.bankapp.model.Customer;
import com.bankapp.model.Transaction;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/withdraw")
public class WithdrawServlet extends HttpServlet {
    private CustomerDAO customerDAO;
    private TransactionDAO transactionDAO;

    @Override
    public void init() {
        customerDAO = new CustomerDAO();
        transactionDAO = new TransactionDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("customer") != null) {
            Customer customer = (Customer) session.getAttribute("customer");

            try {
                double amount = Double.parseDouble(request.getParameter("amount"));

                // Validate the amount
                if (amount <= 0) {
                    response.sendRedirect("customerDashboard.jsp?error=Invalid amount");
                    return;
                }

                if (customer.getBalance() >= amount) {
                    double newBalance = customer.getBalance() - amount;
                    customerDAO.updateCustomerBalance(customer.getAccountNo(), newBalance);

                    Transaction transaction = new Transaction();
                    transaction.setAccountNo(customer.getAccountNo());
                    transaction.setAmount(amount);
                    transaction.setTransactionType("Withdraw");
                    transactionDAO.addTransaction(transaction);

                    customer.setBalance(newBalance);
                    session.setAttribute("customer", customer);
                    response.sendRedirect("customerDashboard.jsp");
                } else {
                    response.sendRedirect("customerDashboard.jsp?error=Insufficient balance");
                }
            } catch (NumberFormatException e) {
                response.sendRedirect("customerDashboard.jsp?error=Invalid amount format");
            } catch (SQLException e) {
                throw new ServletException("Database error while processing withdrawal", e);
            }
        } else {
            response.sendRedirect("customerLogin.jsp");
        }
    }
}
