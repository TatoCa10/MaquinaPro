package dao;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Conexion;
import vo.UsuarioVO;
import vo.VendedorVO;

public class VendedorDAO {
     private Connection conexion;

    public VendedorDAO(){
         try {
             this.conexion = Conexion.getConnection();
         } catch (URISyntaxException ex) {
             Logger.getLogger(VendedorDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    public boolean insertar(VendedorVO vendedor) {
        boolean resultado = false;
        try {
            //1.Establecer la consulta
            String consulta = "INSERT INTO Vendedores VALUES(?,?,?,?)";
            //2. Crear el PreparedStament
            PreparedStatement statement
                    = this.conexion.prepareStatement(consulta);
            //-----------------------------------
            statement.setString(1, vendedor.getCelular());
            statement.setString(2, vendedor.getNombre());
            statement.setString(3, vendedor.getCorreo());
            statement.setString(4, vendedor.getPassword());
            
            //--------------------------------------
            //3. Hacer la ejecucion
            resultado = statement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return resultado;
    }
    
    
    public boolean editar(VendedorVO vendedor) {
        boolean result = false;
        String query = "update Vendedores set Celular = ?, Nombre = ?, Correo = ?, contrasena= ? where Celular = ?";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = this.conexion.prepareStatement(query);
            preparedStmt.setString(1, vendedor.getCelular());
            preparedStmt.setString(2, vendedor.getNombre());
            preparedStmt.setString(3, vendedor.getCorreo());
            preparedStmt.setString(4, vendedor.getPassword());
            preparedStmt.setString(5, vendedor.getCelular());
            
            if (preparedStmt.executeUpdate() > 0) {
                result = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
    
    public boolean borrar(VendedorVO vendedor) {
        boolean result = false;
        String query = "delete from Usuarios where Celular = ?";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = this.conexion.prepareStatement(query);
            preparedStmt.setString(1, vendedor.getCelular());
            result = preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
    
    public boolean loggear(VendedorVO vendedor) {

        boolean res = false;
        String query = "select * from Vendedores where Correo = ? and Contrasena = ?";

        try {
            PreparedStatement preparedStmt = this.conexion.prepareStatement(query);

            preparedStmt.setString(1, vendedor.getCorreo());
            preparedStmt.setString(2, vendedor.getPassword());

            ResultSet rs = preparedStmt.executeQuery();

            if (rs.next()) {
                res = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return res;
    }
    
    public boolean buscar(VendedorVO vendedor){
        try {
            String consulta = "SELECT celular FROM Vendedores WHERE Correo = ?";
            PreparedStatement statement
                    = this.conexion.prepareStatement(consulta);

            statement.setString(1, vendedor.getCorreo());
            ResultSet resultado = statement.executeQuery();
            return resultado.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    public String buscarCelVendedor(String correo) {

        try {

            String query = "SELECT celular from Vendedores where correo=?";

             PreparedStatement stmt = null;

            stmt = this.conexion.prepareStatement(query);
            
            stmt.setString(1, correo);
            ResultSet res = stmt.executeQuery();
            

            if(res.next()){
                return res.getString("celular");
            }else{
                return null;
            }
            


        } catch (SQLException e) {
            System.out.println("Failed to make insertion!");
            e.printStackTrace();
        }
        return null;
    }
    
    
    public void notiVendedor(boolean noti, String correo){
    
            String query = "UPDATE vendedores SET noti=" + noti + " where correo='" + correo + "'";
        PreparedStatement preparedStmt = null;
        try {         
                    preparedStmt = this.conexion.prepareStatement(query);
                    preparedStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
            
    
    }
    
    public boolean checkBoolean(String correo){
         
       try {
            String query = "SELECT noti from Vendedores where correo=?";

             PreparedStatement stmt = null;

            stmt = this.conexion.prepareStatement(query);
            
            stmt.setString(1, correo);
            ResultSet res = stmt.executeQuery();
            
            if(res.next()){
                return res.getBoolean("noti");
            }else{
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
         
       return false;

    }
    
}
