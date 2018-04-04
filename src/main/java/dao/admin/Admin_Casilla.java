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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Conexion;
import vo.Casilla;
import vo.Producto;

public class Admin_Casilla {

    private Connection conexion;

    public Admin_Casilla() {
        try {
            this.conexion = Conexion.getConnection();
        } catch (URISyntaxException ex) {
            Logger.getLogger(Admin_Casilla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean crearCasilla(Casilla casilla) {
        boolean resultado = false;

        ArrayList<Casilla> casi = leerCasilla();
        try {
            for (int i = 0; i < casilla.getEspacio(); i++) {
                //1.Establecer la consulta
                String consulta = "INSERT INTO Casilla VALUES(?,?,?)";
                //2. Crear el PreparedStament
                PreparedStatement statement
                        = this.conexion.prepareStatement(consulta);
                //-----------------------------------

                if (casi != null) {
                    statement.setString(1, Integer.toString(i + casi.size()));
                    statement.setString(2, "Vacio");
                    statement.setInt(3, 0);
                } else {

                    statement.setString(1, Integer.toString(i));
                    statement.setString(2, "Vacio");
                    statement.setInt(3, 0);
                }

                //--------------------------------------
                //3. Hacer la ejecucion
                resultado = statement.execute();

            }
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return resultado;
    }

    public boolean modificarCasilla(Casilla casilla) {
        boolean result = false;
        Admin_Producto productoDAO = new Admin_Producto();
        ArrayList<Producto> prod = new ArrayList<>();
        prod = productoDAO.leerProducto();

        if (leerCasilla() == null) {
            return result;
        }

        for (int i = 0; i < prod.size(); i++) {
            if (prod.get(i).getNombre().equals(casilla.getProducto().getNombre())) {

                String query = "update Casilla set ID = ?, Producto = ?, CantidadProducto= ? where ID = ?";
                PreparedStatement preparedStmt = null;
                try {
                    preparedStmt = this.conexion.prepareStatement(query);
                    preparedStmt.setString(1, casilla.getID());
                    // preparedStmt.setInt(2, casilla.getEspacio());
                    preparedStmt.setString(2, casilla.getProducto().getNombre());
                    preparedStmt.setInt(3, casilla.getCantidadProducto());
                    preparedStmt.setString(4, casilla.getID());

                    if (preparedStmt.executeUpdate() > 0) {
                        result = true;
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (i == prod.size()) {
                return false;
            }
        }

        return result;
    }

    public int numeroCasillas() {

        String consulta = "SELECT COUNT(id) FROM Casilla";

        try {
            //Statement
            Statement statement
                    = this.conexion.createStatement();
            //Ejecucion
            ResultSet resultado
                    = statement.executeQuery(consulta);
            //----------------------------
            //Recorrido sobre el resultado
            if (resultado != null) {
                int i = resultado.getInt(1);
                System.out.println();
                return i;

            } else {
                return 0;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    public ArrayList<Casilla> leerCasilla() {
        Admin_Producto producoDAO = new Admin_Producto();

        //1.Consulta
        ArrayList<Casilla> respuesta = new ArrayList<>();
        ArrayList<Producto> productos = new ArrayList<>();
        productos = producoDAO.leerProducto();
        int cantCasillas = numeroCasillas();

        for (int i = 0; i < productos.size(); i++) {
            System.out.println("Rescatando productos:");
            System.out.println(i + " " + productos.get(i).getNombre());
        }
        String consulta = "SELECT * FROM Casilla order by id";

        try {
            //Statement
            Statement statement
                    = this.conexion.createStatement();
            //Ejecucion
            ResultSet resultado
                    = statement.executeQuery(consulta);
            //----------------------------
            //Recorrido sobre el resultado
            while (resultado.next()) {

                Casilla casillaVO = new Casilla();

                casillaVO.setID(resultado.getString(1));


                    if (resultado.getString(2).equals("Vacio")) {
                        Producto prod = new Producto();
                        prod.setNombre("Vacio");
                        prod.setPrecio(0);
                        casillaVO.setProducto(prod);
                    } else {

                        for (int j = 0; j < productos.size(); j++) {

                            if (productos.get(j).nombre.equals(resultado.getString(2))) {
                                casillaVO.setProducto(productos.get(j));
                                break;
                            }
                        }
                }

                casillaVO.setCantidadProducto(resultado.getInt(3));
                respuesta.add(casillaVO);
            }

            for (int i = 0; i < respuesta.size(); i++) {
                System.out.println("Prod desde Metedo: " + respuesta.get(i).getProducto().getNombre());
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return respuesta;
    }

    public boolean borrarCasilla(Casilla casilla) {
        boolean result = false;
        String query = "delete from Casilla where ID = ?";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = this.conexion.prepareStatement(query);
            preparedStmt.setString(1, casilla.getID());
            result = preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

}
