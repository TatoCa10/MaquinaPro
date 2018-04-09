package controlador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import dao.admin.Admin_Casilla;
import dao.admin.Admin_Transaccion;
import dao.servicio.Servicio_Caja;
import dao.servicio.Servicio_Casilla;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import vo.Caja;
import vo.Casilla;
import vo.Producto;
import vo.Transaccion;

/**
 *
 * @author Carlos Alberto
 */
public class ServletTest extends HttpServlet {

    JSONArray array;
    JSONObject json;

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
        response.setContentType("json/application");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

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
        PrintWriter out = response.getWriter();
        //processRequest(request, response);
        response.setContentType("application/json");

        array = new JSONArray();

        Transaccion trans = new Transaccion();
        Admin_Transaccion transaccion = new Admin_Transaccion();

        Admin_Casilla adminCasilla = new Admin_Casilla();
        ArrayList<Casilla> listaCasillas = new ArrayList<>();
        listaCasillas = adminCasilla.leerCasilla();

        Servicio_Casilla casillaDAO = new Servicio_Casilla();
        Servicio_Caja cajaDAO = new Servicio_Caja();

        Casilla casillaVO = new Casilla();
        Caja cajaVO = new Caja();
        int precio;

        String ubicacion = request.getParameter("Ubicacion");
        String denominaciones = request.getParameter("Forma_Pago");
        String Sum = request.getParameter("Plata_Pago");
        int Suma = Integer.parseInt(Sum);

        casillaVO.setID(ubicacion);
        String producto = "";

        for (int i = 0; i < listaCasillas.size(); i++) {
            System.out.println("Productos servletTest: " + listaCasillas.get(i).getProducto().getNombre());
            if (ubicacion.equals(listaCasillas.get(i).getID())) {

                if (listaCasillas.get(i).getProducto().getNombre().equals("Vacio")) {
                    json = new JSONObject();
                    json.put("confirmacion", "3");

                    out.print(json);
                } else {
                    producto = listaCasillas.get(i).getProducto().getNombre();
                }
            }

        }

        String partes[] = denominaciones.split(",");
        int Arreglo[] = new int[partes.length];

        System.out.println("Partes:" + partes.length);
        for (int i = 1; i < partes.length - 1; i++) {
            Arreglo[i] = Integer.parseInt(partes[i]);
        }

//        for (int i = 0; i < Arreglo.length; i++) {
//            switch (i) {
//                case 0:
//                    Suma = Suma + Arreglo[i] * 50;
//                    break;
//                case 1:
//                    Suma = Suma + Arreglo[i] * 100;
//                    break;
//                case 2:
//                    Suma = Suma + Arreglo[i] * 200;
//                    break;
//                case 3:
//                    Suma = Suma + Arreglo[i] * 500;
//                    break;
//                case 4:
//                    Suma = Suma + Arreglo[i] * 1000;
//                    break;
//                case 5:
//                    Suma = Suma + Arreglo[i] * 2000;
//                    break;
//                case 6:
//                    Suma = Suma + Arreglo[i] * 5000;
//                    break;
//                case 7:
//                    Suma = Suma + Arreglo[i] * 10000;
//                    break;
//                case 8:
//                    Suma = Suma + Arreglo[i] * 20000;
//                    break;
//                case 9:
//                    Suma = Suma + Arreglo[i] * 50000;
//                    break;
//            }
//        }
        System.out.println("Ubicacion: " + ubicacion);
        System.out.println("Arreglo: " + denominaciones);
        System.out.println("Total Ingresado: " + Suma);

        precio = casillaDAO.consultarPrecio(casillaVO);

        if (precio != 0 || precio != 1) {

            if ((Suma - precio) == 0) {
                Producto prood = new Producto();
                casillaDAO.entregarProducto(casillaVO);
                prood.setNombre(producto);

                trans.setProducto(prood);
                trans.setEntradaDinero(Suma);
                trans.setSalidaDinero(0);

                transaccion.crearTransaccion(trans);

                cajaVO.setSaldo(Arreglo);
                cajaDAO.entradaDinero(cajaVO);

                json = new JSONObject();
                json.put("confirmacion", "4");
                json.put("producto", producto);

                out.print(json);

            } else if ((Suma - precio) < 0) {
                json = new JSONObject();
                json.put("confirmacion", "1");
                json.put("m50", Arreglo[0]);
                json.put("m100", Arreglo[1]);
                json.put("m200", Arreglo[2]);
                json.put("m500", Arreglo[3]);
                json.put("m1000", Arreglo[4]);
                json.put("b2000", Arreglo[5]);
                json.put("b5000", Arreglo[6]);
                json.put("b10000", Arreglo[7]);
                json.put("b20000", Arreglo[8]);
                json.put("b50000", Arreglo[9]);

                out.print(json);

            } else if ((Suma - precio) > 0) {
                int numero = Suma - precio;
                int[] res = cajaDAO.salidaDinero(numero);

                if (res.length > 2) {

                    cajaVO.setSaldo(Arreglo);
                    int ent = cajaDAO.entradaDinero(cajaVO);

                    if (ent == 0) {
                        if (casillaDAO.entregarProducto(casillaVO)) {

                            Producto prood = new Producto();
                            prood.setNombre(producto);

                            trans.setProducto(prood);
                            trans.setEntradaDinero(Suma);
                            trans.setSalidaDinero(Suma - precio);

                            transaccion.crearTransaccion(trans);

                            json = new JSONObject();
                            json.put("confirmacion", "2");
                            json.put("m50", res[0]);
                            json.put("m100", res[1]);
                            json.put("m200", res[2]);
                            json.put("m500", res[3]);
                            json.put("m1000", res[4]);
                            json.put("b2000", res[5]);
                            json.put("b5000", res[6]);
                            json.put("b10000", res[7]);
                            json.put("b20000", res[8]);
                            json.put("b50000", res[9]);
                            json.put("producto", producto);

                            out.print(json);

                        }

                    } else {
                        json = new JSONObject();
                        json.put("confirmacion", "3");

                        out.print(json);
                    }
                } else {
                    json = new JSONObject();
                    json.put("confirmacion", "3");

                    out.print(json);
                }
            }
        } else {
            json = new JSONObject();
            json.put("confirmacion", "3");

            out.print(json);
        }

//        JSONObject json = new JSONObject();
//        json.put("confirmacion", denominaciones);
//
//        out.print(json);
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
