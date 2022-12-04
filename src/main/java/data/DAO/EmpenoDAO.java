package data.DAO;

import java.util.List;
import models.Cliente;
import models.Empeno;

/**
 *
 * @author matan
 */
public interface EmpenoDAO {
    
    public List<Empeno> listEmpeno(Cliente cliente);
    public List<Empeno> listAllEmpeno();
    public Empeno findEmpenoById(int idEmpeno);
    public Empeno findEmpenoByName(Empeno empeno);
    public int insertEmpeno(Empeno empeno);
    public int updateEmpeno(Empeno empeno);
    public int updateEstado(Empeno empeno);
    public int updateUltimoPago(Empeno empeno);
    public int updateInteres(Empeno empeno);
    public int deleteEmpeno(Empeno empeno);
    public int deleteEmpenoCliente(Cliente cliente);
    
}
