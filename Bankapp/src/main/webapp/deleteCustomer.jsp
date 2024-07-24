<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.bankapp.dao.CustomerDAO, com.bankapp.model.Customer" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete Customer</title>
</head>
<body>
    <%
        String customerIdStr = request.getParameter("customerId");
        if (customerIdStr != null) {
            CustomerDAO customerDAO = new CustomerDAO();
            try {
                Customer customer = customerDAO.getCustomerById(Integer.parseInt(customerIdStr));
                if (customer != null) {
    %>
    <h1>Confirm Deletion</h1>
    <p>Are you sure you want to delete the following customer?</p>
    <table border="1">
        <tr>
            <th>Customer ID</th>
            <th>Full Name</th>
            <th>Address</th>
            <th>Mobile No</th>
            <th>Email ID</th>
            <th>Account Type</th>
            <th>Date of Birth</th>
            <th>ID Proof</th>
            <th>Account No</th>
        </tr>
        <tr>
            <td><%= customer.getCustomerId() %></td>
            <td><%= customer.getFullName() %></td>
            <td><%= customer.getAddress() %></td>
            <td><%= customer.getMobileNo() %></td>
            <td><%= customer.getEmailId() %></td>
            <td><%= customer.getAccountType() %></td>
            <td><%= customer.getDob() %></td>
            <td><%= customer.getIdProof() %></td>
            <td><%= customer.getAccountNo() %></td>
        </tr>
    </table>
    <form action="DeleteCustomerServlet" method="post">
        <input type="hidden" name="customerId" value="<%= customerIdStr %>">
        <input type="submit" value="Confirm Deletion">
    </form>
    <a href="viewCustomers">Cancel</a>
    <%
                } else {
                    out.println("<p>Customer not found.</p>");
                }
            } catch (Exception e) {
                e.printStackTrace();
                out.println("<p>Error retrieving customer details.</p>");
            }
        } else {
            out.println("<p>No customer ID provided.</p>");
        }
    %>
</body>
</html>
