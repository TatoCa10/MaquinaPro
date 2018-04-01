package controlador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import dao.admin.Admin_Producto;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import vo.Producto;

/**
 *
 * @author USUARIO
 */
public class Servlet_Productos extends HttpServlet {

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
//        processRequest(request, response);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");

        Producto producto = new Producto();
        Admin_Producto productoDAO = new Admin_Producto();

        String Nombre_Producto = request.getParameter("Nombre_Producto");
        String Precio_Producto = request.getParameter("Precio_Producto");

        producto.setNombre(Nombre_Producto);
        producto.setPrecio(Integer.parseInt(Precio_Producto));

        if (productoDAO.crearProducto(producto)) {
            JSONObject json = new JSONObject();
            json.put("confirmacion", "Producto <b><i>" + Nombre_Producto + " </i></b>Aprendido con un valor unitario de $" + Precio_Producto);
            System.out.println("Aprendido");
            out.print(json);
        } else {

            JSONObject json = new JSONObject();
            json.put("confirmacion", "Producto <b>Error al crear producto</b>");
            System.out.println("No se aprendio proucto");
            out.print(json);

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
