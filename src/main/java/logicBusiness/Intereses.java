package logicBusiness;

import data.AbonoDaoJDBC;
import data.EmpenoDaoJDBC;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import models.Abono;
import models.Cliente;
import models.Empeno;

/**
 *
 * @author matan
 */
public class Intereses {
/////////////Este metodo me esta dando un problema bacanisimo///////////////////////////////////////////////////////
    ////////////////////////
    ////////////////////////////////////////
    ///////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
//////    /////////////////////////////////////////////////////////////////////////////////////
    //Metodo para calcular los intereses generados de un cliente

    public int interesGenerado() {
        int row = 0;
        double interes;
        double interesDiario;
        double calculo;
        double resultado;
        double cero = 0.0;
        double sumaAbonos = 0;
        double resultadoFinal = 0;
        final double S = 7;
        final double Q = 15;
        final double M = 30;
        final double A = 365;
        String tipoDePago;
        int cuotas = 0;
        //obtengo todos los empenos
        List<Empeno> empenos = new ArrayList<>();
        List<Abono> abonos = new ArrayList<>();
        empenos = new EmpenoDaoJDBC().listAllEmpeno();
        Date ultimoPago;
        Date fechaAhora = new Date();
        for (Empeno empeno : empenos) {

            abonos = new AbonoDaoJDBC().listAbono(empeno);
            sumaAbonos = 0;
            for (Abono abono : abonos) {

                sumaAbonos += abono.getAbono();
            }

            interes = empeno.getTasa() / 100;
            tipoDePago = empeno.getTipoPago();
            switch (tipoDePago) {
                case "S":
                    cuotas = (int) S;
                    break;
                case "Q":
                    cuotas = (int) Q;
                    break;
                case "M":
                    cuotas = (int) M;
                    break;
                case "A":
                    cuotas = (int) A;
                    break;
            }
            calculo = (interes * empeno.getValorEmpeno()) / 30;
            resultado = calculo * cuotas;

            ultimoPago = empeno.getFechaEmpeno();
            long time_difference = fechaAhora.getTime() - ultimoPago.getTime();
            // Calucalte time difference in days  
            long days_difference = (time_difference / (1000 * 60 * 60 * 24)) % 365;

            if (empeno.getInteresEmpeno() == 0) {
                empeno.setInteresEmpeno(resultado);
                row = new EmpenoDaoJDBC().updateInteres(empeno);
            } else {
                switch (cuotas) {
                    //Si el tipo de cuotas es semanal para este empeno entonces:::
                    case 7:
                        if (days_difference >= cuotas) {
                            double semanas = days_difference / S;
                            double modulo = days_difference % S;
                            if (modulo == cero) {
                                int semanasRounded = (int) semanas;
                                resultadoFinal = (resultado * semanasRounded) - sumaAbonos;
                                empeno.setInteresEmpeno(resultadoFinal);
                                row = new EmpenoDaoJDBC().updateInteres(empeno);
                            } else {
                                int semanasRounded = (int) semanas + 1;
                                resultadoFinal = (resultado * semanasRounded) - sumaAbonos;
                                empeno.setInteresEmpeno(resultadoFinal);
                                row = new EmpenoDaoJDBC().updateInteres(empeno);
                            }
                        }
                        break;
                    case 15:
                        if (days_difference >= cuotas) {
                            double quincenas = days_difference / Q;
                            double modulo = days_difference % Q;
                            if (modulo == cero) {
                                int quincenasRounded = (int) quincenas;
                                resultadoFinal = (resultado * quincenasRounded) - sumaAbonos;
                                empeno.setInteresEmpeno(resultadoFinal);
                                row = new EmpenoDaoJDBC().updateInteres(empeno);
                            } else {
                                int quincenasRounded = (int) quincenas + 1;
                                resultadoFinal = (resultado * quincenasRounded) - sumaAbonos;
                                empeno.setInteresEmpeno(resultadoFinal);
                                row = new EmpenoDaoJDBC().updateInteres(empeno);
                            }
                        }
                        break;
                    case 30:
                        if (days_difference >= cuotas) {
                            double mensualidad = days_difference / M;
                            double modulo = days_difference % M;
                            if (modulo == cero) {
                                int mensualidadRounded = (int) mensualidad;
                                resultadoFinal = (resultado * mensualidadRounded) - sumaAbonos;
                                empeno.setInteresEmpeno(resultadoFinal);
                                row = new EmpenoDaoJDBC().updateInteres(empeno);

                            } else {
                                int mensualidadRounded = (int) mensualidad + 1;
                                resultadoFinal = (resultado * mensualidadRounded) - sumaAbonos;
                                empeno.setInteresEmpeno(resultadoFinal);
                                row = new EmpenoDaoJDBC().updateInteres(empeno);
                            }

                        }
                        break;
                }

            }

        }

        return row;
    }
//Este metodo funciona como se espera solo debo ajustarle para que se complete todo el ciclo /////////////////////////////////////////////////
    //Metodo para generar los abono de manera automatica dependiendo del tipo de pago

