<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Flight Results | SkyBook</title>
    <link rel="stylesheet" href="/css/main.css">
</head>
<body>
<header>
    <h1>Flight Results</h1>
    <nav>
        <a href="<c:url value='/' />">Home</a>
        <a href="<c:url value='/search' />">New Search</a>
        <a href="<c:url value='/login' />">Airline Login</a>
    </nav>
</header>

<main>
    <section class="flight-results">

        <c:if test="${empty results}">
            <div id="noResults">
                No flights found.
            </div>
        </c:if>

        <c:if test="${not empty results}">
            <table>
                <thead>
                <tr>
                    <th data-type="string">Airline</th>
                    <th data-type="string">Flight Number</th>
                    <th data-type="string">Origin</th>
                    <th data-type="string">Destination</th>
                    <th data-type="datetime">Date & Time</th>
                    <th data-type="number">Price</th>
                    <th data-type="number">Available Seats</th>
                    <th>Action</th>
                </tr>
                </thead>

                <tbody id="flightsTbody">
                <c:forEach items="${results}" var="f">
                    <tr>
                        <td>${f.airlineName()}</td>
                        <td>${f.flightNumber()}</td>
                        <td>${f.origin()}</td>
                        <td>${f.destination()}</td>
                        <td>${f.departureTime()}</td>
                        <td>$${f.price()}</td>

                        <td>
                                ${f.totalSeats() - f.reservedSeats()}
                        </td>

                        <td>
                            <c:choose>
                                <c:when test="${f.reservedSeats() < f.totalSeats()}">
                                    <form action="<c:url value='/booking/add' />" method="post">
                                        <input type="hidden" name="flightId" value="${f.id()}" />
                                        <button type="submit" class="btn-primary">Book</button>
                                    </form>
                                </c:when>

                                <c:otherwise>
                                    <button class="btn-primary" style="background: gray;" disabled>Full</button>
                                </c:otherwise>
                            </c:choose>
                        </td>
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

<script src="/js/results.js"></script>
</body>
</html>
