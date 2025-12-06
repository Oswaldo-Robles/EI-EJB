package pe.isil.dae1.ei.dao.producto;
import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import pe.isil.dae1.ei.model.producto.ReporteGeneral;

@Stateless
public class ReporteGeneralDAO {

    @PersistenceContext(unitName = "UnidadPersistenciaEI")
    protected EntityManager em;

    // Ejecuta el SP y obtiene el reporte de productos + usuarios
    public List<ReporteGeneral> obtenerReporte(String nombreProducto, String Categoria){

        // Llama al stored procedure con parámetros
        String sql = "EXEC sp_reporte_general_productos_usuarios ?,?";

        // Usa el mapeo definido en la entidad ReporteGeneral ( del ReporteGeneral (model))
        Query q = em.createNativeQuery(sql,"ReporteProductosMapping");

        // Setea los parámetros del SP
        q.setParameter(1, nombreProducto); // Envía a @pNombreProducto
        q.setParameter(2, Categoria);      // Envía a @pCategoria

        // Obtiene la lista de resultados
        @SuppressWarnings("unchecked")
        List<ReporteGeneral> lista = q.getResultList();

        // Desvincula cada objeto del EntityManager para que no sea gestionado por JPA (son solo datos de reporte)
        for (ReporteGeneral rg: lista) {
            em.detach(rg);
        }

        // Retorna la lista del reporte
        return lista;	
    }
}

