<!DOCTYPE html>
<html>
<head>
    <title>Transaction History</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
    <h2>Transaction History</h2>
    <table>
        <tr>
            <th>Date</th>
            <th>Type</th>
            <th>Amount</th>
        </tr>
        <c:forEach var="transaction" items="${transactions}">
            <tr>
                <td>${transaction.transactionDate}</td>
                <td>${transaction.transactionType}</td>
                <td>${transaction.amount}</td>
            </tr>
        </c:forEach>
    </table>
    <a href="customerDashboard.jsp">Back to Dashboard</a>
</body>
</html>
