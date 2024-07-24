<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Logout</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<%
    // Redirect to the AdminLogoutServlet to handle the logout process
    response.sendRedirect("adminLogout");
%>
</body>
</html>
