
package data.DAO;

import java.util.List;
import models.Cliente;

/**
 *
 * @author matan
 */
public interface ClienteDAO {
    
    public List<Cliente> listCustomers();
    public Cliente findCustomerById(Cliente cliente);
    public Cliente findCustomerByName(Cliente cliente);
    public int insertCustomer(Cliente cliente);
    public int updateCustomer(Cliente cliente);
    public int deleteCustomer(Cliente cliente);
    
}
