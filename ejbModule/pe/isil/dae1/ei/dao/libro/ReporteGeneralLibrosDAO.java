package pe.isil.dae1.ei.dao.libro;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import pe.isil.dae1.ei.model.libro.ReporteGeneralLibros;

@Stateless
public class ReporteGeneralLibrosDAO {

    @PersistenceContext(unitName = "ProyectoEJBLibrosPU")
    private EntityManager em;

    public List<ReporteGeneralLibros> obtenerReporte(String titulo, String autor) {
        String sql = "EXEC GX_sp_reporte_general_libros_usuarios ?, ?"; // GX el numero de grupo 
        Query q = em.createNativeQuery(sql, "ReporteGeneralLibrosMapping");
        q.setParameter(1, titulo);
        q.setParameter(2, autor);

        @SuppressWarnings("unchecked")
        List<ReporteGeneralLibros> lista = q.getResultList();
        lista.forEach(em::detach);
        return lista;
    }
}
