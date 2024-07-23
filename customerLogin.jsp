<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Login</title>
    <style>
        body { font-family: Arial, sans-serif; }
        .container { max-width: 500px; margin: auto; padding: 20px; border: 1px solid #ccc; border-radius: 5px; }
        input[type="text"], input[type="password"] { width: 100%; padding: 10px; margin: 5px 0 10px 0; }
        input[type="submit"] { width: 100%; padding: 10px; background-color: #4CAF50; color: white; border: none; border-radius: 5px; }
        .error-message { color: red; margin: 10px 0; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Customer Login</h2>
        <form action="CustomerLoginServlet" method="post">
            <label for="accountNo">Account No:</label>
            <input type="text" id="accountNo" name="accountNo" required>
            
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
            
            <input type="submit" value="Login">
            
            <% 
                String errorMessage = (String) request.getAttribute("errorMessage");
                if (errorMessage != null) {
            %>
                <p class="error-message"><%= errorMessage %></p>
            <% 
                }
            %>
        </form>
    </div>
</body>
</html>
