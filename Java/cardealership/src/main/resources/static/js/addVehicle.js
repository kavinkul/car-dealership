$(document).ready(function() {
    saveVehicle();
});

function saveVehicle() {
    $('#saveButton').click(function(event) {
        // Still need to check validation
        // var hasvalidationErrors = ...
        
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/api/vehicle',
            data: JSON.stringify({
                vin: $('#vin').val(),
                make: $('#make').val(),
                model: $('#model').val(),
                body: $('#bodyStyle').val(),
                type: $('#type').val(),
                color: $('#color').val(),
                interior: $('#interior').val(),
                mileage: $('#mileage').val(),
                msrp: $('#msrp').val(),
                salePrice: $('#salePrice').val(),
                description: $('#description').val(),
                picture: $('#filePicture').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json',
            success: function() {
                alert('insert success');
            },
            error: function() {
                alert('insert fail');
            }
        });
    });
}