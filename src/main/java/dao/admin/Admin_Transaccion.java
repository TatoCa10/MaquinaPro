/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.admin;

/**
 *
 * @author Carlos Alberto
 */
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.persistence.internal.eis.adapters.aq.AQRecord;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import util.Conexion;
import vo.Producto;
import vo.Transaccion;

public class Admin_Transaccion {

    private Connection conexion;

    public Admin_Transaccion() {

        try {
            this.conexion = Conexion.getConnection();
        } catch (URISyntaxException ex) {
            Logger.getLogger(Admin_Transaccion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean crearTransaccion(Transaccion transaccion) {
        boolean resultado = false;
        ArrayList<Transaccion> trans = new ArrayList<>();
        trans = leerTransaccion();

        if (trans != null) {

            try {
                //1.Establecer la consulta
                String consulta = "INSERT INTO Transaccion VALUES(?,?,?,?)";
                //2. Crear el PreparedStament
                PreparedStatement statement
                        = this.conexion.prepareStatement(consulta);
                //-----------------------------------
                statement.setString(1, Integer.toString(trans.size()));
                statement.setString(2, transaccion.getProducto().getNombre());
                statement.setInt(3, transaccion.getEntradaDinero());
                statement.setInt(4, transaccion.getSalidaDinero());
                statement.setDate(5, (Date) transaccion.getFecha());

                //--------------------------------------
                //3. Hacer la ejecucion
                resultado = statement.execute();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                //1.Establecer la consulta
                String consulta = "INSERT INTO Transaccion VALUES(?,?,?,?)";
                //2. Crear el PreparedStament
                PreparedStatement statement
                        = this.conexion.prepareStatement(consulta);
                //-----------------------------------
                statement.setString(1, Integer.toString(0));
                statement.setString(2, transaccion.getProducto().getNombre());
                statement.setInt(3, transaccion.getEntradaDinero());
                statement.setInt(4, transaccion.getSalidaDinero());
                statement.setDate(5, (Date) transaccion.getFecha());

                //--------------------------------------
                //3. Hacer la ejecucion
                resultado = statement.execute();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }

        return resultado;
    }

    public ArrayList<Transaccion> leerTransaccion() {
        //1.Consulta
        ArrayList<Transaccion> respuesta = new ArrayList<Transaccion>();
        String consulta = "SELECT * FROM Transaccion";
        try {
            //----------------------------
            //Statement
            Statement statement
                    = this.conexion.createStatement();
            //Ejecucion
            ResultSet resultado
                    = statement.executeQuery(consulta);
            //----------------------------
            //Recorrido sobre el resultado
            while (resultado.next()) {
                Transaccion transaccion = new Transaccion();
                Producto producto = new Producto();
                transaccion.setId(resultado.getString(1));
                producto.setNombre(resultado.getString(2));
                transaccion.setProducto(producto);
                transaccion.setEntradaDinero(resultado.getInt(3));
                transaccion.setSalidaDinero(resultado.getInt(4));
                transaccion.setFecha(resultado.getDate(5));
                respuesta.add(transaccion);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return respuesta;
    }

}
