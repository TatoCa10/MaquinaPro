$(document).ready(function () {

});

//function cargarValorADisplay(ubicacion) {
//    console.log("Valor A Display");
//    switch (ubicacion) {
//        case "A1":
//            document.getElementById("text").value = "2000";
//            document.getElementById("launch").click();
//            break;
//        case "B1":
//            document.getElementById("text").value = "3500";
//            document.getElementById("launch").click();
//            break;
//        case "C1":
//            document.getElementById("text").value = "70000";
//            document.getElementById("launch").click();
//            break;
//        case "A2":
//            document.getElementById("text").value = "800";
//            document.getElementById("launch").click();
//            break;
//        case "B2":
//            document.getElementById("text").value = "1300";
//            document.getElementById("launch").click();
//            break;
//        case "C2":
//            document.getElementById("text").value = "600";
//            document.getElementById("launch").click();
//            break;
//        case "A3":
//            document.getElementById("text").value = "800";
//            document.getElementById("launch").click();
//            break;
//        case "B3":
//            document.getElementById("text").value = "1500";
//            document.getElementById("launch").click();
//            break;
//    }
//
//
//}

//function Pago(ubicacion) {
//    var dinero = $('#dinero').val();
//    switch (ubicacion) {
//        case "A1":
//            var saldo = dinero - 2000;
//            if (saldo >= 0) {
//                document.getElementById("text").value = "saldo";
//                $("#borde2").append("<img src='cocacola.png' alt='CocaCola'><br>");
//            } else {
//                document.getElementById("text").value = "FAIL";
//                cancelar();
//            }
//            break;
//        case "B1":
//            var saldo = dinero - 3500;
//            if (saldo >= 0) {
//                document.getElementById("text").value = "saldo";
//                $("#borde2").append("<img src='pringles.png' alt='Pringles'><br>");
//            } else {
//                document.getElementById("text").value = "FAIL";
//                cancelar();
//            }
//            break;
//        case "C1":
//            var saldo = dinero - 70000;
//            if (saldo >= 0) {
//                document.getElementById("text").value = "saldo";
//                $("#borde2").append("<img src='chocoramo.png' alt='Chocorramo'><br>");
//            } else {
//                document.getElementById("text").value = "FAIL";
//                cancelar();
//            }
//            break;
//        case "A2":
//            var saldo = dinero - 800;
//            if (saldo >= 0) {
//                document.getElementById("text").value = "saldo";
//                $("#borde2").append("<img src='clubsocial.png' alt='Club Social'><br>");
//            } else {
//                document.getElementById("text").value = "FAIL";
//                cancelar();
//            }
//            break;
//        case "B2":
//            var saldo = dinero - 1300;
//            if (saldo >= 0) {
//                document.getElementById("text").value = "saldo";
//                $("#borde2").append("<img src='hit.png' alt='Jugo Hit'><br>");
//            } else {
//                document.getElementById("text").value = "FAIL";
//                cancelar();
//            }
//            break;
//        case "C2":
//            var saldo = dinero - 600;
//            if (saldo >= 0) {
//                document.getElementById("text").value = "saldo";
//                $("#borde2").append("<img src='achiras.png' alt='Achiras Ramo'><br>");
//            } else {
//                document.getElementById("text").value = "FAIL";
//                cancelar();
//            }
//            break;
//        case "A3":
//            var saldo = dinero - 800;
//            if (saldo >= 0) {
//                document.getElementById("text").value = "saldo";
//                $("#borde2").append("<img src='manicero.png' alt='Mani Manicero'><br>");
//            } else {
//                document.getElementById("text").value = "FAIL";
//                cancelar();
//            }
//            break;
//        case "B3":
//            var saldo = dinero - 1500;
//            if (saldo >= 0) {
//                document.getElementById("text").value = "saldo";
//                $("#borde2").append("<img src='Boliqueso.png' alt='Boliqueso'><br>");
//            } else {
//                document.getElementById("text").value = "FAIL";
//                cancelar();
//            }
//            break;
//
//    }
//}




