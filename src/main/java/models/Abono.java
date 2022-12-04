package models;

import java.sql.Date;

/**
 *
 * @author manuel antonio sarante
 */
public class Abono {

    private int idAbono;
    private Date fechaAbono;
    private double abono;
    private double valorCapital;
    private double cargo;
    private String estado;
    private String operacion;
    private int idEmpeno;

    public Abono() {
    }

    public Abono(int idAbono) {
        this.idAbono = idAbono;
    }

    public Abono(double abono, double valorCapital, double cargo, String estado,String operacion, int idEmpeno) {
        this.abono = abono;
        this.valorCapital = valorCapital;
        this.cargo = cargo;
        this.estado = estado;
        this.operacion = operacion;
        this.idEmpeno = idEmpeno;
    }

    public Abono(int idAbono, Date fechaAbono, double abono, double valorCapital, double cargo, String estado,String operacion, int idEmpeno) {
        this.idAbono = idAbono;
        this.fechaAbono = fechaAbono;
        this.abono = abono;
        this.valorCapital = valorCapital;
        this.cargo = cargo;
        this.estado = estado;
        this.operacion = operacion;
        this.idEmpeno = idEmpeno;
    }

    public Abono(int idAbono, Date fechaAbono, double abono, double valorCapital, double cargo, String estado, String operacion) {
        this.idAbono = idAbono;
        this.fechaAbono = fechaAbono;
        this.abono = abono;
        this.valorCapital = valorCapital;
        this.cargo= cargo;
        this.estado = estado;
        this.operacion=operacion;
    }

    public Abono(Date fechaAbono, double abono, double valorCapital, double cargo, String estado,String operacion, int idEmpeno) {
        this.fechaAbono = fechaAbono;
        this.abono = abono;
        this.valorCapital = valorCapital;
        this.cargo = cargo;
        this.estado = estado;
        this.operacion = operacion;
        this.idEmpeno = idEmpeno;
        
    }

 
    

    public int getIdAbono() {
        return idAbono;
    }

    public void setIdAbono(int idAbono) {
        this.idAbono = idAbono;
    }

    public Date getFechaAbono() {
        return fechaAbono;
    }

    public void setFechaAbono(Date fechaAbono) {
        this.fechaAbono = fechaAbono;
    }

    public double getAbono() {
        return abono;
    }

    public void setAbono(double abono) {
        this.abono = abono;
    }

    public double getValorCapital() {
        return valorCapital;
    }

    public void setValorCapital(double valorCapital) {
        this.valorCapital = valorCapital;
    }

    public double getCargo() {
        return cargo;
    }

    public void setCargo(double cargo) {
        this.cargo = cargo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }
    
    

    public int getIdEmpeno() {
        return idEmpeno;
    }

    public void setIdEmpeno(int idEmpeno) {
        this.idEmpeno = idEmpeno;
    }
    
    public double getSaldo(){
        return this.cargo + this.valorCapital;
    }

    @Override
    public String toString() {
        return "Abono{" + "idAbono=" + idAbono + ", fechaAbono=" + fechaAbono + ", abono=" + abono + ", valorCapital=" + valorCapital + ", idEmpeno=" + idEmpeno + '}';
    }

}
