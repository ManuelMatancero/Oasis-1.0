package data;

import data.DAO.PrendaDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Empeno;
import models.Prenda;

/**
 *
 * @author matan
 */
public class PrendaDaoJDBC implements PrendaDAO {

    public static final String SQL_SELECT = "SELECT id_prenda, nombre_prenda, peso, tipo_prenda, valor_prenda, descripcion,"
            + "id_empeno FROM prenda";
    public static final String SQL_INSERT = "INSERT INTO prenda(nombre_prenda,  peso, tipo_prenda, valor_prenda, descripcion,"
            + " id_empeno) VALUES(?, ?, ?, ?, ?, ?)";
    public static final String SQL_UPDATE = "UPDATE prenda SET nombre_prenda=?, peso=?, tipo_prenda=?,"
            + " valor_prenda=?, descripcion=? WHERE id_prenda=?";
    public static final String SQL_DELETE = "DELETE FROM prenda WHERE id_prenda=?";

    public static final String SQL_SELECT_BY_ID = "SELECT id_prenda, nombre_prenda, peso, tipo_prenda, valor_prenda, descripcion,"
            + " FROM empeno WHERE id_empeno= ?";

    @Override
    public List<Prenda> listItems() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Prenda> lista = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);

            rs = stmt.executeQuery();

            while (rs.next()) {
                int idPrenda = rs.getInt("id_prenda");
                String nombrePrenda = rs.getString("nombre_prenda");
                String peso = rs.getString("peso");
                String tipoPrenda = rs.getString("tipo_prenda");
                double valorPrenda = rs.getDouble("valor_prenda");
                String descripcion = rs.getString("descripcion");
                int idEmpeno = rs.getInt("id_empeno");

                Prenda prenda = new Prenda(idPrenda, nombrePrenda, peso, tipoPrenda, valorPrenda, descripcion, idEmpeno);
                lista.add(prenda);

            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(conn);
            Conexion.close(stmt);
            Conexion.close(rs);
        }

        return lista;
    }

    @Override
    public Prenda findItemById(Empeno empeno) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Prenda prenda = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, empeno.getIdEmpeno());
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idPrenda = rs.getInt("id_prenda");
                String nombrePrenda = rs.getString("nombre_prenda");
                String peso = rs.getString("peso");
                String tipoPrenda = rs.getString("tipo_prenda");
                double valorPrenda = rs.getDouble("valor_prenda");
                String descripcion = rs.getString("descripcion");

                prenda = new Prenda(idPrenda, nombrePrenda, peso, tipoPrenda, valorPrenda, descripcion);

            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(conn);
            Conexion.close(stmt);
            Conexion.close(rs);
        }

        return prenda;
    }

    @Override
    public Prenda findItemByName(Prenda prenda) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int insertItem(Prenda prenda) {

        Connection conn = null;
        PreparedStatement stmt = null;
        int row = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, prenda.getNombrePrenda());
            stmt.setString(2, prenda.getPeso());
            stmt.setString(3, prenda.getTipoPrenda());
            stmt.setDouble(4, prenda.getValorPrenda());
            stmt.setString(5, prenda.getDescripcion());
            stmt.setInt(6, prenda.getIdEmpeno());

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
    public int updateItem(Prenda prenda) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int row = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, prenda.getNombrePrenda());
            stmt.setString(2, prenda.getPeso());
            stmt.setString(3, prenda.getTipoPrenda());
            stmt.setDouble(4, prenda.getValorPrenda());
            stmt.setString(5, prenda.getDescripcion());
            stmt.setInt(6, prenda.getIdPrenda());

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
    public int deletePrenda(Prenda prenda) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int row = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, prenda.getIdPrenda());
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
