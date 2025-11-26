<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Airline Login | SkyBook</title>
    <link rel="stylesheet" href="/css/main.css">
</head>
<body>
<header>
    <h1>Airline Admin Login</h1>
    <nav>
        <a href="<c:url value='/' />">Home</a>
        <a href="<c:url value='/search' />">Search Flights</a>
    </nav>
</header>
<main>
    <section class="form-container">
        <h2>Login</h2>
        <form id="loginForm" action="${pageContext.request.contextPath}/do-login" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>

            <button type="submit" class="btn-primary">Login</button>
        </form>
        <p id="loginError" class="error-message"></p>
    </section>
</main>
<footer>
    <p>&copy; 2025 SkyBook Airlines. All rights reserved.</p>
</footer>

<script src="/js/login.js"></script>
</body>
</html>