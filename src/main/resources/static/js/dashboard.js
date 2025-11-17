const map = L.map("map").setView([0, 0], 15);
L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png").addTo(map);

// Track markers by device ID
const markers = {};

function updateMarkers() {
    fetch("/api/location")
        .then(res => res.json())
        .then(devices => {
            if (!Array.isArray(devices)) return;

            devices.forEach(device => {
                const id = device.id;   // Make sure API returns 'id'
                const lat = device.latitude;
                const lng = device.longitude;

                if (!lat || !lng) return; // Skip invalid data

                if (markers[id]) {
                    markers[id].setLatLng([lat, lng]);
                } else {
                    markers[id] = L.marker([lat, lng]).addTo(map);
                }
            });

            // Remove markers that no longer exist (rescued devices)
            Object.keys(markers).forEach(id => {
                if (!devices.find(d => d.id === parseInt(id))) {
                    map.removeLayer(markers[id]);
                    delete markers[id];
                }
            });
        })
        .catch(err => console.error("Error fetching devices:", err));
}

// Initial load
updateMarkers();

// Refresh every 2 seconds
setInterval(updateMarkers, 2000);
