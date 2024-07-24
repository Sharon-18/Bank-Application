<!DOCTYPE html>
<html>
<head>
    <title>Customer Dashboard</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
    <h2>Welcome, ${customer.fullName}</h2>
    <p>Account No: ${customer.accountNo}</p>
    <p>Balance: ${customer.balance}</p>
    <a href="TransactionHistoryServlet">View Transaction History</a><br>
    <a href="deposit.jsp">Deposit</a><br>
    <a href="withdraw.jsp">Withdraw</a><br>
    <form action="customerLogout" method="post">
        <input type="submit" value="Logout">
    </form>
</body>
</html>
