package data;

import data.DAO.EmpenoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.time.LocalDate;
import java.sql.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Cliente;
import models.Empeno;

/**
 *
 * @author matan
 */
public class EmpenoDaoJDBC implements EmpenoDAO {

    public static final String SQL_SELECT = "SELECT id_empeno, fecha_empeno, fecha_vencimiento, ultimo_pago, adelanto, tipo_pago, tasa, estado, valor_empeno, interes_empeno, id_cliente, nombre_prenda, peso, categoria, descripcion FROM empeno WHERE id_cliente=? ORDER BY fecha_empeno DESC";
    public static final String SQL_SELECT_ALL = "SELECT id_empeno, fecha_empeno, fecha_vencimiento, ultimo_pago, adelanto,tipo_pago, tasa, estado, valor_empeno, interes_empeno, id_cliente, nombre_prenda, peso, categoria, descripcion FROM empeno";
    public static final String SQL_INSERT = "INSERT INTO empeno(fecha_vencimiento, adelanto, tipo_pago, tasa, estado, valor_empeno, interes_empeno, id_cliente, nombre_prenda, peso, categoria, descripcion)"
            + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String SQL_UPDATE = "UPDATE empeno SET fecha_vencimiento=?, ultimo_pago= ?, adelanto= ?, tipo_pago=?, tasa=?, estado=?, valor_empeno=?, interes_empeno=?, nombre_prenda=?, peso=?, categoria=?, descripcion=?"
            + " WHERE id_empeno=?";
    public static final String SQL_UPDATE_INTERES = "UPDATE empeno SET interes_empeno=? WHERE id_empeno=?";
    public static final String SQL_DELETE = "DELETE FROM empeno WHERE id_empeno=?";

    public static final String SQL_SELECT_BY_ID = "SELECT id_empeno, fecha_empeno, fecha_vencimiento, ultimo_pago, adelanto, tipo_pago, tasa, estado, valor_empeno, interes_empeno, id_cliente, nombre_prenda, peso, categoria, descripcion FROM empeno WHERE id_empeno= ?";
    public static final String SQL_UPDATE_ESTADO = "UPDATE empeno SET estado=? WHERE id_empeno=?";
    public static final String SQL_UPDATE_ULTIMOPAGO = "UPDATE empeno SET ultimo_pago=? WHERE id_empeno=?";
    public static final String SQL_DELETE_CLIENTE = "DELETE FROM empeno WHERE id_cliente =?";

    @Override
    public List<Empeno> listEmpeno(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        //codigo para quitarle los decimales al interes y el capital
        Locale locale = new Locale("en", "UK");
        String pattern = "###.##";
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(locale);
        decimalFormat.applyPattern(pattern);
        List<Empeno> lista = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            stmt.setInt(1, cliente.getIdCliente());
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idEmpeno = rs.getInt("id_empeno");
                Date fechaEmpeno = rs.getDate("fecha_empeno");
                String fechaVencimiento = rs.getString("fecha_vencimiento");
                Date ultimoPago = rs.getDate("ultimo_pago");
                double adelanto = rs.getDouble("adelanto");
                String tipoPago = rs.getString("tipo_pago");
                double tasa = rs.getDouble("tasa");
                String estado = rs.getString("estado");
                double valorEmpeno = rs.getDouble("valor_empeno");
                double interesEmpeno = rs.getDouble("interes_empeno");
                int idCliente = rs.getInt("id_cliente");
                String nombrePrenda = rs.getString("nombre_prenda");
                String peso = rs.getString("peso");
                String categoria = rs.getString("categoria");
                String descripcion = rs.getString("descripcion");
                
                
                
                String interes = decimalFormat.format(interesEmpeno);
                double interesFormated = Double.parseDouble(interes);

                LocalDate fechaVenMod = LocalDate.parse(fechaVencimiento);

                Empeno empeno = new Empeno(idEmpeno, fechaEmpeno, fechaVenMod, ultimoPago, adelanto, tipoPago, tasa, estado, valorEmpeno, interesFormated, idCliente, nombrePrenda,
                        peso, categoria, descripcion);
                lista.add(empeno);

            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {         
            Conexion.close(stmt);
            Conexion.close(rs);
            Conexion.close(conn);
        }

        return lista;
    }

