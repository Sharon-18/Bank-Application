<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Registration</title>
    <style>
        body { font-family: Arial, sans-serif; }
        .container { max-width: 500px; margin: auto; padding: 20px; border: 1px solid #ccc; border-radius: 5px; }
        input[type="text"], input[type="number"], input[type="date"], input[type="email"], select { width: 100%; padding: 10px; margin: 5px 0 10px 0; }
        input[type="submit"] { width: 100%; padding: 10px; background-color: #4CAF50; color: white; border: none; border-radius: 5px; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Customer Registration</h2>
        <form action="CustomerRegistrationServlet" method="post">
            <label for="fullName">Full Name:</label>
            <input type="text" id="fullName" name="fullName" required>
            <label for="address">Address:</label>
            <input type="text" id="address" name="address" required>
            <label for="mobileNo">Mobile No:</label>
            <input type="text" id="mobileNo" name="mobileNo" required>
            <label for="emailId">Email ID:</label>
            <input type="email" id="emailId" name="emailId" required>
            <label for="accountType">Type of Account:</label>
            <select id="accountType" name="accountType" required>
                <option value="Saving Account">Saving Account</option>
                <option value="Current Account">Current Account</option>
            </select>
            <label for="initialBalance">Initial Balance (min 1000):</label>
            <input type="number" id="initialBalance" name="initialBalance" min="1000" required>
            <label for="dateOfBirth">Date of Birth:</label>
            <input type="date" id="dateOfBirth" name="dateOfBirth" required>
            <label for="idProof">ID Proof:</label>
            <input type="text" id="idProof" name="idProof" required>
            <input type="submit" value="Register">
        </form>
    </div>
</body>
</html>
