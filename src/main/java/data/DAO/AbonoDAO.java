package data.DAO;

import java.util.List;
import models.Abono;
import models.Empeno;

/**
 *
 * @author matan
 */
public interface AbonoDAO {

    public List<Abono> listAbono(Empeno empeno);
    public List<Abono> listAllAbono();

    public Abono findAbonoById(int idAbonos);

    public Abono findAbonoByName(Abono abono);

    public int insertAbono(Abono abono);

    public int updateAbono(Abono abono);
    public int updateAbonoPagoInteres(Abono abono);

    public int deleteAbono(Empeno empeno);

}
