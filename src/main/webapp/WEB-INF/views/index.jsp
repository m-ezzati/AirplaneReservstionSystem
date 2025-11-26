<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Airline Reservation System</title>
    <link rel="stylesheet" href="/css/main.css">

</head>
<body>
<header>
    <h1>Welcome to SkyBook</h1>
    <nav>
        <a href="<c:url value='/login' />">Airline Login</a>
        <a href="<c:url value='/search' />">Search Flights</a>
    </nav>
</header>
<main>
    <section class="intro">
        <h2>Your trusted partner for flight reservations</h2>
        <p>Search and book flights with ease. Manage your airline routes from the dashboard.</p>
        <a href="<c:url value='/search' />" class="btn-primary">Start Booking</a>
    </section>
</main>
<footer>
    <p>&copy; All rights reserved.</p>
</footer>
</body>
</html>
