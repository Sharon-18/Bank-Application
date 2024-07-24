<!-- customerDashboard.jsp -->
<!DOCTYPE html>
<html>
<head>
    <title>Customer Dashboard</title>
    <link rel="stylesheet" href="styles.css"> <!-- Link to your CSS file -->
    <script>
        function confirmLogout() {
            return confirm("Are you sure you want to logout?");
        }
    </script>
</head>
<body>
    <h1>Welcome, ${customer.fullName}!</h1>
    <!-- Other dashboard content here -->

    <!-- Logout Button -->
    <form action="customerLogout" method="get" onsubmit="return confirmLogout();">
        <button type="submit" class="logout-button">Logout</button>
    </form>
</body>
</html>
