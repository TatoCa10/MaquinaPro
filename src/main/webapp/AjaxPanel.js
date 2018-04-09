var denominaciones = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0];

$(document).ready(function () {

    $('#pedir').click(function () {


        var Ubicacion = comando;
        var Forma_Pago = denominaciones;
        var Plata_Pago = suma;
        denominaciones = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
        document.getElementById("launch").click();
        $.ajax({
            url: 'ServletTest',
            type: 'GET',
            data: {Ubicacion: Ubicacion, Forma_Pago: JSON.stringify(Forma_Pago), Plata_Pago: Plata_Pago},
            dataType: 'json',
            success: function (data) {

                console.log("DATOS SUCCESS");

                if (data.confirmacion === "1") {

                    $("#log").append("<p>No le alcanza: " + data.confirmacion + "</p> <br>");


                }
                if (data.confirmacion === "2") {

                    $("#log").append("<p>No le alcanza: " + data.confirmacion + "</p> <br>");
                    $("#hole").append("Vueltas: ");
                    $("#borde2").append("<center><div id='Producto'>" + data.producto + "</div>");

                    for (var i = 0; i < 10; i++) {
                        switch (i) {
                            case 0:
                                for (var j = 0; j < data.m50; j++) {
                                    $("#log").append("<img class='tabla' src='50.png' alt='50 COP'><br>");
                                }
                                break;
                            case 1:
                                for (var j = 0; j < data.m100; j++) {
                                    $("#log").append("<img class='tabla' src='100.png' alt='100 COP'><br>");
                                }
                                break;
                            case 2:
                                for (var j = 0; j < data.m200; j++) {
                                    $("#log").append("<img class='tabla' src='200.png' alt='100 COP'><br>");
                                }
                                break;
                            case 3:
                                for (var j = 0; j < data.m500; j++) {
                                    $("#log").append("<img class='tabla' src='500.png' alt='500 COP'><br>");
                                }
                                break;
                            case 4:
                                for (var j = 0; j < data.m1000; j++) {
                                    $("#log").append("<img class='tabla' src='1000.png' alt='1000 COP'><br>");
                                }
                                break;
                            case 5:
                                for (var j = 0; j < data.b2000; j++) {
                                    $("#log").append("<img class='tabla2' src='2000.png' alt='2000 COP'><br>");
                                }
                                break;
                            case 6:
                                for (var j = 0; j < data.b5000; j++) {
                                    $("#log").append("<img class='tabla2' src='5000.jpg' alt='5000 COP'><br>");
                                }
                                break;
                            case 7:
                                for (var j = 0; j < data.b10000; j++) {
                                    $("#log").append("<img class='tabla2' src='10000.jpg' alt='10000 COP'><br>");
                                }
                                break;
                            case 8:
                                for (var j = 0; j < data.b20000; j++) {
                                    $("#log").append("<img class='tabla2' src='20000.png' alt='20000 COP'><br>");
                                }
                                break;
                            case 9:
                                for (var j = 0; j < data.b50000; j++) {
                                    $("#log").append("<img class='tabla2' src='50000.jpg' alt='50000 COP'><br>");
                                }
                                break;
                        }
                    }

                }
                if (data.confirmacion === "3") {
                    
                    $("#log").append("<p>Error de transaccion: " + data.confirmacion + "</p> <br>");
                    
                }

                if (data.confirmacion === "4") {
                    $("#log").append("<p>No le alcanza: " + data.confirmacion + "</p> <br>");
                    $("#hole").append("Vueltas: 0");
                    $("#borde2").append("<center><div id='Producto'>" + data.producto + "</div>");
                }

                $("#log").append("<p>" + data.confirmacion + "</p><br>");
                /*document.getElementById("return").innerHTML = data.confirmacion;*/
            },
            error: function () {
                console.log("ERROR");
                $("#log").append("ERROR");
            }
        });
    });


});


function cargar(id) {
    contar();
    if (check === true) {
        comando = comando + id;
        document.getElementById("text").value = comando;
        actualizar();
    } else {
        alert('Maximo de digitos');
        alert(comando);
    }
}
function contar() {
    if (comando.length <= 2) {
        check = true;
    } else {
        check = false;
    }
}
function actualizar() {
    if (check === true) {
        document.getElementById("launch").click();
    }
    contar();
}


