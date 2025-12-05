<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard | SkyBook Airlines</title>
    <link rel="stylesheet" href="/css/main.css">
</head>
<body>
<header>
    <h1>Airline Dashboard</h1>
    <nav>
        <a href="<c:url value='/' />">Home</a>
        <a href="<c:url value='/search' />">Search Flights</a>
    </nav>
</header>

<main>

    <section class="form-container">
        <form:errors path="departureTime" cssClass="error"/>
        <form:errors path="price" cssClass="error"/>
        <h2>Add New Flight</h2>
        <form action="<c:url value='/admin/dashboard/add-flight' />" method="post">

            <label for="flightNumber">Airline Number:</label>
            <input type="text" name="flightNumber" required>

            <label for="airlineName">Airline Name:</label>
            <input type="text" name="airlineName" required>

            <label for="origin">Origin:</label>
            <input type="text" name="origin" required>

            <label for="destination">Destination:</label>
            <input type="text" name="destination" required>

            <label for="date">Departure Time:</label>
            <input type="datetime-local" name="departureTime" required>

            <label for="date">Arrival Time:</label>
            <input type="datetime-local" name="arrivalTime" required>

            <label for="price">Price ($):</label>
            <input type="number" name="price" min="0" step="0.01" required>

            <label for="totalSeats">Total Seats:</label>
            <input type="number" name="totalSeats" min="1" required>

            <label for="flightType">Class:</label>
            <select name="flightType">
                <option value="ECONOMY">Economy</option>
                <option value="BUSINESS">Business</option>
                <option value="FIRST_CLASS">First</option>
            </select>

            <button type="submit" class="btn-primary">Add Flight</button>
        </form>
        <p id="addFlightMsg" class="success-message"></p>
    </section>

    <section class="flight-list">
        <h2>Current Flights</h2>

        <c:if test="${empty flights}">
            <p>No flights available.</p>
        </c:if>

        <c:if test="${not empty flights}">
            <table>
                <thead>
                <tr>
                    <th>Flight Number</th>
                    <th>Route</th>
                    <th>Departure -> Arrival</th>
                    <th>Price & Class</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${flights}" var="flight">
                    <tr>
                        <td>${flight.flightNumber()}</td>
                        <td>${flight.origin()} -> ${flight.destination()}</td>
                        <td>${flight.departureTime()} -> ${flight.arrivalTime()}</td>
                        <td>$${flight.price()} - ${flight.flightType()}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
    </section>
</main>

<footer>
    <p>&copy; 2025 SkyBook Airlines. All rights reserved.</p>
</footer>

<script src="/js/dashboard.js"></script>
</body>
</html>