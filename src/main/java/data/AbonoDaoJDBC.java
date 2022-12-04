package data;

import data.DAO.AbonoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Abono;
import models.Empeno;

/**
 *
 * @author matan
 */
public class AbonoDaoJDBC implements AbonoDAO {

    private static final String SQL_SELECT_BY_IDEMPENO = "SELECT id_abono, fecha_abono, abono, valor_capital, cargo, estado, operacion, id_empeno"
            + " FROM abono WHERE id_empeno= ?";
    private static final String SQL_SELECT_BY_ID = "SELECT id_abono, fecha_abono, abono, valor_capital, cargo, estado, operacion, id_empeno"
            + " FROM abono WHERE id_abono= ?";

    private static final String SQL_SELECT_ALL = "SELECT id_abono, fecha_abono, abono, valor_capital, cargo, estado, operacion "
            + " FROM abono";
    private static final String SQL_UPDATE = "UPDATE abono SET fecha_abono=?, abono=?, valor_capital=?, cargo=?, estado=?, operacion=?, id_empeno=?"
            + " WHERE id_abono=?";
    private static final String SQL_INSERT = "INSERT INTO abono (fecha_abono, abono, valor_capital, cargo, estado,operacion, id_empeno) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_PAGO_INTERES = "UPDATE abono SET cargo=?, abono=?, estado=?"
            + " WHERE id_abono=?";
    private static final String SQL_DELETE = "DELETE FROM abono WHERE id_empeno=?";

    @Override
    public List<Abono> listAbono(Empeno empeno) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Abono> abonos = new ArrayList<>();
        Abono abono = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_IDEMPENO);
            stmt.setInt(1, empeno.getIdEmpeno());

            rs = stmt.executeQuery();

            while (rs.next()) {
                int idAbono = rs.getInt("id_abono");
                Date fechaAbono = rs.getDate("fecha_abono");
                double valorAbono = rs.getDouble("abono");
                double valorCapital = rs.getDouble("valor_capital");
                double cargo = rs.getDouble("cargo");
                String estado = rs.getString("estado");
                String operacion = rs.getString("operacion");
                int idEmpeno = rs.getInt("id_empeno");

                abono = new Abono(idAbono, fechaAbono, valorAbono, valorCapital, cargo, estado, operacion, idEmpeno);
                abonos.add(abono);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(rs);
            Conexion.close(conn);
        }
        return abonos;
    }

    @Override
    public Abono findAbonoById(int idAbonos) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Abono abono = new Abono();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, idAbonos);

            rs = stmt.executeQuery();

            while (rs.next()) {
                int idAbono = rs.getInt("id_abono");
                Date fechaAbono = rs.getDate("fecha_abono");
                double valorAbono = rs.getDouble("abono");
                double valorCapital = rs.getDouble("valor_capital");
                double cargo = rs.getDouble("cargo");
                String estado = rs.getString("estado");
                String operacion = rs.getString("operacion");
                int idEmpeno = rs.getInt("id_empeno");

                abono = new Abono(idAbono, fechaAbono, valorAbono, valorCapital, cargo, estado, operacion, idEmpeno);

            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(rs);
            Conexion.close(conn);
        }
        return abono;
    }

    @Override
    public Abono findAbonoByName(Abono abono) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int insertAbono(Abono abono) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int row = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setDate(1, abono.getFechaAbono());
            stmt.setDouble(2, abono.getAbono());
            stmt.setDouble(3, abono.getValorCapital());
            stmt.setDouble(4, abono.getCargo());
            stmt.setString(5, abono.getEstado());
            stmt.setString(6, abono.getOperacion());
            stmt.setInt(7, abono.getIdEmpeno());

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
    public int updateAbono(Abono abono) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int row = 0;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setDate(1, abono.getFechaAbono());
            stmt.setDouble(2, abono.getAbono());
            stmt.setDouble(3, abono.getValorCapital());
            stmt.setDouble(4, abono.getCargo());
            stmt.setString(5, abono.getEstado());
            stmt.setString(6, abono.getOperacion());
            stmt.setInt(7, abono.getIdEmpeno());
            stmt.setInt(8, abono.getIdAbono());
            
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
    public int deleteAbono(Empeno empeno) {
        
        Connection conn = null;
        PreparedStatement stmt = null; 
        int row = 0;
        
        try{
            conn = Conexion.getConnection();
            stmt  = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, empeno.getIdEmpeno());
            row = stmt.executeUpdate();
            
            
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }finally{
            Conexion.close(conn);
            Conexion.close(stmt);
        }
        
        return row;
        
    }

    @Override
    public List<Abono> listAllAbono() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Abono> abonos = new ArrayList<>();
        Abono abono = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_ALL);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idAbono = rs.getInt("id_abono");
                Date fechaAbono = rs.getDate("fecha_abono");
                double valorAbono = rs.getDouble("abono");
                double valorCapital = rs.getDouble("valor_capital");
                double cargo = rs.getDouble("cargo");
                String estado = rs.getString("estado");
                String operacion = rs.getString("operacion");

                abono = new Abono(idAbono, fechaAbono, valorAbono, valorCapital, cargo, estado, operacion);
                abonos.add(abono);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(rs);
            Conexion.close(conn);
        }
        return abonos;
    }

    @Override
    public int updateAbonoPagoInteres(Abono abono) {       
        Connection conn = null;
        PreparedStatement stmt = null;
        int row = 0;
        
        try{
            conn = Conexion.getConnection();
            stmt= conn.prepareStatement(SQL_UPDATE_PAGO_INTERES);
            stmt.setDouble(1, abono.getCargo());
            stmt.setDouble(2, abono.getAbono());
            stmt.setString(3, abono.getEstado());
            stmt.setInt(4, abono.getIdAbono());
            
            row = stmt.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }finally{
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return row;
    }

}
