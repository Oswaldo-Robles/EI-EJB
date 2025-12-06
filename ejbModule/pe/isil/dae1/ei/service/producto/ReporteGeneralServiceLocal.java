package pe.isil.dae1.ei.service.producto;

import jakarta.ejb.Local;
import pe.isil.dae1.ei.model.producto.ReporteGeneral;
import java.util.List;

@Local
public interface ReporteGeneralServiceLocal {
	
	public List<ReporteGeneral> obtenerReporteGeneral(String nombreProducto, String Categoria );
}
