// dashboard.js
const map = L.map("map").setView([13.0, 122.0], 6);
L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png").addTo(map);

const markers = {};

// small helper to create colored SVG icon
function createIcon(color) {
    return L.divIcon({
        className: 'custom-marker',
        html: `<svg xmlns="http://www.w3.org/2000/svg" width="28" height="28" viewBox="0 0 24 24">
            <path fill="${color}" d="M12 2C8 2 5 5 5 9c0 6.5 7 13 7 13s7-6.5 7-13c0-4-3-7-7-7z"/>
            <circle cx="12" cy="9" r="2.5" fill="white"/>
        </svg>`
    });
}

function updateMarkers() {
    fetch("/api/location")
        .then(res => res.json())
        .then(devices => {
            if (!Array.isArray(devices)) return;

            devices.forEach(device => {
                if (!device.deviceId) return;
                const id = device.deviceId;
                const lat = device.latitude;
                const lng = device.longitude;

                if (lat == null || lng == null) return;

                const color = device.impactDetected ? '#ff3b30' : '#007bff';
                const icon = createIcon(color);
                const popupText = `Device ${id} — HR: ${device.heartRate ?? 'N/A'} bpm — SpO₂: ${device.spo2 ?? 'N/A'}%`;

                if (markers[id]) {
                    markers[id].setLatLng([lat, lng]);
                    markers[id].setIcon(icon);
                    markers[id].setPopupContent(popupText);
                } else {
                    const m = L.marker([lat, lng], { icon }).addTo(map).bindPopup(popupText);
                    markers[id] = m;
                }
            });

            // Remove markers for devices no longer returned
            Object.keys(markers).forEach(id => {
                if (!devices.find(d => d.deviceId === id)) {
                    map.removeLayer(markers[id]);
                    delete markers[id];
                }
            });
        })
        .catch(err => console.error("Error fetching devices:", err));
}

updateMarkers();
setInterval(updateMarkers, 2000);
