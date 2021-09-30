$(document).ready(function() {
    loadVehicles();
});

function loadVehicles() {
    var content = $('#content');
    
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/api/vehicle',
        success: function(vehicleArray) {
            
        },
        error: function() {
            
        }
    });
}