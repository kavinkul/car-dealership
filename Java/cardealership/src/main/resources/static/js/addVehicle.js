$(document).ready(function() {
    saveVehicle();
});

function saveVehicle() {
    $('#saveButton').click(function(event) {
        // Still need to check validation
        // var hasvalidationErrors = ...
        
        $.ajax({
            type: 'POST',
            url: '',
            data: JSON.stringify({
                vin: $('').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json',
            success: function() {
                
            },
            error: function() {
                
            }
        });
    });
}