<!DOCTYPE html>
<html>
<head>
    <title>Edit Customer</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
    <h2>Edit Customer</h2>
    <form action="updateCustomer" method="post">
        <input type="hidden" name="customerId" value="${customer.customerId}">
        <label for="fullName">Full Name:</label>
        <input type="text" id="fullName" name="fullName" value="${customer.fullName}" required><br>
        <label for="address">Address:</label>
        <input type="text" id="address" name="address" value="${customer.address}" required><br>
        <label for="mobileNo">Mobile No:</label>
        <input type="text" id="mobileNo" name="mobileNo" value="${customer.mobileNo}" required><br>
        <label for="emailId">Email ID:</label>
        <input type="email" id="emailId" name="emailId" value="${customer.emailId}" required><br>
        <label for="accountType">Account Type:</label>
        <select id="accountType" name="accountType" required>
            <option value="Saving" ${customer.accountType == 'Saving' ? 'selected' : ''}>Saving Account</option>
            <option value="Current" ${customer.accountType == 'Current' ? 'selected' : ''}>Current Account</option>
        </select><br>
        <label for="dob">Date of Birth:</label>
        <input type="date" id="dob" name="dob" value="${customer.dob}" required><br>
        <label for="idProof">ID Proof:</label>
        <input type="text" id="idProof" name="idProof" value="${customer.idProof}" required><br>
        <input type="submit" value="Update">
    </form>
    <a href="viewCustomers.jsp">Back to Customer List</a>
</body>
</html>