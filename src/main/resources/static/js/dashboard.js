    document.querySelector("form").addEventListener("submit", function (e) {

    const departure = document.querySelector("input[name='departureTime']").value;
    const arrival = document.querySelector("input[name='arrivalTime']").value;
    const price = document.querySelector("input[name='price']").value;

    let errors = [];

    if (departure && arrival && new Date(departure) >= new Date(arrival)) {
    errors.push("Departure time must be earlier than arrival time.");
}

    if (price !== "" && Number(price) <= 0) {
    errors.push("Price must be greater than zero.");
}

    if (errors.length > 0) {
    e.preventDefault();

    const msg = document.getElementById("addFlightMsg");
    msg.style.color = "red";
    msg.innerHTML = errors.join("<br>");
}
});
