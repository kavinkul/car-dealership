$(document).ready(function() {
    loadVehicles();
});

function loadVehicles() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/api/inventory/new',
        success: handleVehicles,
        error: function() {
            
        }
    });
}

function loadAVehicle(vin) {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/api/inventory/detail/' + vin,
        success: showVehicleDetail,
        error: function(){
            
        }
        
    });
}

