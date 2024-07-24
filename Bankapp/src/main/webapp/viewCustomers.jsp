<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List, com.bankapp.model.Customer" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Customers</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
    <h2>Customer List</h2>
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
            <th>Actions</th>
        </tr>
        <%
            List<Customer> customers = (List<Customer>) request.getAttribute("customers");
            if (customers != null) {
                for (Customer customer : customers) {
        %>
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
            <td>
                <a href="editCustomer?customerId=<%= customer.getCustomerId() %>">Edit</a> |
                <a href="deleteCustomer.jsp?customerId=<%= customer.getCustomerId() %>" onclick="return confirm('Are you sure you want to delete this customer?');">Delete</a>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="10">No customers found</td>
        </tr>
        <%
            }
        %>
    </table>
    <a href="adminDashboard.jsp">Back to Dashboard</a>
</body>
</html>