    @Override
    public Empeno findEmpenoById(int idEmpenos) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Empeno empeno = new Empeno();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, idEmpenos);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idEmpeno = rs.getInt("id_empeno");
                Date fechaEmpeno = rs.getDate("fecha_empeno");
                String fechaVencimiento = rs.getString("fecha_vencimiento");
                Date ultimoPago = rs.getDate("ultimo_pago");
                double adelanto = rs.getDouble("adelanto");
                String tipoPago = rs.getString("tipo_Pago");
                double tasa = rs.getDouble("tasa");
                String estado = rs.getString("estado");
                double valorEmpeno = rs.getDouble("valor_empeno");
                double interesEmpeno = rs.getDouble("interes_empeno");
                int idCliente = rs.getInt("id_cliente");
                String nombrePrenda = rs.getString("nombre_prenda");
                String peso = rs.getString("peso");
                String categoria = rs.getString("categoria");
                String descripcion = rs.getString("descripcion");

                LocalDate fechaVenMod = LocalDate.parse(fechaVencimiento);
                empeno = new Empeno(idEmpeno, fechaEmpeno, fechaVenMod, ultimoPago, adelanto, tipoPago, tasa, estado, valorEmpeno, interesEmpeno, idCliente,
                        nombrePrenda, peso, categoria, descripcion);

            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
           
            Conexion.close(stmt);
            Conexion.close(rs);
            Conexion.close(conn);
        }

        return empeno;
    }

    @Override
    public Empeno findEmpenoByName(Empeno empeno) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int insertEmpeno(Empeno empeno) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int row = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, empeno.getFechaVencimiento().toString());
            stmt.setDouble(2, empeno.getAdelanto());
            stmt.setString(3, empeno.getTipoPago());
            stmt.setDouble(4, empeno.getTasa());
            stmt.setString(5, empeno.getEstado());
            stmt.setDouble(6, empeno.getValorEmpeno());
            stmt.setDouble(7, empeno.getInteresEmpeno());
            stmt.setInt(8, empeno.getIdCliente());
            stmt.setString(9, empeno.getNombrePrenda());
            stmt.setString(10, empeno.getPeso());
            stmt.setString(11, empeno.getCategoria());
            stmt.setString(12, empeno.getDescripcion());

            row = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            
            Conexion.close(stmt);
            Conexion.close(conn);

        }
        return row;
    }

    @Override
    public int updateEmpeno(Empeno empeno) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int row = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, empeno.getFechaVencimiento().toString());
            stmt.setDate(2, (java.sql.Date) empeno.getUltimoPago());
            stmt.setDouble(3, empeno.getAdelanto());
            stmt.setString(4, empeno.getTipoPago());
            stmt.setDouble(5, empeno.getTasa());
            stmt.setString(6, empeno.getEstado());
            stmt.setDouble(7, empeno.getValorEmpeno());
            stmt.setDouble(8, empeno.getInteresEmpeno());
            stmt.setString(9, empeno.getNombrePrenda());
            stmt.setString(10, empeno.getPeso());
            stmt.setString(11, empeno.getCategoria());
            stmt.setString(12, empeno.getDescripcion());
            stmt.setInt(13, empeno.getIdEmpeno());

            row = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            
            Conexion.close(stmt);
            Conexion.close(conn);

        }
        return row;
    }

    @Override
    public int deleteEmpeno(Empeno empeno) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int row = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, empeno.getIdEmpeno());

            row = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(conn);
            Conexion.close(stmt);

        }
        return row;
    }

    @Override
    public List<Empeno> listAllEmpeno() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Empeno> lista = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_ALL);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idEmpeno = rs.getInt("id_empeno");
                Date fechaEmpeno = rs.getDate("fecha_empeno");
                String fechaVencimiento = rs.getString("fecha_vencimiento");
                Date ultimoPago = rs.getDate("ultimo_pago");
                double adelanto = rs.getDouble("adelanto");
                String tipoPago = rs.getString("tipo_pago");
                double tasa = rs.getDouble("tasa");
                String estado = rs.getString("estado");
                double valorEmpeno = rs.getDouble("valor_empeno");
                double interesEmpeno = rs.getDouble("interes_empeno");
                int idCliente = rs.getInt("id_cliente");
                String nombrePrenda = rs.getString("nombre_prenda");
                String peso = rs.getString("peso");
                String categoria = rs.getString("categoria");
                String descripcion = rs.getString("descripcion");

                LocalDate fechaVenMod = LocalDate.parse(fechaVencimiento);

                Empeno empeno = new Empeno(idEmpeno, fechaEmpeno, fechaVenMod, ultimoPago, adelanto, tipoPago, tasa, estado, valorEmpeno, interesEmpeno, idCliente, nombrePrenda,
                        peso, categoria, descripcion);
                lista.add(empeno);

            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            
            Conexion.close(stmt);
            Conexion.close(rs);
            Conexion.close(conn);
        }

        return lista;
    }

    @Override
    public int updateEstado(Empeno empeno) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int row = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE_ESTADO);
            stmt.setString(1, empeno.getEstado());
            stmt.setInt(2, empeno.getIdEmpeno());

            row = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return row;
    }

    @Override
    public int updateInteres(Empeno empeno) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int row = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE_INTERES);
            stmt.setDouble(1, empeno.getInteresEmpeno());
            stmt.setInt(2, empeno.getIdEmpeno());

            row = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return row;
    }

    @Override
    public int updateUltimoPago(Empeno empeno) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int row = 0;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE_ULTIMOPAGO);
            java.sql.Date ultimoPago = new java.sql.Date(empeno.getUltimoPago().getTime());
            stmt.setDate(1, ultimoPago);////////////////un fallo bacanisimo
            stmt.setInt(2, empeno.getIdEmpeno());
            
            row = stmt.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return row;
    }

    @Override
    public int deleteEmpenoCliente(Cliente cliente) {
           Connection conn = null;
        PreparedStatement stmt = null;
        int row = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, cliente.getIdCliente());

            row = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(conn);
            Conexion.close(stmt);

        }
        return row;
    }

}
