<!DOCTYPE html>
<html>
<head>
    <title>Customer Login</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
    <h2>Customer Login</h2>
    <form action="customerLogin" method="post">
        <label for="accountNo">Account No:</label>
        <input type="text" id="accountNo" name="accountNo" required><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br>
        <input type="submit" value="Login">
    </form>
    <c:if test="${param.error != null}">
        <p style="color:red;">${param.error}</p>
    </c:if>
</body>
</html>
