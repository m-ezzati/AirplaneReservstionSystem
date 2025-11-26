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
        <div class="filters">
            <label for="sortSelect">Sort by:</label>
            <select id="sortSelect">
                <option value="">None</option>
                <option value="price-asc">Price</option>
<%--                <option value="price-desc">Price</option>--%>
                <option value="airline-asc">Airline</option>
<%--                <option value="airline-desc">Airline</option>--%>
                <option value="route-asc">Route</option>
<%--                <option value="route-desc">Route</option>--%>
            </select>
        </div>


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
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody id="flightsTbody">
                    <c:forEach items="${results}" var="f">
                        <tr>
                            <td>${f.airlineName}</td>
                            <td>${f.flightNumber}</td>
                            <td>${f.origin}</td>
                            <td>${f.destination}</td>
                            <td>${f.departureTime}</td>
                            <td>$${f.price}</td>
                            <td><button class="btn-primary" onclick="bookFlight('${f.id}')">Book</button></td>

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
