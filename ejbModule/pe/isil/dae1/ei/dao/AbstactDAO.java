package pe.isil.dae1.ei.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

public abstract class AbstactDAO<T> implements GenericDAO<T> {
	
	@PersistenceContext(unitName = "UnidadPersistenciaEI") // mismo nombre declarado en la linea 7 de persistence.xml
	protected EntityManager em;
	
	private Class<T> entityClass;
	
	protected AbstactDAO(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	@Override
	public T obtenerPorId(int id) {
		return em.find(entityClass, id);
	}

	@Override
	public T insertar(T objeto) {
		em.persist(objeto);
		return objeto;
	}

	@Override
	public T actualizar(T objeto) {
		return em.merge(objeto);
	}

	@Override
	public void eliminar(int id) {
		T objeto = em.find(entityClass, id);
		if (objeto != null) {
			em.remove(objeto);
		}
	}

	@Override
	public List<T> listar() {
		TypedQuery<T> q = em.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass);
		return q.getResultList();
	}

}
