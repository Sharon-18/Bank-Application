<%@ page import="com.bankapp.model.Transaction" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Transaction History</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
    <h2>Transaction History</h2>
    <table border="1">
        <tr>
            <th>Date</th>
            <th>Type</th>
            <th>Amount</th>
        </tr>
        <% 
            // Retrieve the transactions list from the request
            List<Transaction> transactions = (List<Transaction>) request.getAttribute("transactions");
            
            if (transactions != null) {
                for (Transaction transaction : transactions) {
        %>
            <tr>
                <td><%= transaction.getTransactionDate() %></td>
                <td><%= transaction.getTransactionType() %></td>
                <td><%= transaction.getAmount() %></td>
            </tr>
        <% 
                } 
            } else {
        %>
            <tr>
                <td colspan="3">No transactions found.</td>
            </tr>
        <% 
            }
        %>
    </table>
    <a href="customerDashboard.jsp">Back to Dashboard</a>
</body>
</html>
