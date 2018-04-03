package controlador;

import dao.admin.Admin_Casilla;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import vo.Casilla;
import vo.Producto;

/**
 *
 * @author USUARIO
 */
@WebServlet(urlPatterns = {"/ServletCasillas"})
public class ServletCasillas extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");

        Producto producto = new Producto();

        Admin_Casilla casillaDAO = new Admin_Casilla();

        //-------------Creacion Casillas--------------------------
        String Numero_Casillas = request.getParameter("Numero_Casillas");
        String indicador = request.getParameter("indicador");

        //-------------Ingresar Prod a Casillas-------------------
        String Nombre = request.getParameter("Producto_Ingresar");
        String Ubicacion = request.getParameter("Casilla_Colocar");
        String Cantidad = request.getParameter("Cantidad_Producto");

        if (indicador != null) {

            if (indicador.equals("1")) {
                Casilla casilla = new Casilla();
                casilla.setEspacio(Integer.parseInt(Numero_Casillas));

                if (casillaDAO.crearCasilla(casilla)) {
                    JSONObject json = new JSONObject();
                    json.put("confirmacion", Numero_Casillas + " Casillas creadas");
                    System.out.println(Numero_Casillas + " Casillas creadas");
                    out.print(json);

                } else {
                    JSONObject json = new JSONObject();
                    json.put("confirmacion", "Error en la creacion de casillas");
                    System.out.println("Error en la creacion de casillas");
                    out.print(json);
                }

            } else if (indicador.equals("0")) {
                Casilla casilla = new Casilla();
                casilla.setID(Ubicacion);
                producto.setNombre(Nombre);
                casilla.setProducto(producto);
                casilla.setCantidadProducto(Integer.parseInt(Cantidad));
                
                System.out.println(Nombre);

                if (casillaDAO.modificarCasilla(casilla)) {

                    String Full = Numero_Casillas + " / " + Nombre + " / " + Ubicacion + " / " + Cantidad;
                    JSONObject json = new JSONObject();
                    json.put("confirmacion", Full);
                    System.out.println(Full);
                    out.print(json);

                } else {
                    JSONObject json = new JSONObject();
                    json.put("confirmacion", "Error en ingresar productos");
                    System.out.println("Error en ingresar productos");
                    out.print(json);

                }

            }

        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
