$(document).ready(function () {


    $.ajax({
        url: 'ServletCargaLista',
        type: 'get',
        dataType: 'json',
        success: function (data) {
            console.log("DATOS SUCCESS");
            for (var i = 0; i < data.length; i++) {
            $("#llegada").append("<div class='module'><img src='"+data.casillas[i].source+"' alt='"+data.casillas[i].nombre+"'><div>"+data.casillas[i].ubicacion+"</div></div>");
                
            }
           // $("#llegada").append("<div class='module' style='height:140px'>" + data.test + "<div>D4</div></div>");




        },
        error: function () {
            console.log("ERROR");
        }
    });

});

