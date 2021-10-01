function handleVehicles(vehicleArray) {
    var content = $('#searchResult');
    
    $.each(vehicleArray, function(index, vehicle) {
        console.log(vehicle.vin);
        
        var row = '<div class="container bg-light p-3 my-3 border">';

        // Make, Model, Year 
        row += '<div class="row">';
        row += '<h5 class="col-12 pull-left my-2">' 
                + vehicle.model.year + ' '
                + vehicle.model.make.name + ' '
                + vehicle.model.name;
        row += '</div>';
        
        row += '<div class="row">';
        
        // Picture
        row += '<img class="col-2" src="../img/carPlaceholder.png" style="max-width: 10rem;"/>';
        
        // BodyStyle, Transmission, ExteriorColor
        row += '<div class="col-4">';
        row += tableEntry('Body Style', vehicle.bodyStyle);
        row += tableEntry('Trans', vehicle.trim.transmission);
        row += tableEntry('Color', vehicle.trim.exteriorColor);
        row += '</div>';
        
        // InteriorColor, Mileage, VIN
        row += '<div class="col-3">';
        row += tableEntry('Interior', vehicle.trim.interiorColor);
        row += tableEntry('Mileage', vehicle.vehicleCondition.mileage 
                + ' ' + vehicle.vehicleCondition.unit);
        row += tableEntry('VIN #', vehicle.vin);
        row += '</div>';
        
        // SalesPrice, MSRP
        row += '<div class="col-3">';
        row += tableEntry('Sale Price', '$ ' + vehicle.salesPrice);
        row += tableEntry('MSRP', '$ ' + vehicle.msrp);
        row += '<div class="row justify-content-center">';
        row += '<button  type="button" onclick="showVehicleDetailDiv" id="detailButton" \n\
                        class="btn btn-primary pull-right">Details';
        row += '</button>';
        row += '</div>';
        row += '</div>';
        
        row += '</div>';
        
        content.append(row);
    });
}

function tableEntry(title, value) {
    var row = '<div class="row">';
    row += '<div class="col-5 font-weight-bold text-right">';
    row += title + ':';
    row += '</div>';
    row += '<div class="col-7">';
    row += value;
    row += '</div>';
    row += '</div>';
    
    return row;
}



function detailButtonListener() {

    $('#detailButton').click(function(event) {

      //$('#errorMessagesMain').empty();
	    
      var vin = $('#vin').val();
	    
      vehicleDetail(vin);
      }
    );
}

function showVehicleDetailDiv(){
    $('#searchResult').hide();
    $('#vehicleDetail').show();
}
