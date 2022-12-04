package logicBusiness;

import data.AbonoDaoJDBC;
import data.ClienteDaoJDBC;
import data.EmpenoDaoJDBC;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import models.Abono;
import models.Empeno;

/**
 *
 * @author matan
 */
public class UpdateTables {
//Metodo para actuaizar los estados de si es activo o vencido de los empenos

    public int updateEstados(String estado) {
        Date fechaActual = new Date();
        LocalDate fechaVen;
        List<Empeno> empenos = new EmpenoDaoJDBC().listAllEmpeno();
        int row = 0;
        for (Empeno empeno : empenos) {
            //Esta accion solo se llevara acabo si el prestamo esta en estado de activo
            if (empeno.getEstado().equalsIgnoreCase("activo")) {
                //COn este codigo pasamos de localDate a Date para comparar las fechas
                fechaVen = empeno.getFechaVencimiento();
                ZonedDateTime zonedDateTime = fechaVen.atStartOfDay(ZoneId.systemDefault());
                Instant instant = zonedDateTime.toInstant();
                Date localToDate = Date.from(instant);

                if (fechaActual.compareTo(localToDate) > 0) {

                    empeno.setEstado(estado);
                    row = new EmpenoDaoJDBC().updateEstado(empeno);

                }
            }

        }
        return row;
    }

    //Metodo para saber cuantos pagos pendientes tiene ese empeno
    public int pagosPendientes(List<Abono> abonos) {
        int contador = 0;

        for (Abono abono : abonos) {

            if (abono.getEstado().equalsIgnoreCase("pendiente")) {
                contador++;
            }

        }
        return contador;
    }

    //Metodo para calcular el total del saldo que debe cada empeno
    public double calculaTotalSaldo(Empeno empeno) {

        List<Abono> abonos = new AbonoDaoJDBC().listAbono(empeno);
        double saldoEmpeno = empeno.getValorEmpeno();
        for (Abono abono : abonos) {

            saldoEmpeno += abono.getCargo();

        }

        return saldoEmpeno;

    }

    //Metodo para actualizar el capital y los abonos
    public int pagoCapital(Empeno empeno, double capital) {

        List<Abono> abonos = new AbonoDaoJDBC().listAbono(empeno);
        int row = 0;
        for (Abono abono : abonos) {

            abono.setAbono(abono.getCargo());
            abono.setCargo(0);
            abono.setEstado("saldado");

            row = new AbonoDaoJDBC().updateAbono(abono);
        }

        double operacionResta = empeno.getValorEmpeno() - capital;

        empeno.setValorEmpeno(operacionResta);

        int updated = new EmpenoDaoJDBC().updateEmpeno(empeno);

        //Date convertida a SQL date para poder insertar en base de datos
        Date date = new Date();
        java.sql.Date dateParsed = new java.sql.Date(date.getTime());
        int newAbonoCapital = new AbonoDaoJDBC().insertAbono(new Abono(dateParsed, capital, empeno.getValorEmpeno(), 0, "saldado", "abono capital", empeno.getIdEmpeno()));

        return row;
    }

    public int isSaldado(Empeno empeno) {
        int row = 0;
        Empeno emp = new EmpenoDaoJDBC().findEmpenoById(empeno.getIdEmpeno());

        if (emp.getValorEmpeno() == 0) {

            emp.setEstado("Liquidado");
        }

        row = new EmpenoDaoJDBC().updateEstado(emp);

        return row;

    }

}
