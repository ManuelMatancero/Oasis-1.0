package data.DAO;

import models.Retiro;

/**
 *
 * @author matan
 */
public interface RetiroDAO {
    
    public int insertRetiro(Retiro ret);
    public int updateRetiro(Retiro ret);
    public int deleteRetiro(Retiro ret);
    
    
}
