package com.bankapp.servlet;

import com.bankapp.dao.CustomerDAO;
import com.bankapp.model.Customer;
import com.bankapp.util.PasswordUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/registerCustomerNew")
public class RegisterCustomerServlet extends HttpServlet {
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

        // Generate account number and temporary password
        String accountNo = PasswordUtils.generateAccountNumber();
        String tempPassword = PasswordUtils.generateTemporaryPassword();

        Customer customer = new Customer();
        customer.setFullName(fullName);
        customer.setAddress(address);
        customer.setMobileNo(mobileNo);
        customer.setEmailId(emailId);
        customer.setAccountType(accountType);
        customer.setBalance(initialBalance);
        customer.setDob(dob);
        customer.setIdProof(idProof);
        customer.setAccountNo(accountNo);
        customer.setPassword(tempPassword); // Store the plain text temporary password

        CustomerDAO customerDAO = new CustomerDAO();
        try {
            customerDAO.addCustomer(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Redirect to a success page with the generated account number and temporary password
        request.setAttribute("accountNo", accountNo);
        request.setAttribute("tempPassword", tempPassword);
        request.getRequestDispatcher("customerRegistrationSuccess.jsp").forward(request, response);
    }
}
