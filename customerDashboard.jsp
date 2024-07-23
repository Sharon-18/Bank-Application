<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Dashboard</title>
    <style>
        body { font-family: Arial, sans-serif; }
        .container { max-width: 800px; margin: auto; padding: 20px; }
        .button { padding: 10px 15px; background-color: #4CAF50; color: white; text-decoration: none; border-radius: 5px; display: inline-block; margin-right: 10px; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Customer Dashboard</h2>
        <p>Account No: <%= session.getAttribute("accountNo") %></p>
        <p>Balance: <%= session.getAttribute("balance") %></p>
        <a href="transactionHistory.jsp" class="button">View Transactions</a>
        <a href="deposit.jsp" class="button">Deposit</a>
        <a href="withdraw.jsp" class="button">Withdraw</a>
        <form action="LogoutServlet" method="post" style="display: inline;">
            <input type="submit" value="Logout" class="button" style="background-color: #f44336;">
        </form>
    </div>
</body>
</html>
