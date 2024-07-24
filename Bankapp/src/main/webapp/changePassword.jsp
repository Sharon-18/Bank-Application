<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Change Password</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
    <div class="container">
        <h2>Change Password</h2>

        <!-- Display error message -->
        <c:if test="${not empty error}">
            <div class="message error">
                <c:out value="${error}"/>
            </div>
        </c:if>

        <!-- Display success message -->
        <c:if test="${not empty message}">
            <div class="message success">
                <c:out value="${message}"/>
            </div>
        </c:if>

        <form action="changePassword" method="post">
            <div class="form-group">
                <label for="newPassword">New Password:</label>
                <input type="password" id="newPassword" name="newPassword" required>
            </div>
            <div class="form-group">
                <label for="confirmPassword">Confirm Password:</label>
                <input type="password" id="confirmPassword" name="confirmPassword" required>
            </div>
            <button type="submit">Change Password</button>
        </form>
    </div>
</body>
</html>
