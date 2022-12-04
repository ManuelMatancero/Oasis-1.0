package models;

/**
 *
 * @author manuel antonio sarante
 */
public class Prenda {

    private int idPrenda;
    private String nombrePrenda;
    private String peso;
    private String tipoPrenda;
    private double valorPrenda;
    private String descripcion;
    private int idEmpeno;

    public Prenda() {
    }

    public Prenda(int idPrenda) {
        this.idPrenda = idPrenda;
    }

    public Prenda(String nombrePrenda, String peso, String tipoPrenda, double valorPrenda, String descripcion, int idEmpeno) {
        this.nombrePrenda = nombrePrenda;
        this.peso = peso;
        this.tipoPrenda = tipoPrenda;
        this.valorPrenda = valorPrenda;
        this.descripcion = descripcion;
        this.idEmpeno = idEmpeno;
    }

    public Prenda(int idPrenda, String nombrePrenda, String peso, String tipoPrenda, double valorPrenda, String descripcion, int idEmpeno) {
        this.idPrenda = idPrenda;
        this.nombrePrenda = nombrePrenda;
        this.peso = peso;
        this.tipoPrenda = tipoPrenda;
        this.valorPrenda = valorPrenda;
        this.descripcion = descripcion;
        this.idEmpeno = idEmpeno;
    }

    public Prenda(int idPrenda, String nombrePrenda, String peso, String tipoPrenda, double valorPrenda, String descripcion) {
        this.idPrenda = idPrenda;
        this.nombrePrenda = nombrePrenda;
        this.peso = peso;
        this.tipoPrenda = tipoPrenda;
        this.valorPrenda = valorPrenda;
        this.descripcion = descripcion;
    }

   

    public int getIdPrenda() {
        return idPrenda;
    }

    public void setIdPrenda(int idPrenda) {
        this.idPrenda = idPrenda;
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

    public String getTipoPrenda() {
        return tipoPrenda;
    }

    public void setTipoPrenda(String tipoPrenda) {
        this.tipoPrenda = tipoPrenda;
    }

    public double getValorPrenda() {
        return valorPrenda;
    }

    public void setValorPrenda(double valorPrenda) {
        this.valorPrenda = valorPrenda;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdEmpeno() {
        return idEmpeno;
    }

    public void setIdEmpeno(int idEmpeno) {
        this.idEmpeno = idEmpeno;
    }

    @Override
    public String toString() {
        return "Prenda{" + "idPrenda=" + idPrenda + ", nombrePrenda=" + nombrePrenda + ", peso=" + peso + ", tipoPrenda=" + tipoPrenda + ", valorPrenda=" + valorPrenda + ", descripcion=" + descripcion + ", idEmpeno=" + idEmpeno + '}';
    }

}
