package web;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import data.AbonoDaoJDBC;
import data.ClienteDaoJDBC;
import data.EmpenoDaoJDBC;
import java.io.IOException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import logicBusiness.CambiosSalida;
import logicBusiness.Fechas;
import logicBusiness.Intereses;
import logicBusiness.UpdateTables;
import models.Abono;
import models.Cliente;
import models.Empeno;

/**
 *
 * @author matan
 */
@WebServlet("/ServletControladorAbono")
public class ServletAbono extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "pagarAbono":
                    this.pagarAbono(request, response);
                    break;
                case "imprimirRecibo":
                    this.imprimirRecibo(request, response);
                    break;
                case "obtenerDatos":
                    this.obtenerDatos(request, response);
                    break;
                case "archivar": {
                    this.archivarEmpeno(request, response);
                }
                break;
                case "cancelar": {
                    this.cancelarEmpeno(request, response);
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
                case "pagarAbono":
                    this.pagarAbono(request, response);
                    break;
                case "abonarCapital":
                    this.abonoCapital(request, response);
                    break;
                default:
                    this.accionDefault(request, response);
            }
        } else {
            this.accionDefault(request, response);
        }

    }

    private void accionDefault(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String alerta = request.getParameter("alerta");
        int idEmpeno = Integer.parseInt(request.getParameter("idEmpeno"));
        //Calculo el saldo total de ese empeno manipulano los abonos
        Empeno empeno = new EmpenoDaoJDBC().findEmpenoById(idEmpeno);
        Cliente cliente = new ClienteDaoJDBC().findCustomerById(new Cliente(empeno.getIdCliente()));
        double saldoTotal = new UpdateTables().calculaTotalSaldo(empeno);

        //este metodo actualiza el interes en la informacion del empeno
        int updateInteres = 0;
        updateInteres = new Intereses().sumaCargoInteres(cliente);
        empeno = new EmpenoDaoJDBC().findEmpenoById(idEmpeno);
        ////////
        List<Abono> abonos = new AbonoDaoJDBC().listAbono(new Empeno(idEmpeno));
        //Metodo que cuenta cuantos pagos pendientes tiene ese empeno
        int pagosPendientes = new UpdateTables().pagosPendientes(abonos);
        Empeno empenoFilt = new CambiosSalida().filtroTipoPago(empeno);

        HttpSession sesion = request.getSession();
        sesion.setAttribute("idEmpeno", idEmpeno);
        sesion.setAttribute("cliente", cliente);
        sesion.setAttribute("empeno", empenoFilt);
        sesion.setAttribute("alerta", alerta);
        sesion.setAttribute("abonos", abonos);
        sesion.setAttribute("saldoTotal", saldoTotal);
        sesion.setAttribute("pagosPendientes", pagosPendientes);
        request.getRequestDispatcher("abono.jsp").forward(request, response);
    }

    private void pagarAbono(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String alerta = request.getParameter("alerta");
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        int idAbono = Integer.parseInt(request.getParameter("idAbono"));
        double interes = Double.parseDouble(request.getParameter("interesGenerado"));
        Abono abono = new AbonoDaoJDBC().findAbonoById(idAbono);
        abono.setAbono(interes);
        abono.setCargo(0);
        abono.setEstado("saldado");
        int updateAbonoPagoInteres = new AbonoDaoJDBC().updateAbonoPagoInteres(abono);

        HttpSession sesion = request.getSession();
        sesion.setAttribute("alerta", alerta);
        sesion.setAttribute("idCliente", idCliente);
        sesion.setAttribute("pago", updateAbonoPagoInteres);

        this.accionDefault(request, response);

    }

    public void imprimirRecibo(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int idAbono = Integer.parseInt(request.getParameter("idAbono"));
        int idEmpeno = Integer.parseInt(request.getParameter("idEmpeno"));

        Abono abono = new AbonoDaoJDBC().findAbonoById(idAbono);
        Empeno empeno = new EmpenoDaoJDBC().findEmpenoById(idEmpeno);
        Cliente cliente = new ClienteDaoJDBC().findCustomerById(new Cliente(empeno.getIdCliente()));

        Locale locale = new Locale("es", "US");
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, locale);
        String fechaAbono = dateFormat.format(abono.getFechaAbono());
        String fechaEmpeno = dateFormat.format(empeno.getFechaEmpeno());
        String fechaHoy = dateFormat.format(new Date());
        NumberFormat formatter2 = NumberFormat.getCurrencyInstance(Locale.US);
        String valorAbono = formatter2.format(abono.getAbono());
        //COnvertir de localDate a Date
        Date fechaV = new Fechas().convertToDateViaInstant(empeno.getFechaVencimiento());
        String fechaVencimiento = dateFormat.format(fechaV);
        Document document = new Document();

        response.setContentType("application/pdf");

        response.setHeader("Content-disposition", "inline; filename=Downloaded.pdf");
        try {

            PdfWriter.getInstance(document, response.getOutputStream());

            document.open();

            //Codigo
            Paragraph codigo = new Paragraph();
            Font letraCodigo = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.BLACK);
            codigo.add(new Phrase("Cod: " + String.valueOf(idAbono), letraCodigo));
            codigo.setAlignment(Element.ALIGN_RIGHT);
            document.add(codigo);

            //Cabecero
            Paragraph cabecero = new Paragraph();
            Font letraCabecero = new Font(Font.FontFamily.COURIER, 20, Font.BOLD, BaseColor.BLACK);
            Font letraCabecero2 = new Font(Font.FontFamily.HELVETICA, 15, Font.ITALIC, BaseColor.BLACK);
            cabecero.add(new Phrase("Compraventa Ventura\n \n", letraCabecero));
            cabecero.add(new Phrase("Recibo de " + fechaAbono, letraCabecero2));
            document.add(cabecero);

            //Cuerpo del recibo
            Paragraph cuerpo = new Paragraph();
            Font letraCuerpo = new Font(Font.FontFamily.COURIER, 12, Font.NORMAL, BaseColor.BLACK);
            Font line = new Font(Font.FontFamily.COURIER, 8, Font.NORMAL, BaseColor.GRAY);
            cuerpo.add(new Phrase("________________________________________________________________________________________________________", line));
            cuerpo.add(new Phrase("Cliente:                 " + cliente.getNombre() + " " + cliente.getApellido() + "\n", letraCuerpo));
            cuerpo.add(new Phrase("Fecha de prestamo:       " + fechaEmpeno + "\n", letraCuerpo));
            cuerpo.add(new Phrase("Fecha de vencimiento:    " + fechaVencimiento + "\n", letraCuerpo));
            cuerpo.add(new Phrase("Codigo de empeño:        " + String.valueOf(idEmpeno) + "\n", letraCuerpo));
            cuerpo.add(new Phrase("Prenda:                  " + empeno.getNombrePrenda() + ", " + empeno.getDescripcion() + "\n \n", letraCuerpo));
            document.add(cuerpo);

            //Pie de recibo
            PdfPTable tabla3 = new PdfPTable(3);
            PdfPCell celda1 = new PdfPCell(new Paragraph(new Phrase("Fecha:", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.BLACK))));
            PdfPCell celda2 = new PdfPCell(new Paragraph(new Phrase("Concepto:", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.BLACK))));
            PdfPCell celda3 = new PdfPCell(new Paragraph(new Phrase("SubTotal:", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.BLACK))));
            celda1.setBorder(Rectangle.NO_BORDER);
            celda2.setBorder(Rectangle.NO_BORDER);
            celda3.setBorder(Rectangle.NO_BORDER);

            PdfPCell celdaA1 = new PdfPCell(new Paragraph(new Phrase(fechaHoy, FontFactory.getFont("Courier", 12, Font.ITALIC, BaseColor.BLACK))));
            PdfPCell celdaA2 = new PdfPCell(new Paragraph(new Phrase(abono.getOperacion().toUpperCase(), FontFactory.getFont("Courier", 12, Font.ITALIC, BaseColor.BLACK))));
            PdfPCell celdaA3 = new PdfPCell(new Paragraph(new Phrase(valorAbono, FontFactory.getFont("Courier", 12, Font.ITALIC, BaseColor.BLACK))));
            celdaA1.setBorder(Rectangle.NO_BORDER);
            celdaA2.setBorder(Rectangle.NO_BORDER);
            celdaA3.setBorder(Rectangle.NO_BORDER);

            tabla3.addCell(celda1);
            tabla3.addCell(celda2);
            tabla3.addCell(celda3);
            tabla3.addCell(celdaA1);
            tabla3.addCell(celdaA2);
            tabla3.addCell(celdaA3);
            tabla3.setWidthPercentage(100f);
            document.add(tabla3);

            PdfPTable tabla4 = new PdfPTable(1);
            PdfPCell celdaB1 = new PdfPCell(new Paragraph(new Phrase("Total:    " + valorAbono, FontFactory.getFont("Arial", 15, Font.BOLD, BaseColor.BLACK))));
            celdaB1.setBorder(Rectangle.NO_BORDER);
            celdaB1.setPaddingTop(15f);
            celdaB1.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
            tabla4.addCell(celdaB1);
            document.add(tabla4);

            Paragraph lineaInt = new Paragraph();
            lineaInt.add(new Phrase("\n\n\n\n\n- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - \n\n\n\n\n"));
            document.add(lineaInt);

            //Copy codigo
            Paragraph codigo1 = new Paragraph();
            codigo1.add(new Phrase("Cod: " + String.valueOf(idAbono), letraCodigo));
            codigo1.setAlignment(Element.ALIGN_RIGHT);
            document.add(codigo1);
            //Copy cabecero
            Paragraph cabecero1 = new Paragraph();
            cabecero1.add(new Phrase("Compraventa Ventura\n \n", letraCabecero));
            cabecero1.add(new Phrase("Copia", new Font(Font.FontFamily.COURIER, 20, Font.NORMAL, BaseColor.GRAY)));
            cabecero1.add(new Phrase("  Recibo de " + fechaAbono, letraCabecero2));
            document.add(cabecero1);
            //Copy cuerpo del recibo
            Paragraph cuerpo1 = new Paragraph();
            cuerpo1.add(new Phrase("________________________________________________________________________________________________________", line));
            cuerpo1.add(new Phrase("Cliente:                 " + cliente.getNombre() + " " + cliente.getApellido() + "\n", letraCuerpo));
            cuerpo1.add(new Phrase("Fecha de prestamo:       " + fechaEmpeno + "\n", letraCuerpo));
            cuerpo1.add(new Phrase("Fecha de vencimiento:    " + fechaVencimiento + "\n", letraCuerpo));
            cuerpo1.add(new Phrase("Codigo de empeño:        " + String.valueOf(idEmpeno) + "\n", letraCuerpo));
            cuerpo1.add(new Phrase("Prenda:                  " + empeno.getNombrePrenda() + ", " + empeno.getDescripcion() + "\n \n", letraCuerpo));
            document.add(cuerpo1);
            //Copy pie de recibo
            PdfPTable tabla1 = new PdfPTable(3);
            tabla1.addCell(celda1);
            tabla1.addCell(celda2);
            tabla1.addCell(celda3);
            tabla1.addCell(celdaA1);
            tabla1.addCell(celdaA2);
            tabla1.addCell(celdaA3);
            tabla1.setWidthPercentage(100f);
            document.add(tabla1);

            PdfPTable tabla5 = new PdfPTable(1);
            tabla5.addCell(celdaB1);
            document.add(tabla5);

            document.close();

        } catch (DocumentException de) {
            throw new IOException(de.getMessage());
        }
    }

    public void obtenerDatos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idAbono = request.getParameter("idAbono");
        String idEmpeno = request.getParameter("idEmpeno");
        Empeno empeno = new EmpenoDaoJDBC().findEmpenoById(Integer.parseInt(idEmpeno));
        Cliente cliente = new ClienteDaoJDBC().findCustomerById(new Cliente(empeno.getIdCliente()));

        String nombreCliente = cliente.getNombre() + " " + cliente.getApellido();
        String nombreArticulo = empeno.getNombrePrenda() + ", " + empeno.getDescripcion();
        int idCliente = empeno.getIdCliente();

        HttpSession sesion = request.getSession();

        sesion.setAttribute("idAbono", idAbono);
        sesion.setAttribute("idEmpeno", idEmpeno);
        sesion.setAttribute("nombreCliente", nombreCliente);
        sesion.setAttribute("nombreArticulo", nombreArticulo);
        sesion.setAttribute("idCliente", idCliente);

        String url = "/WEB-INF/pages/printContract/imprimirRecibo.jsp";

        request.getRequestDispatcher(url).forward(request, response);

    }

    public void abonoCapital(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        double capital = Double.parseDouble(request.getParameter("capital"));
        int idEmpeno = Integer.parseInt(request.getParameter("idEmpeno"));
        Empeno empeno = new EmpenoDaoJDBC().findEmpenoById(idEmpeno);
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        //Aqui actualizo el valor Del empeno y agrego el abono al capital y tambien si el cliente saldo el empeno
        int row = new UpdateTables().pagoCapital(empeno, capital);

        //Aqui se actualiza el estado del empeno a saldado si se paga el total del capital
        int updRow = new UpdateTables().isSaldado(empeno);

        this.accionDefault(request, response);

    }

    public void archivarEmpeno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int idEmpeno = Integer.parseInt(request.getParameter("idEmpeno"));
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));

        Empeno empeno = new EmpenoDaoJDBC().findEmpenoById(idEmpeno);

        empeno.setEstado("Archivado");

        int row = new EmpenoDaoJDBC().updateEmpeno(empeno);

        this.accionDefault(request, response);

    }

    public void cancelarEmpeno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idEmpeno = Integer.parseInt(request.getParameter("idEmpeno"));

        Empeno empeno = new EmpenoDaoJDBC().findEmpenoById(idEmpeno);

        empeno.setEstado("Cancelado");

        int row = new EmpenoDaoJDBC().updateEmpeno(empeno);

        this.accionDefault(request, response);

    }

}
