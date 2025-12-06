package pe.isil.dae1.ei.service.producto;

import java.util.List;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import pe.isil.dae1.ei.dao.producto.ProductoDAO;
import pe.isil.dae1.ei.dao.UsuarioDAO;
import pe.isil.dae1.ei.model.producto.Producto;


@Stateless
@LocalBean
public class ProductoService implements ProductoServiceLocal {

	@PersistenceContext(unitName = "UnidadPersistenciaEI")
	protected EntityManager em;

	
	//Inyectamos el DAO
    @Inject
    private ProductoDAO productoDAO;
    
    @Inject
    private UsuarioDAO usuarioDAO;

    public ProductoService() {
    }
    
    // Obtener por categoría
    @Override
    public Producto obtenerPorCategoria(String categoria) {
        return productoDAO.obtenerPorCategoria(categoria);
    }
    
    // Obtener por Id
    @Override
    public Producto obtenerPorId(int idProducto) {
        return productoDAO.obtenerPorId(idProducto);
    }
    

    @Override
    public Producto obtenerPorNombre(String nombre) {
        return productoDAO.obtenerPorNombre(nombre);
    }
    
    @Override
    public List<Producto> buscarPorCampo(String campo, String valor) {

        String jpql = "SELECT p FROM Producto p WHERE ";

        switch (campo) {
            case "nombre":
                jpql += "p.nombre LIKE :v";
                break;

            case "categoria":
                jpql += "p.categoria LIKE :v";
                break;

            case "descripcion":
                jpql += "p.descripcion LIKE :v";
                break;

            default:
                return listar();
        }

        return em.createQuery(jpql, Producto.class)
                 .setParameter("v", "%" + valor.toLowerCase() + "%")
                 .getResultList();
    }



 // Insertar con validaciones empresariales similares (tengo que definir los errores tmb)
    @Override
    public int insertar(Producto obj) {

        if (obj == null) return -1;

        if (obj.getNombre() == null || obj.getNombre().trim().isEmpty())
            return -11;

        if (obj.getPrecio() == null || obj.getPrecio().doubleValue() < 0)
            return -12;

        if (obj.getStock() < 0)
            return -13;

        if (obj.getCategoria() == null || obj.getCategoria().trim().isEmpty())
            return -14;

        // Nombre duplicado
        Producto existe = productoDAO.obtenerPorNombre(obj.getNombre());
        if (existe != null)
            return -15;

        Producto respuesta = productoDAO.insertar(obj);

        return (respuesta != null) ? respuesta.getId() : -99;
    }



    // Actualizar ( luego tengo que definir los errores)
    @Override
    public int actualizar(Producto obj) {

        if (obj == null || obj.getId() <= 0)
            return -1;

        if (obj.getNombre() == null || obj.getNombre().trim().isEmpty())
            return -11;

        if (obj.getPrecio() == null || obj.getPrecio().doubleValue() < 0)
            return -12;

        if (obj.getStock() < 0)
            return -13;

        if (obj.getCategoria() == null || obj.getCategoria().trim().isEmpty())
            return -14;

        Producto respuesta = productoDAO.actualizar(obj);

        return (respuesta != null) ? respuesta.getId() : -99;
    }


    @Override
    public int eliminar(int idProducto) {
    	
        try {
        	
            productoDAO.eliminar(idProducto);
            return 1;       
        } 
        catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Producto> listar() {
        return productoDAO.listar();
    }


}
