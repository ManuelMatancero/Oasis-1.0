package logicBusiness;

import java.util.ArrayList;
import java.util.List;
import models.Empeno;

public class CambiosSalida {
    
    public List<Empeno> listaFiltradaTipoPago(List<Empeno> empenos) {
        List<Empeno> filtroEmpeno = new ArrayList<>();
        
        for (Empeno empeno : empenos) {
            
            if (empeno.getTipoPago().equals("M")) {
                empeno.setTipoPago("Mensual");
            } else if (empeno.getTipoPago().equals("Q")) {
                empeno.setTipoPago("Quincenal");
            } else {
                empeno.setTipoPago("Semanal");
            }
            
            filtroEmpeno.add(empeno);
            
        }
        
        return filtroEmpeno;
        
    }
    
    public Empeno filtroTipoPago(Empeno empeno) {
        
        if (empeno.getTipoPago().equals("M")) {
            empeno.setTipoPago("Mensual");
        } else if (empeno.getTipoPago().equals("Q")) {
            empeno.setTipoPago("Quincenal");
        } else {
            empeno.setTipoPago("Semanal");
        }
        
        return empeno;
    }

    //Este metodo es para solo agregar los empenos que esten activos, liquidados o vencidos a la lista de empenos que se 
    //le mostraran al cliente
    public List<Empeno> empenosNotArchivados(List<Empeno> empenos) {
        List<Empeno> empenosFiltrados = new ArrayList<>();
        for (Empeno empeno : empenos) {
            String estado = empeno.getEstado();
            if (estado.equalsIgnoreCase("activo")
                    || estado.equalsIgnoreCase("liquidado")
                    || estado.equalsIgnoreCase("vencido")
                    || estado.equalsIgnoreCase("cancelado")) {
                
                empenosFiltrados.add(empeno);
                
            }            
        }
        
        return empenosFiltrados;
    }
    
}
