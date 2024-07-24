<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer Registration Success</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <script type="text/javascript">
        // Redirect to adminDashboard.jsp after 5 seconds
        setTimeout(function() {
            window.location.href = 'adminDashboard.jsp';
        }, 5000); // 5000 milliseconds = 5 seconds
    </script>
</head>
<body>
    <h1>Registration Successful!</h1>
    <p>Your account has been created successfully.</p>
    <p>Account Number: ${accountNo}</p>
    <p>Temporary Password: ${tempPassword}</p>
    <p>Please change your password after your first login.</p>
    <p>You will be redirected to the dashboard in 5 seconds.</p>
    <p>If you are not redirected automatically, click the link below:</p>
    <p><a href="adminDashboard.jsp">Back to Dashboard</a></p>
</body>
</html>
