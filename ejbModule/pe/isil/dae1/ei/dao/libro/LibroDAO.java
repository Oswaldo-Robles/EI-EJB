package pe.isil.dae1.ei.dao.libro;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.TypedQuery;

import pe.isil.dae1.ei.dao.AbstactDAO;
import pe.isil.dae1.ei.model.libro.Libro;

@Stateless
public class LibroDAO extends AbstactDAO<Libro> {

    public LibroDAO() {
        super(Libro.class);
    }

    // Búsqueda por título o autor
    public List<Libro> buscarPorTituloAutor(String filtro) {
        String f = (filtro == null || filtro.isBlank()) ? null : filtro.trim();

        String jpql = "SELECT l FROM Libro l " +
                      "WHERE (:filtro IS NULL " +
                      "       OR l.titulo LIKE :filtroLike " +
                      "       OR l.autor  LIKE :filtroLike)";

        TypedQuery<Libro> q = em.createQuery(jpql, Libro.class);
        q.setParameter("filtro", f);
        q.setParameter("filtroLike", f == null ? "%" : "%" + f + "%");

        return q.getResultList();
    }

    // Validación de duplicidad
    public boolean existePorTituloAutor(String titulo, String autor) {
        String jpql = "SELECT COUNT(l) FROM Libro l " +
                      "WHERE TRIM(l.titulo) = TRIM(:titulo) " +
                      "  AND TRIM(l.autor)  = TRIM(:autor)";
        Long cantidad = em.createQuery(jpql, Long.class)
                          .setParameter("titulo", titulo)
                          .setParameter("autor", autor)
                          .getSingleResult();
        return cantidad != null && cantidad > 0;
    }

    public boolean existePorTituloAutorExceptoId(String titulo, String autor, int id) {
        String jpql = "SELECT COUNT(l) FROM Libro l " +
                      "WHERE TRIM(l.titulo) = TRIM(:titulo) " +
                      "  AND TRIM(l.autor)  = TRIM(:autor) " +
                      "  AND l.id <> :id";
        Long cantidad = em.createQuery(jpql, Long.class)
                          .setParameter("titulo", titulo)
                          .setParameter("autor", autor)
                          .setParameter("id", id)
                          .getSingleResult();
        return cantidad != null && cantidad > 0;
    }
}
