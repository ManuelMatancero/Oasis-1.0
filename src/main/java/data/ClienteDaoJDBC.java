package data;

import data.DAO.ClienteDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Cliente;

/**
 *
 * @author matan
 */
public class ClienteDaoJDBC implements ClienteDAO {

    private static final String SQL_SELECT = "SELECT id_cliente, nombre, apellido, cedula, direccion, telefono "
            + "FROM cliente ORDER BY date_added DESC";
    private static final String SQL_INSERT = "INSERT INTO cliente (nombre, apellido, cedula, direccion, telefono)"
            + " VALUES(?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_BY_ID = "SELECT id_cliente, nombre, apellido, cedula, direccion, telefono"
            + " FROM cliente WHERE id_cliente=?";
    private static final String SQL_UPDATE = "UPDATE cliente SET nombre=?, apellido=?, cedula=?, direccion=?, telefono=?"
            + " WHERE id_cliente=?";
    private static final String SQL_DELETE = "DELETE FROM cliente WHERE id_cliente=?";

    @Override
    public List<Cliente> listCustomers() {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Cliente> clientes = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idCliente = rs.getInt("id_cliente");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String cedula = rs.getString("cedula");
                String direccion = rs.getString("direccion");
                String telefono = rs.getString("telefono");

                Cliente cliente = new Cliente(idCliente, nombre, apellido, cedula, direccion, telefono);
                clientes.add(cliente);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
            Conexion.close(rs);

        }

        return clientes;
    }

    @Override
    public Cliente findCustomerById(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, cliente.getIdCliente());
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idCliente = rs.getInt("id_cliente");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String cedula = rs.getString("cedula");
                String direccion = rs.getString("direccion");
                String telefono = rs.getString("telefono");

                cliente = new Cliente(idCliente, nombre, apellido, cedula, direccion, telefono);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return cliente;
    }

    @Override
    public Cliente findCustomerByName(Cliente cliente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int insertCustomer(Cliente cliente) {

        Connection conn = null;
        PreparedStatement stmt = null;
        int clientesAgregados = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getCedula());
            stmt.setString(4, cliente.getDireccion());
            stmt.setString(5, cliente.getTelefono());

            clientesAgregados = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);

        }

        return clientesAgregados;
    }

    @Override
    public int updateCustomer(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int row = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getCedula());
            stmt.setString(4, cliente.getDireccion());
            stmt.setString(5, cliente.getTelefono());
            stmt.setInt(6, cliente.getIdCliente());
            
            row = stmt.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return row;
    }

    @Override
    public int deleteCustomer(Cliente cliente) {
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
