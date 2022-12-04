package models;

import java.time.LocalDate;
import java.util.Date;


/**
 *
 * @author manuel antonio sarante
 */
public class Empeno {

    private int idEmpeno;
    private Date fechaEmpeno;
    private LocalDate fechaVencimiento;
    private Date ultimoPago;
    private double adelanto;
    private String tipoPago;
    private double tasa;
    private String estado;
    private double valorEmpeno;
    private double interesEmpeno;
    private int idCliente;
    private String nombrePrenda;
    private String peso;
    private String categoria;
    private String descripcion;
    

    public Empeno() {
    }

    public Empeno(int idEmpeno) {
        this.idEmpeno = idEmpeno;
    }

    public Empeno(LocalDate fechaVencimiento, double adelanto, String tipoPago, double tasa, String estado, double valorEmpeno, double interesEmpeno, int idCliente, String nombrePrenda, String peso, String categoria, String descripcion) {
        this.fechaVencimiento = fechaVencimiento;
        this.adelanto =adelanto;
        this.tipoPago = tipoPago;
        this.tasa = tasa;
        this.estado = estado;
        this.valorEmpeno = valorEmpeno;
        this.interesEmpeno = interesEmpeno;
        this.idCliente = idCliente;
        this.nombrePrenda = nombrePrenda;
        this.peso = peso;
        this.categoria = categoria;
        this.descripcion = descripcion;
    }

    public Empeno(int idEmpeno, Date fechaEmpeno, LocalDate fechaVencimiento, Date ultimoPago, double adelanto, String tipoPago, double tasa, String estado, double valorEmpeno, double interesEmpeno, int idCliente, String nombrePrenda, String peso, String categoria, String descripcion) {
        this.idEmpeno = idEmpeno;
        this.fechaEmpeno = fechaEmpeno;
        this.fechaVencimiento = fechaVencimiento;
        this.ultimoPago = ultimoPago;
        this.adelanto = adelanto;
        this.tipoPago = tipoPago;
        this.tasa= tasa;
        this.estado= estado;
        this.valorEmpeno = valorEmpeno;
        this.interesEmpeno = interesEmpeno;
        this.idCliente = idCliente;
        this.nombrePrenda = nombrePrenda;
        this.peso = peso;
        this.categoria = categoria;
        this.descripcion = descripcion;
    }

    public Empeno(int idEmpeno, Date fechaEmpeno, LocalDate fechaVencimiento, Date ultimoPago, double adelanto, String tipoPago, double tasa, String estado, double valorEmpeno, double interesEmpeno, String nombrePrenda, String peso, String categoria, String descripcion) {
        this.idEmpeno = idEmpeno;
        this.fechaEmpeno = fechaEmpeno;
        this.fechaVencimiento = fechaVencimiento;
        this.ultimoPago = ultimoPago;
        this.adelanto = adelanto;
        this.tipoPago= tipoPago;
        this.tasa = tasa;
        this.estado = estado;
        this.valorEmpeno = valorEmpeno;
        this.interesEmpeno = interesEmpeno;
        this.nombrePrenda = nombrePrenda;
        this.peso = peso;
        this.categoria = categoria;
        this.descripcion = descripcion;
    }

    public Empeno(int idEmpeno, Date now) {
        this.idEmpeno = idEmpeno;
        this.ultimoPago = now;
    }

  

    public int getIdEmpeno() {
        return idEmpeno;
    }

    public void setIdEmpeno(int idEmpeno) {
        this.idEmpeno = idEmpeno;
    }

    public Date getFechaEmpeno() {
        return fechaEmpeno;
    }

    public void setFechaEmpeno(Date fechaEmpeno) {
        this.fechaEmpeno = fechaEmpeno;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Date getUltimoPago() {
        return ultimoPago;
    }

    public void setUltimoPago(Date ultimoPago) {
        this.ultimoPago = ultimoPago;
    }

    public double getAdelanto() {
        return adelanto;
    }

    public void setAdelanto(double adelanto) {
        this.adelanto = adelanto;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }
    
    

    public double getTasa() {
        return tasa;
    }

    public void setTasa(double tasa) {
        this.tasa = tasa;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    

    public double getValorEmpeno() {
        return valorEmpeno;
    }

    public void setValorEmpeno(double valorEmpeno) {
        this.valorEmpeno = valorEmpeno;
    }

    public double getInteresEmpeno() {
        return interesEmpeno;
    }

    public void setInteresEmpeno(double interesEmpeno) {
        this.interesEmpeno = interesEmpeno;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombrePrenda() {
        return nombrePrenda;
    }

    public void setNombrePrenda(String nombrePrenda) {
        this.nombrePrenda = nombrePrenda;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    

    @Override
    public String toString() {
        return "Empeno{" + "idEmpeno=" + idEmpeno + ", fechaEmpeno=" + fechaEmpeno.toString() + ", fechaVencimiento=" + fechaVencimiento + ", valorEmpeno=" + valorEmpeno + ", interesEmpeno=" + interesEmpeno + ", idCliente=" + idCliente + '}';
    }

}
