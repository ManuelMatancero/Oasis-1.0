package web;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.*;
import java.util.Date;
import data.AbonoDaoJDBC;
import data.ClienteDaoJDBC;
import data.EmpenoDaoJDBC;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import logicBusiness.CambiosSalida;
import logicBusiness.Fechas;
import logicBusiness.Intereses;
import logicBusiness.UpdateTables;
import models.Cliente;
import models.Empeno;

@WebServlet("/ServletControladorEmpeno")
public class ServletEmpeno extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "eliminarCliente": {

                    this.eliminarCliente(request, response);
                }
                break;
                case "imprimirBoleto": {
                    this.imprimirBoleto(request, response);
                }
                break;
                case "datosBoleto":{
                    this.datosBoleto(request, response);
                }
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
                case "insertarEmpeno": {

                    this.insertarEmpenoPrenda(request, response);
                }
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

        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        Cliente cliente = new Cliente(idCliente);
        ClienteDaoJDBC clienteDao = new ClienteDaoJDBC();
        //Variables de estados de empenos
        int activos = 0;
        int vencidos = 0;
        int liquidados = 0;
        //Emcontramos el cliente seleccionado con todos sus valores
        cliente = clienteDao.findCustomerById(cliente);
        //compartimos el objeto en el alcace session
        HttpSession sesion = request.getSession();
        sesion.setAttribute("cliente", cliente);
        //Obtenemos todos los empenos que este tiene asociados
        List<Empeno> empenos = new ArrayList<>();
        //Actualizando estados de empenos
        int updateEstadoEmpeno = new UpdateTables().updateEstados("Vencido");

        empenos = new EmpenoDaoJDBC().listEmpeno(cliente);
        for (Empeno empeno : empenos) {
            if (empeno.getEstado().equalsIgnoreCase("activo")) {
                activos++;
            }
            if (empeno.getEstado().equalsIgnoreCase("vencido")) {
                vencidos++;
            }
            if (empeno.getEstado().equalsIgnoreCase("liquidado")) {
                liquidados++;
            }
        }

        // aquise filtran los empenos para modificar la nomenclatura del tipo de pago
        List<Empeno> empenoFiltrado = new CambiosSalida().listaFiltradaTipoPago(empenos);
        
        List<Empeno> empenosNotArchived = new CambiosSalida().empenosNotArchivados(empenoFiltrado);
                
        sesion.setAttribute("activos", activos);
        sesion.setAttribute("vencidos", vencidos);
        sesion.setAttribute("liquidados", liquidados);
        sesion.setAttribute("cliente", cliente);
        sesion.setAttribute("empenos", empenosNotArchived);
        sesion.setAttribute("idCliente", idCliente);

        request.getRequestDispatcher("empeno.jsp").forward(request, response);
    }

    private void insertarEmpenoPrenda(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        String nombrePrenda = request.getParameter("nombrePrenda");
        String peso = request.getParameter("peso");
        double valorEmpeno = Double.parseDouble(request.getParameter("valorEmpeno"));
        int meses = Integer.parseInt(request.getParameter("meses"));
        double interes = Double.parseDouble(request.getParameter("interes"));
        String categoria = request.getParameter("categoria");
        String descripcion = request.getParameter("descripcion");
        double adelanto = 0;
        String tipoPago = request.getParameter("tipoPago");

        String estado = "Activo";

        //INSERTANDO EMPENO A LA TABLA///////////
        //Obtebiendo la fecha de vencimiento
        String fechaV = new Fechas().fechaVencimiento(meses);
        //Convirtiendo mes a double
        //Obteniendo los intereses
        double intereses = 0;
        LocalDate date1 = LocalDate.parse(fechaV);
        int insertado = new EmpenoDaoJDBC().insertEmpeno(new Empeno(date1, adelanto, tipoPago, interes, estado, valorEmpeno, intereses, idCliente, nombrePrenda, peso, categoria, descripcion));
        System.out.println("insertado = " + insertado);

        this.accionDefault(request, response);

    }

    private void eliminarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idCliente = Integer.parseInt(request.getParameter("idCliente"));

        List<Empeno> empenos = new EmpenoDaoJDBC().listEmpeno(new Cliente(idCliente));
        int row = 0;
        for (Empeno empeno : empenos) {

            row = new AbonoDaoJDBC().deleteAbono(empeno);
            row = new EmpenoDaoJDBC().deleteEmpeno(empeno);

        }
        HttpSession sesion = request.getSession();

        int eliminado = 3;
        row = new ClienteDaoJDBC().deleteCustomer(new Cliente(idCliente));
        request.setAttribute("insertado", eliminado);
        String url = "/WEB-INF/pages/loadings/eliminarCliente.jsp";

        request.getRequestDispatcher(url).forward(request, response);

    }

    public void imprimirBoleto(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        String idEmpeno = request.getParameter("idEmpeno");
        Cliente cliente = new ClienteDaoJDBC().findCustomerById(new Cliente(idCliente));
        Empeno empeno = new EmpenoDaoJDBC().findEmpenoById(Integer.parseInt(idEmpeno));

        //Datos de empeno
        String cuotas = empeno.getTipoPago();
        if (cuotas.equalsIgnoreCase("S")) {
            cuotas = "SEMANAL";
        } else if (cuotas.equalsIgnoreCase("Q")) {
            cuotas = "QUINCENAL";
        } else if (cuotas.equalsIgnoreCase("Mensual")) {
            cuotas = "MENSUAL";
        }
        //Formato de numeros de precios
        NumberFormat formatter2 = NumberFormat.getCurrencyInstance(Locale.US);
        int interesBruto = (int) empeno.getTasa();
        String interes = String.valueOf(interesBruto) + "%";
        int prestamoBruto = (int) empeno.getValorEmpeno();
        String prestamo = formatter2.format(prestamoBruto);
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaEmpenoBruta = empeno.getFechaEmpeno();
        String fechaEmpeno = DateFor.format(fechaEmpenoBruta);
        LocalDate fechaVencimientoBruta = empeno.getFechaVencimiento();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        String fechaVencimiento = formatter.format(fechaVencimientoBruta);

        //Datos del articulo
        String nombrePrenda = empeno.getNombrePrenda() + ", " + empeno.getDescripcion();
        String peso = empeno.getPeso();
        int valorBruto = (int) empeno.getValorEmpeno();      
        String valor = formatter2.format(valorBruto);

        response.setContentType("application/pdf");

        response.setHeader(
                "Content-disposition",
                "inline; filename=Downloaded.pdf");

        try {

            Document document = new Document();

            PdfWriter.getInstance(document, response.getOutputStream());

            document.open();
            //Codigo
            Paragraph codigo = new Paragraph();
            Font letraCodigo = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.BLACK);
            codigo.add(new Phrase(idEmpeno, letraCodigo));
            codigo.setAlignment(Element.ALIGN_RIGHT);
            document.add(codigo);

            //Cabecero
            Paragraph cabecero = new Paragraph();
            Font letraCabecero = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD, BaseColor.BLACK);
            cabecero.add(new Phrase("Compraventa Ventura\n", letraCabecero));
            cabecero.setAlignment(Element.ALIGN_CENTER);
            document.add(cabecero);

            //Direccion
            Paragraph direccion = new Paragraph();
            Font letraDireccion = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.NORMAL, BaseColor.BLACK);
            
            direccion.add(new Phrase("Calle general florimon #70\n Matancitas, Nagua\n Tel. 849-351-3816\n \n \n", letraDireccion));
            direccion.setAlignment(Element.ALIGN_CENTER);
            document.add(direccion);

            //Terminos
            Paragraph terminos = new Paragraph();
            Font letraTerminos = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);
            Font letraNombre = new Font(Font.FontFamily.HELVETICA, 12, Font.UNDERLINE, BaseColor.BLACK);
            String nombreClienteA = cliente.getNombre() + " " + cliente.getApellido();
            Phrase nombreC = new Phrase(nombreClienteA, letraNombre);
            terminos.add(new Phrase("CONTRATO DE MUTUO INTERES Y GARANTIA PRENDARIA (EMPEÑO), que celebran por una parte Yngreni Sanchez Gil con domicilio en: Calle General Florimon #70, Matancitas, Nagua y "
                    + nombreC
                    + " con cedula de identidad: " + cliente.getCedula()
                    + " Teléfono: " + cliente.getTelefono() + ".\n \n Detalles del empeño:\n \n", letraTerminos));
            document.add(terminos);

            //Tabla de detalles de empeno
            PdfPTable tabla = new PdfPTable(5);
            PdfPCell celda1 = new PdfPCell(new Paragraph(new Phrase("Cuotas", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.BLACK))));
            PdfPCell celda2 = new PdfPCell(new Paragraph(new Phrase("Interes", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.BLACK))));
            PdfPCell celda3 = new PdfPCell(new Paragraph(new Phrase("Prestamo", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.BLACK))));
            PdfPCell celda4 = new PdfPCell(new Paragraph(new Phrase("Fecha Empeño", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.BLACK))));
            PdfPCell celda5 = new PdfPCell(new Paragraph(new Phrase("Fecha Vencimiento", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.BLACK))));

            PdfPCell celdaA1 = new PdfPCell(new Paragraph(new Phrase(cuotas, FontFactory.getFont("Helvetica", 14, Font.ITALIC, BaseColor.BLACK))));
            PdfPCell celdaA2 = new PdfPCell(new Paragraph(new Phrase(interes, FontFactory.getFont("Helvetica", 14, Font.ITALIC, BaseColor.BLACK))));
            PdfPCell celdaA3 = new PdfPCell(new Paragraph(new Phrase(prestamo, FontFactory.getFont("Helvetica", 14, Font.ITALIC, BaseColor.BLACK))));
            PdfPCell celdaA4 = new PdfPCell(new Paragraph(new Phrase(fechaEmpeno, FontFactory.getFont("Helvetica", 14, Font.ITALIC, BaseColor.BLACK))));
            PdfPCell celdaA5 = new PdfPCell(new Paragraph(new Phrase(fechaVencimiento, FontFactory.getFont("Helvetica", 14, Font.ITALIC, BaseColor.BLACK))));

            celdaA1.setFixedHeight(36f);
            celdaA2.setFixedHeight(36f);
            celdaA3.setFixedHeight(36f);
            celdaA4.setFixedHeight(36f);
            celdaA5.setMinimumHeight(36f);

            tabla.addCell(celda1);
            tabla.addCell(celda2);
            tabla.addCell(celda3);
            tabla.addCell(celda4);
            tabla.addCell(celda5);
            tabla.addCell(celdaA1);
            tabla.addCell(celdaA2);
            tabla.addCell(celdaA3);
            tabla.addCell(celdaA4);
            tabla.addCell(celdaA5);
            
            tabla.setWidthPercentage(100f);

            document.add(tabla);

            //detalles de bien
            Paragraph details = new Paragraph();
            details.add(new Phrase("\n GARANTIA: Para garantizar el pago de este prestamo, el CONSUMIDOR deja en garantia el bien que se describe a continuacion. \n \n", letraTerminos));
            document.add(details);
            //Tabla de detalles del bien empenado
            PdfPTable tabla2 = new PdfPTable(3);
            PdfPCell celdaB1 = new PdfPCell(new Paragraph(new Phrase("BIEN, descripcion del articulo", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.BLACK))));
            PdfPCell celdaB2 = new PdfPCell(new Paragraph(new Phrase("PESO", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.BLACK))));
            PdfPCell celdaB3 = new PdfPCell(new Paragraph(new Phrase("VALOR DE EMPEÑO", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.BLACK))));
            celdaB1.setPaddingLeft(20f);
            celdaB1.setPaddingRight(20f);

            PdfPCell celdaC1 = new PdfPCell(new Paragraph(new Phrase(nombrePrenda, FontFactory.getFont("Helvetica", 14, Font.ITALIC, BaseColor.BLACK))));
            PdfPCell celdaC2 = new PdfPCell(new Paragraph(new Phrase(peso, FontFactory.getFont("Helvetica", 14, Font.ITALIC, BaseColor.BLACK))));
            PdfPCell celdaC3 = new PdfPCell(new Paragraph(new Phrase(valor, FontFactory.getFont("Helvetica", 14, Font.ITALIC, BaseColor.BLACK))));
            celdaC1.setPaddingLeft(20f);
            celdaC1.setPaddingRight(20f);

            tabla2.addCell(celdaB1);
            tabla2.addCell(celdaB2);
            tabla2.addCell(celdaB3);
            tabla2.addCell(celdaC1);
            tabla2.addCell(celdaC2);
            tabla2.addCell(celdaC3);
            
            tabla2.setWidthPercentage(100f);

            document.add(tabla2);
            
            //Nota aclaratoria
            Paragraph nota = new Paragraph();
            nota.add(new Phrase("\n \n \n \n \n \n NOTA: Plazo establecido hasta la fecha de vencimiento adjunta en este documento, cada fraccion de plazo mensual se considera un plazo completo para fines de tanto porciento a cobrar dividido en el tipo de cuotas pactado, si usted no paga en tiempo y forma, sus articulos podran ser comercializados.", letraTerminos ));
            document.add(nota);
            
            //Nueva pagina
            document.newPage();
            
            //Detalles del articulo para fines de almacenamiento
            //Codigo
            document.add(codigo);
            //Detalles
            PdfPTable tabla3 = new PdfPTable(3);
            PdfPCell celdaD1 = new PdfPCell(new Paragraph(new Phrase("Nombre: \n \n" + nombreClienteA , FontFactory.getFont("Helvetica", 14, Font.ITALIC, BaseColor.BLACK))));
            PdfPCell celdaD2 = new PdfPCell(new Paragraph(new Phrase("Fecha: \n \n" + fechaEmpeno, FontFactory.getFont("Helvetica", 14, Font.ITALIC, BaseColor.BLACK))));
            PdfPCell celdaD3 = new PdfPCell(new Paragraph(new Phrase("Valor: \n \n" + valor, FontFactory.getFont("Helvetica", 14, Font.ITALIC, BaseColor.BLACK))));
            celdaD1.setPaddingLeft(20f);
            celdaD1.setPaddingBottom(20f);
            celdaD2.setPaddingLeft(20f);
            celdaD3.setPaddingLeft(20f);
            
           
            
            tabla3.addCell(celdaD1);
            tabla3.addCell(celdaD2);
            tabla3.addCell(celdaD3);
            tabla3.setWidthPercentage(100f);
            
            document.add(tabla3);
            
            Paragraph linea = new Paragraph();
            linea.add(new Phrase("----------------------------------------------------------------------------------------------------------------------------------"));
            linea.setAlignment(Element.ALIGN_CENTER);
            document.add(linea);

            document.close();
        } catch (DocumentException de) {
            throw new IOException(de.getMessage());
        }
    }

    private void datosBoleto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String idCliente = request.getParameter("idCliente");
        String idEmpeno = request.getParameter("idEmpeno");
        Cliente cliente = new ClienteDaoJDBC().findCustomerById(new Cliente(Integer.parseInt(idCliente)));
        Empeno empeno = new EmpenoDaoJDBC().findEmpenoById(Integer.parseInt(idEmpeno));
        
        String nombreCliente = cliente.getNombre() + " " + cliente.getApellido();
        
        
        HttpSession sesion = request.getSession();
        
        sesion.setAttribute("idCliente", idCliente);
        sesion.setAttribute("idEmpeno", idEmpeno);
        sesion.setAttribute("nombreCliente", nombreCliente);
        
        String url = "/WEB-INF/pages/printContract/imprimirContrato.jsp";
        request.getRequestDispatcher(url).forward(request, response);
        
        
    }
    
    

}
