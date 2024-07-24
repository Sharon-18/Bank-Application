package com.bankapp.servlet;

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
import java.util.List;

@WebServlet("/transactionHistory")
public class TransactionHistoryServlet extends HttpServlet {
    private TransactionDAO transactionDAO;

    @Override
    public void init() throws ServletException {
        transactionDAO = new TransactionDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("customer") != null) {
            Customer customer = (Customer) session.getAttribute("customer");
            try {
                // Fetch transactions for the customer's account number
                List<Transaction> transactions = transactionDAO.getTransactionsByAccountNo(customer.getAccountNo());
                request.setAttribute("transactions", transactions);
                
                // Forward to the JSP page to display the transactions
                request.getRequestDispatcher("transactionHistory.jsp").forward(request, response);
            } catch (SQLException e) {
                throw new ServletException("Error retrieving transaction history", e);
            }
        } else {
            // Redirect to login page if the session is not valid
            response.sendRedirect("customerLogin.jsp");
        }
    }
}
