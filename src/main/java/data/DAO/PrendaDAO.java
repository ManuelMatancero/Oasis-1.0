package data.DAO;

import java.util.List;
import models.Empeno;
import models.Prenda;

/**
 *
 * @author matan
 */
public interface PrendaDAO {
    
    public List<Prenda> listItems();
    public Prenda findItemById(Empeno empeno);
    public Prenda findItemByName(Prenda prenda);
    public int insertItem(Prenda prenda);
    public int updateItem(Prenda prenda);
    public int deletePrenda(Prenda prenda);
    
    
}
