package test;

import data.ClienteDaoJDBC;
import data.EmpenoDaoJDBC;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import logicBusiness.Fechas;
import logicBusiness.Intereses;
import logicBusiness.UpdateTables;
import models.Cliente;
import models.Empeno;

/**
 *
 * @author matan
 */
public class Tests {

    public static void main(String[] args) {
        //int idCliente = 100;
        //double valorEmpeno = 1000;
        //int meses = 4;
        // double interes = 10;

        //Obtebiendo la fecha de vencimiento
        /// String fechaV = new Fechas().fechaVencimiento(meses);
        //Convirtiendo mes a double
        // String mes = String.valueOf(meses); 
        //double mesConv = Double.parseDouble(mes);
        //Obteniendo los intereses
        // double intereses = new Intereses().interesGenerado(interes, valorEmpeno, mesConv);
        //java.lang.ClassCastException: java.util.Date cannot be cast to java.sql.Date
        /////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////
        ////
        /*Cliente cliente = new Cliente("jjjjjajajjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjrerererererre", "ererererereereeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee", "222223323232323","ewwwwewewewewe", "weweweweweweww" );
        int afectados = new ClienteDaoJDBC().insertCustomer(cliente);
        String exeption = String.valueOf(afectados);
        System.out.println(exeption);*/
   
        
        
        

        // int insertado = new EmpenoDaoJDBC().insertEmpeno(new Empeno(date1, valorEmpeno, intereses, idCliente));
        ////////////////////////////////////////////////////////////////////////////////////
    }

    static void findDifference(String start_date,
                    String end_date) {

        // SimpleDateFormat converts the
        // string format to date object
        SimpleDateFormat sdf
                = new SimpleDateFormat(
                        "dd-MM-yyyy");

        // Try Block
        try {

            // parse method is used to parse
            // the text from a string to
            // produce the date
            Date d1 = sdf.parse(start_date);
            Date d2 = sdf.parse(end_date);

            // Calucalte time difference
            // in milliseconds
            long difference_In_Time = d2.getTime() - d1.getTime();

            // Calucalte time difference in
            // seconds, minutes, hours, years,
            // and days
          

            long difference_In_Days = (difference_In_Time / (1000 * 60 * 60 * 24))% 365;

            // Print the date difference in
            // years, in days, in hours, in
            // minutes, and in seconds
            System.out.print(
                    "Difference "
                    + "between two dates is: ");

            System.out.println(
                   difference_In_Days
                    + " days, "
                  );
        } // Catch the Exception
        catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