function servicio() {
    var saldo = 0;
    var operacion = 0;
    saldo = document.getElementById("dinero").value;
    console.log("Saldo: " + saldo);
    console.log(comando);
    if (comando === "A1") {
        if (cantidades[0] > 0) {
            operacion = saldo - 2000;
            console.log("Operacion: " + operacion);
            if (operacion >= 0) {
                $("#hole").append("Vueltas: " + operacion);
                $("#borde2").append("<img src='cocacola.png' alt='CocaCola' width='200px' height='200px'><br>");
                console.log("Entro a la condicion");
                cantidades[0] = cantidades[0] - 1;
            } else {
                $("#hole").append("Saldo Insuficiente<br>");
            }
        } else {
            $("#hole").append("No hay productos<br>");
        }
    }

    if (comando === "B1") {
        if (cantidades[1] > 0) {
            operacion = saldo - 3500;
            console.log("Operacion: " + operacion);
            if (operacion >= 0) {
                $("#hole").append("Vueltas: " + operacion);
                $("#borde2").append("<img src='pringles.png' alt='Pringles' width='200px' height='200px'><br>");
                console.log("Entro a la condicion");
                cantidades[1] = cantidades[1] - 1;
            } else {
                $("#hole").append("Saldo Insuficiente<br>");
            }
        } else {
            $("#hole").append("No hay productos<br>");
        }
    }

    if (comando === "C1") {
        if (cantidades[2] > 0) {
            operacion = saldo - 70000;
            console.log("Operacion: " + operacion);
            if (operacion >= 0) {
                $("#hole").append("Vueltas: " + operacion);
                $("#borde2").append("<img src='chocoramo.png' alt='Chocoramo' width='200px' height='200px'><br>");
                console.log("Entro a la condicion");
                cantidades[2] = cantidades[2] - 1;
            } else {
                $("#hole").append("Saldo Insuficiente");
            }
        } else {
            $("#hole").append("No hay productos");
        }
    }

    if (comando === "A2") {
        if (cantidades[3] > 0) {
            operacion = saldo - 800;
            console.log("Operacion: " + operacion);
            if (operacion >= 0) {
                $("#hole").append("Vueltas: " + operacion);
                $("#borde2").append("<img src='clubsocial.png' alt='Cul Social' width='200px' height='200px'><br>");
                console.log("Entro a la condicion");
                cantidades[3] = cantidades[3] - 1;
            } else {
                $("#hole").append("Saldo Insuficiente");
            }
        } else {
            $("#hole").append("No hay productos");
        }
    }


    if (comando === "B2") {
        if (cantidades[4] > 0) {
            operacion = saldo - 1300;
            console.log("Operacion: " + operacion);
            if (operacion >= 0) {
                $("#hole").append("Vueltas: " + operacion);
                $("#borde2").append("<img src='hit.png' alt='Jugo Hit' width='200px' height='200px'><br>");
                console.log("Entro a la condicion");
                cantidades[4] = cantidades[4] - 1;
            } else {
                $("#hole").append("Saldo Insuficiente");
            }
        } else {
            $("#hole").append("No hay productos");
        }
    }

    if (comando === "C2") {
        if (cantidades[5] > 0) {
            operacion = saldo - 600;
            console.log("Operacion: " + operacion);
            if (operacion >= 0) {
                $("#hole").append("Vueltas: " + operacion);
                $("#borde2").append("<img src='achiras.png' alt='Achiras Ramo' width='200px' height='200px'><br>");
                console.log("Entro a la condicion");
                cantidades[5] = cantidades[5] - 1;
            } else {
                $("#hole").append("Saldo Insuficiente");
            }
        } else {
            $("#hole").append("No hay productos");
        }
    }

    if (comando === "A3") {
        if (cantidades[6] > 0) {
            operacion = saldo - 800;
            console.log("Operacion: " + operacion);
            if (operacion >= 0) {
                $("#hole").append("Vueltas: " + operacion);
                $("#borde2").append("<img src='manicero.png' alt='Mani Manicero' width='200px' height='200px'><br>");
                console.log("Entro a la condicion");
                cantidades[6] = cantidades[6] - 1;
            } else {
                $("#hole").append("Saldo Insuficiente");
            }
        } else {
            $("#hole").append("No hay productos");
        }
    }

    if (comando === "B3") {
        if (cantidades[7] > 0) {
            operacion = saldo - 1500;
            console.log("Operacion: " + operacion);
            if (operacion >= 0) {
                $("#hole").append("Vueltas: " + operacion);
                $("#borde2").append("<img src='boliqueso.png' alt='Boliqueso' width='200px' height='200px'><br>");
                console.log("Entro a la condicion");
                cantidades[7] = cantidades[7] - 1;
            } else {
                $("#hole").append("Saldo Insuficiente");
            }
        } else {
            $("#hole").append("No hay productos");
        }
    }

}


function pedir() {
    document.getElementById("launch").click();
    /*document.getElementById("borde2").innerHTML = "<img src='cocacola.png' alt='CocaCola' width='100' height='100'>";*/
    suma = 0;
    document.getElementById("dinero").value = "$0";
    console.log("pedir()");
    var comprobar = 0;
    for (var i = 0; i < denominaciones.length; i++) {
        comprobar = comprobar + denominaciones[i];
    }
    if (comprobar != 0) {

    } else {
        cargarValorADisplay();
        console.log("VALOR A DISPLAY");
    }
    document.getElementById("arreglo").value = denominaciones;
    for (var i = 0; i < 10; i++) {
        denominaciones[i] = 0;
    }
    servicio();
    /*document.getElementById("submit_tabla").click();*/
}

//var valores = [2000, 3500, 70000, 800, 1300, 600, 800, 1500];
            //var cantidades = [2, 4, 7, 1, 2, 2, 3, 3];


            /*function mandarCode() {
                document.getElementById("launch").click();
                /*Buscar Base De Datos Precio
                 
                 if (document.getElementById("text").value === "A1") {
                 pedir();
                 }*/
                //pedir();
                //servicio();
//                }