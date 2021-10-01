$(document).ready(function() {
    loadVehicles();
});

function loadVehicles() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/api/inventory/used',
        success: handleVehicles,
        error: function() {
            
        }
    });
}

