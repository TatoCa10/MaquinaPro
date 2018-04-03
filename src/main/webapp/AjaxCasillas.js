$(document).ready(function () {

    $('#Configurar').click(function () {


        var Numero_Casillas = $('#Casillas_Maquina').val();
        var indicador = "1";
        $.ajax({
            url: 'ServletCasillas',
            type: 'GET',
            data: {Numero_Casillas: Numero_Casillas, indicador: indicador},
            dataType: 'json',
            success: function (data) {

                console.log("Casillas Creadas");
                $("#return").append("<p>" + data.confirmacion + "</p><br>");
            },
            error: function () {
                console.log("ERROR");
            }
        });
    });




    $('#Agregar').click(function () {


        var Producto_Ingresar = $('#Producto_Ingresar').val();
        var Casilla_Colocar = $('#Casilla_Colocar').val();
        var Cantidad_Producto = $('#Cantidad_Producto').val();
        var indicador = "0";
        $.ajax({
            url: 'ServletCasillas',
            type: 'GET',
            data: {Producto_Ingresar: Producto_Ingresar, Casilla_Colocar: Casilla_Colocar, Cantidad_Producto: Cantidad_Producto, indicador:indicador},
            dataType: 'json',
            success: function (data) {

                console.log("DATOS SUCCESS");
                /*$("#return").append("<p>" + data.confirmacion + "</p><br>");*/
                document.getElementById("return").innerHTML = data.confirmacion;
                alert("Entro");
            },
            error: function () {
                console.log("ERROR");
            }
        });
    });

});