    public int autoFacturas(Cliente cliente) {
        int addAbono = 0;
        int updateEmpeno = 0;
        //Variables para saber el tipo de cuotas de cada cliente
        final double S = 7;
        final double Q = 15;
        final double M = 30;
        //Variable para hacer los calculos
        int cuotas = 0;
        double interes = 0;
        double interesDiario = 0;
        double interesFinal = 0;
        String tipoDePago;
        //obtener todos los empenos
        addAbono = 0;

        LocalDate fechaActual = new Fechas().convertToLocalDateViaInstant(new Date());
        LocalDate ultimoPago;
        LocalDate proximoPago;

        List<Empeno> empenos = new EmpenoDaoJDBC().listEmpeno(cliente);
        for (Empeno empeno : empenos) {
            //Si el empeno esta activo o vencido la factura se generara automaticamente de lo contrario no
            if (empeno.getEstado().equalsIgnoreCase("activo") || empeno.getEstado().equalsIgnoreCase("vencido")) {
                //Verifico que tipo de cuota tiene el cliente
                tipoDePago = empeno.getTipoPago();
                switch (tipoDePago) {
                    case "S":
                        cuotas = (int) S;
                        break;
                    case "Q":
                        cuotas = (int) Q;
                        break;
                    case "M":
                        cuotas = (int) M;
                        break;
                }
                // calculo el interes que va a generar dependiendo el tipo de cuotas
                interes = empeno.getTasa() / 100;
                interesDiario = (interes * empeno.getValorEmpeno()) / 30;
                interesFinal = interesDiario * cuotas;
                ///////////////////////////////////////////////////////////////////

                //Le sumo los dias de cuota al ultimo pago para generar la fecha siguiente de pago
                ultimoPago = new Fechas().convertToLocalDateViaInstant(empeno.getUltimoPago());
                proximoPago = ultimoPago.plusDays(cuotas);

                //Se van a seguir agregando porque dentro de los abonos siempre va aquedar uno igual que se seguira iterando
                Date fechaPagoAbono = new Fechas().convertToDateViaInstant(proximoPago);
                empeno.setUltimoPago(fechaPagoAbono);
                //Date convertida a SQL date para poder insertar en base de datos
                java.sql.Date dateParsed = new java.sql.Date(fechaPagoAbono.getTime());
                //inserto el abono

                if (fechaActual.isAfter(ultimoPago)) {

                    addAbono = new AbonoDaoJDBC().insertAbono(new Abono(dateParsed, 0, empeno.getValorEmpeno(), interesFinal, "pendiente", "interes generado", empeno.getIdEmpeno()));
                    updateEmpeno = new EmpenoDaoJDBC().updateUltimoPago(empeno);

                }

            }
        }

        return addAbono;
    }

    //metodo que suma los intereses generados para actualizar los intereses de la taba empeno
    public int sumaCargoInteres(Cliente cliente) {
        int row = 0;
        List<Empeno> empenos = new EmpenoDaoJDBC().listEmpeno(cliente);

        for (Empeno empeno : empenos) {
            double totalInteres = 0;
            List<Abono> abonos = new AbonoDaoJDBC().listAbono(empeno);

            for (Abono abono : abonos) {

                totalInteres += abono.getCargo();

            }

            empeno.setInteresEmpeno(totalInteres);
            row = new EmpenoDaoJDBC().updateInteres(empeno);

        }

        return row;

    }

    //metodo pata calcular el capital prestado a todos los clientes
    public double capitalPrestado(List<Empeno> empenos) {

        double capital = 0;

        for (Empeno empeno : empenos) {
            capital += empeno.getValorEmpeno();
        }

        return capital;
    }

    //metodo para calcular el interes total de todos los clientes
    public double interesTotal() {

        double interes = 0;
        List<Abono> abonos = new AbonoDaoJDBC().listAllAbono();
        for (Abono abono : abonos) {

            interes += abono.getCargo() + abono.getAbono();
        }

        return interes;
    }

    //Metodo para saber el interes cobrado 
    public double interesCobrado() {

        List<Abono> abonos = new AbonoDaoJDBC().listAllAbono();
        double interes = 0;
        for (Abono abono : abonos) {

            if (abono.getEstado().equalsIgnoreCase("saldado") && abono.getOperacion().equalsIgnoreCase("interes generado")) {
                interes += abono.getAbono();
            }

        }

        return interes;
    }

    public double capitalCobrado() {
        List<Abono> abonos = new AbonoDaoJDBC().listAllAbono();
        double interes = 0;
        for (Abono abono : abonos) {

            if (abono.getOperacion().equalsIgnoreCase("abono capital")) {
                interes += abono.getAbono();
            }

        }

        return interes;
    }

}
