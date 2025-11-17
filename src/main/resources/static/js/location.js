function sendLocation() {
    if (!navigator.geolocation) {
        alert("Geolocation not supported.");
        return;
    }

    navigator.geolocation.getCurrentPosition(
        pos => {
            document.getElementById("lat").value = pos.coords.latitude;
            document.getElementById("lon").value = pos.coords.longitude;

            document.forms[0].submit();
        },
        err => {
            alert("Failed to get location: " + err.message);
        }
    );
}
