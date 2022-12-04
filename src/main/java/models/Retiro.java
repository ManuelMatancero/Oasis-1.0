package models;

import java.util.Date;

/**
 *
 * @author manuel antonio saranet
 */
public class Retiro {

    private int idRetiro;
    private Date fechaRetiro;
    private int pagoCapital; //Este dato 0 es falso y 1 es verdadero
    private int idEmpeno;

    public Retiro() {
    }

    public Retiro(int idRetiro) {
        this.idRetiro = idRetiro;
    }

    public Retiro(Date fechaRetiro, int pagoCapital, int idEmpeno) {
        this.fechaRetiro = fechaRetiro;
        this.pagoCapital = pagoCapital;
        this.idEmpeno = idEmpeno;
    }

    public Retiro(int idRetiro, Date fechaRetiro, int pagoCapital, int idEmpeno) {
        this.idRetiro = idRetiro;
        this.fechaRetiro = fechaRetiro;
        this.pagoCapital = pagoCapital;
        this.idEmpeno = idEmpeno;
    }

    public int getIdRetiro() {
        return idRetiro;
    }

    public void setIdRetiro(int idRetiro) {
        this.idRetiro = idRetiro;
    }

    public Date getFechaRetiro() {
        return fechaRetiro;
    }

    public void setFechaRetiro(Date fechaRetiro) {
        this.fechaRetiro = fechaRetiro;
    }

    public int getPagoCapital() {
        return pagoCapital;
    }

    public void setPagoCapital(int pagoCapital) {
        this.pagoCapital = pagoCapital;
    }

    public int getIdEmpeno() {
        return idEmpeno;
    }

    public void setIdEmpeno(int idEmpeno) {
        this.idEmpeno = idEmpeno;
    }

    @Override
    public String toString() {
        return "Retiro{" + "idRetiro=" + idRetiro + ", fechaRetiro=" + fechaRetiro + ", pagoCapital=" + pagoCapital + ", idEmpeno=" + idEmpeno + '}';
    }

}
