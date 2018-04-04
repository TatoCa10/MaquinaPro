$(document).ready(function () {


    $.ajax({
        url: 'ServletCargaLista',
        type: 'get',
        dataType: 'json',
        success: function (data) {
            console.log("DATOS SUCCESS");
            console.log("voy a entrar al for");
            for (var i = 0; i < data.casillas.length; i++) {
                console.log("ENTRO AL FOR");
                console.log(data.casillas[i].nombre);
                console.log(data.casillas[i].precio);
                console.log(data.casillas[i].ubicacion);
                
//            $("#llegada").append(
//                    
//                    "<div class='module'><p>"+data.casillas[i].nombre+"'</p><div>"+data.casillas[i].ubicacion+"</div></div>"
//                    
//                    
//                    );
            
            $("#llegada").append(
                    
                        "<div class='module'><center><p>"+data.casillas[i].nombre+"'</p><div id='Ubicacion'>"+data.casillas[i].ubicacion+"</div><div id='Precio'>"+data.casillas[i].precio+"</div></center></div>");
                
            }
           // $("#llegada").append("<div class='module' style='height:140px'>" + data.test + "<div>D4</div></div>");




        },
        error: function () {
            console.log("ERROR");
        }
    });

});

