package pe.isil.dae1.ei.service.producto;

import java.util.List;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;
import pe.isil.dae1.ei.dao.producto.ReporteGeneralDAO;
import pe.isil.dae1.ei.model.producto.ReporteGeneral;
/**
 * Session Bean implementation class ReporteGeneralService
 */
@Stateless
@LocalBean
public class ReporteGeneralService implements ReporteGeneralServiceLocal {
	@Inject
	private ReporteGeneralDAO objReporteGeneralDAO;
	
   
    public ReporteGeneralService() {
     
    }
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<ReporteGeneral> obtenerReporteGeneral(String nombreProducto, String Categoria){
     return objReporteGeneralDAO.obtenerReporte(nombreProducto, Categoria);
    }
}
