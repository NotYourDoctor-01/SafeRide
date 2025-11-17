const map = L.map("map").setView([0, 0], 15);
L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png").addTo(map);

let marker = L.marker([0, 0]).addTo(map);

function updateMarker() {
    fetch("/api/location")
        .then(res => res.json())
        .then(loc => {
            if (!loc || loc.latitude === undefined) return;

            const lat = loc.latitude;
            const lng = loc.longitude;

            marker.setLatLng([lat, lng]);
            map.setView([lat, lng]);
        })
        .catch(err => console.error("Error fetching location:", err));
}

// Load immediately
updateMarker();

// Then refresh every 5 seconds
setInterval(updateMarker, 5000);
