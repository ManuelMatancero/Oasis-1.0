package web;

import data.ClienteDaoJDBC;
import data.EmpenoDaoJDBC;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import logicBusiness.Intereses;
import models.Cliente;
import models.Empeno;

/**
 *
 * @author matan
 */
@WebServlet("/ServletControladorCliente")
public class ServletCliente extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "editar":
                    this.editarCliente(request, response);
                    break;
                default:
                    this.accionDefault(request, response);
            }
        } else {
            this.accionDefault(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "insertar":
                    this.insertarCliente(request, response);
                    break;
                case "modificar":
                    this.modificarCliente(request, response);
                    break;
                default:
                    this.accionDefault(request, response);
            }
        } else {
            this.accionDefault(request, response);
        }

    }

    public void accionDefault(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Cliente> clientes = new ArrayList<>();
        clientes = new ClienteDaoJDBC().listCustomers();
        HttpSession sesion = request.getSession();
        String insertado = request.getParameter("insertado");
        

        //Generar recibos automaticamente de cada empeno
        //Actualizar el interes generado de la tabla empeno
        //Aqui tambien se liquidaran los prestamos
        for (Cliente cliente : clientes) {
            //Aqui se agregan todos los abonos automaticos
            int addAbono = 0;
            do {
                addAbono = new Intereses().autoFacturas(cliente);
            } while (addAbono != 0);
          
        }

        int totalClientes = clientes.size();
        List<Empeno> empenos = new EmpenoDaoJDBC().listAllEmpeno();
        //Conocer el capital prestadoo y los intereses generados
        
        double capitalCobrado = new Intereses().capitalCobrado();
        double interesCobrado = new Intereses().interesCobrado();
        double capital = new Intereses().capitalPrestado(empenos);
        double interes = new Intereses().interesTotal();

        sesion.setAttribute("insertado", insertado);
        sesion.setAttribute("clientes", clientes);
        sesion.setAttribute("capital", capital);
        sesion.setAttribute("interesCobrado", interesCobrado);
        sesion.setAttribute("capitalCobrado", capitalCobrado);
        sesion.setAttribute("totalClientes", totalClientes);
        sesion.setAttribute("interes", interes);

        //request.getRequestDispatcher("cliente.jsp").forward(request, response); 
        response.sendRedirect("cliente.jsp");

    }

    private void insertarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String cedula = request.getParameter("cedula");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");

        Cliente cliente = new Cliente(nombre, apellido, cedula, direccion, telefono);

        
        int afectados = new ClienteDaoJDBC().insertCustomer(cliente);  
        
     
        String afectado = String.valueOf(afectados);
        HttpSession sesion = request.getSession();
        sesion.setAttribute("insertado", afectado);
        //Redirigimos a la pagina principal
        this.accionDefaultInsertar(request, response);

    }

    private void editarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idCliente = Integer.parseInt(request.getParameter("idCliente"));

        Cliente cliente = new ClienteDaoJDBC().findCustomerById(new Cliente(idCliente));

        HttpSession sesion = request.getSession();
        sesion.setAttribute("cliente", cliente);

        String jspEditar = "/WEB-INF/pages/customer/editarCliente.jsp";

        request.getRequestDispatcher(jspEditar).forward(request, response);
    }

    private void modificarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String cedula = request.getParameter("cedula");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");

        int clienteActualizado = new ClienteDaoJDBC().updateCustomer(new Cliente(idCliente, nombre, apellido, cedula, direccion, telefono));
        HttpSession sesion = request.getSession();
        sesion.setAttribute("insertado", clienteActualizado);
        System.out.println("clienteActualizado = " + clienteActualizado);

        String url = "/WEB-INF/pages/loadings/modificarCliente.jsp";
        request.getRequestDispatcher(url).forward(request, response);

    }
    
    public void accionDefaultInsertar(HttpServletRequest request, HttpServletResponse response)
            throws IOException{
        List<Cliente> clientes = new ArrayList<>();
        clientes = new ClienteDaoJDBC().listCustomers();
        HttpSession sesion = request.getSession();
     
        

        //Generar recibos automaticamente de cada empeno
        //Actualizar el interes generado de la tabla empeno
        for (Cliente cliente : clientes) {
            //Aqui se agregan todos los abonos automaticos
            int addAbono = 0;
            do {
                addAbono = new Intereses().autoFacturas(cliente);
            } while (addAbono != 0);
            //Aqui se sumaran el cargo de los recibos generados
            int updateInteres = 0;
            updateInteres = new Intereses().sumaCargoInteres(cliente);
        }

        int totalClientes = clientes.size();
        List<Empeno> empenos = new EmpenoDaoJDBC().listAllEmpeno();
        //Conocer el capital prestadoo y los intereses generados
        double capital = new Intereses().capitalPrestado(empenos);
        double interes = new Intereses().interesTotal();

        
        sesion.setAttribute("clientes", clientes);
        sesion.setAttribute("capital", capital);
        sesion.setAttribute("totalClientes", totalClientes);
        sesion.setAttribute("interes", interes);

        //request.getRequestDispatcher("cliente.jsp").forward(request, response); 
        response.sendRedirect("cliente.jsp");
    }

}
