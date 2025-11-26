<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Search Flights | SkyBook</title>
    <link rel="stylesheet" href="/css/main.css">
</head>
<body>
<header>
    <h1>Search Flights</h1>
    <nav>
        <a href="<c:url value='/' />">Home</a>
        <a href="<c:url value='/login' />">Airline Login</a>
    </nav>
</header>

<main>
    <section class="form-container">
        <form id="searchForm" name="searchForm" action="<c:url value='/flights/search' />" method="get" >
            <label for="origin">Origin:</label>
            <input type="text" id="origin" name="origin" placeholder="e.g., Tehran" required>

            <label for="destination">Destination:</label>
            <input type="text" id="destination" name="destination" placeholder="e.g., Istanbul" required>

            <label for="departureDate">Departure date (optional):</label>
            <input type="date" id="departureDate" name="departureDate">

            <button type="submit" class="btn-primary">Search</button>
        </form>
    </section>
</main>

<footer>
    <p>&copy; 2025 SkyBook Airlines. All rights reserved.</p>
</footer>

<script src="/js/search.js"></script>
</body>
</html>