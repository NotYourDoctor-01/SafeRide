const map = L.map("map").setView([0, 0], 15);
L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png").addTo(map);

// Track markers by device ID
const markers = {};

function updateMarkers() {
    fetch("/api/location")
        .then(res => res.json())
        .then(devices => {
            if (!devices || !Array.isArray(devices)) return;

            devices.forEach(device => {
                const id = device.deviceId;

                if (!device.rescued) {
                    if (markers[id]) {
                        markers[id].setLatLng([device.latitude, device.longitude]);
                    } else {
                        markers[id] = L.marker([device.latitude, device.longitude]).addTo(map);
                    }
                } else {
                    if (markers[id]) {
                        map.removeLayer(markers[id]);
                        delete markers[id];
                    }
                }
            });
        })
        .catch(err => console.error("Error fetching devices:", err));
}

// Load immediately, then refresh every 2 seconds
updateMarkers();
setInterval(updateMarkers, 2000);
