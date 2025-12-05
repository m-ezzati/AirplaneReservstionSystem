<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>User Dashboard | SkyBook Airlines</title>
  <link rel="stylesheet" href="/css/main.css">
</head>
<body>
<header>
  <h1>Welcome, ${user.username}</h1>
  <nav>
    <a href="<c:url value='/' />">Home</a>
    <a href="<c:url value='/search' />">Search Flights</a>
    <a href="<c:url value='/logout' />">Logout</a>
  </nav>
</header>

<main>
  <section class="flight-list">
    <h2>Your Booked Flights</h2>

    <c:if test="${empty bookings}">
      <p>You have no booked flights.</p>
    </c:if>

    <c:if test="${not empty bookings}">
      <table>
        <thead>
        <tr>
          <th>Airline</th>
          <th>Flight Number</th>
          <th>Origin</th>
          <th>Destination</th>
          <th>Departure -> Arrival</th>
          <th>Price</th>
          <th>Seats</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${bookings}" var="booking">
          <tr>
            <td>${booking.flight.airlineName}</td>
            <td>${booking.flight.flightNumber}</td>
            <td>${booking.flight.origin}</td>
            <td>${booking.flight.destination}</td>
            <td>${booking.flight.departureTime} -> ${booking.flight.arrivalTime}</td>
            <td>$${booking.flight.price}</td>
            <td>${booking.seatCount}</td>
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
</body>
</html>
