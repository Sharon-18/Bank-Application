<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration Successful</title>
    <style>
        body { font-family: Arial, sans-serif; }
        .container { max-width: 500px; margin: auto; padding: 20px; border: 1px solid #ccc; border-radius: 5px; text-align: center; }
        .info { margin: 20px 0; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Registration Successful</h2>
        <div class="info">
            <p>Account No: <strong><%= request.getAttribute("accountNo") %></strong></p>
            <p>Temporary Password: <strong><%= request.getAttribute("tempPassword") %></strong></p>
        </div>
        <p>Please provide these details to the customer.</p>
        <a href="adminDashboard.jsp">Back to Dashboard</a>
    </div>
</body>
</html>