function respuesta(plata) {
    switch (plata) {
        case 50:
            denominaciones[0] = denominaciones[0] + 1;
            break;
        case 100:
            denominaciones[1] = denominaciones[1] + 1;
            break;
        case 200:
            denominaciones[2] = denominaciones[2] + 1;
            break;
        case 500:
            denominaciones[3] = denominaciones[3] + 1;
            break;
        case 1000:
            denominaciones[4] = denominaciones[4] + 1;
            break;
        case 2000:
            denominaciones[5] = denominaciones[5] + 1;
            break;
        case 5000:
            denominaciones[6] = denominaciones[6] + 1;
            break;
        case 10000:
            denominaciones[7] = denominaciones[7] + 1;
            break;
        case 20000:
            denominaciones[8] = denominaciones[8] + 1;
            break;
        case 50000:
            denominaciones[9] = denominaciones[9] + 1;
            break;
    }

    suma = suma + plata;
    document.getElementById("dinero").value = suma;

}


function cancelar() {

    document.getElementById("text").value = "CODE";
    document.getElementById("launch").click();
    document.getElementById("dinero").value = "$0";
    comando = "";
    suma = 0;
    console.log(denominaciones);
    document.getElementById("log").innerHTML = "";

    var comprobar = 0;
    for (var i = 0; i < denominaciones.length; i++) {
        comprobar = comprobar + denominaciones[i];
    }
    if (comprobar !== 0) {
        for (var i = 0; i < denominaciones.length; i++) {
            switch (i) {
                case 0:
                    for (var j = 0; j < denominaciones[i]; j++) {
                        $("#log").append("<img class='tabla' src='50.png' alt='50 COP'><br>");
                    }
                    break;
                case 1:
                    for (var j = 0; j < denominaciones[i]; j++) {
                        $("#log").append("<img class='tabla' src='100.png' alt='100 COP'><br>");
                    }
                    break;
                case 2:
                    for (var j = 0; j < denominaciones[i]; j++) {
                        $("#log").append("<img class='tabla' src='200.png' alt='100 COP'><br>");
                    }
                    break;
                case 3:
                    for (var j = 0; j < denominaciones[i]; j++) {
                        $("#log").append("<img class='tabla' src='500.png' alt='500 COP'><br>");
                    }
                    break;
                case 4:
                    for (var j = 0; j < denominaciones[i]; j++) {
                        $("#log").append("<img class='tabla' src='1000.png' alt='1000 COP'><br>");
                    }
                    break;
                case 5:
                    for (var j = 0; j < denominaciones[i]; j++) {
                        $("#log").append("<img class='tabla2' src='2000.png' alt='2000 COP'><br>");
                    }
                    break;
                case 6:
                    for (var j = 0; j < denominaciones[i]; j++) {
                        $("#log").append("<img class='tabla2' src='5000.jpg' alt='5000 COP'><br>");
                    }
                    break;
                case 7:
                    for (var j = 0; j < denominaciones[i]; j++) {
                        $("#log").append("<img class='tabla2' src='10000.jpg' alt='10000 COP'><br>");
                    }
                    break;
                case 8:
                    for (var j = 0; j < denominaciones[i]; j++) {
                        $("#log").append("<img class='tabla2' src='20000.png' alt='20000 COP'><br>");
                    }
                    break;
                case 9:
                    for (var j = 0; j < denominaciones[i]; j++) {
                        $("#log").append("<img class='tabla2' src='50000.jpg' alt='50000 COP'><br>");
                    }
                    break;
            }
        }
    }
    for (var i = 0; i < denominaciones.length; i++) {
        denominaciones[i] = 0;
    }
}



//function dispensarProd() {
//
//    var ubicacion = $('#text').val();
//
//    $.ajax({
//        url: 'ServletTest',
//        type: 'GET',
//        data: {ubicacion: ubicacion},
//        dataType: 'json',
//        success: function (data) {
//            console.log("Respuesta Recibida");
//            var ubicacion = $('#text').val(data.confirmacion);
//        },
//        error: function () {
//            console.log("Se jodio papá");
//            $('#log').val("ERROR FATAL");
//        }
//    });
//}




function ingresarAdmin() {
    document.getElementById("Input_Admin").style.visibility = 'visible';
}
function testPass(clave) {
    if (clave === "101099") {
        location.reload();
        location.href = "MainAdmin.html";
    } else {
        document.getElementById("log").append("Error de llave");
    }
}