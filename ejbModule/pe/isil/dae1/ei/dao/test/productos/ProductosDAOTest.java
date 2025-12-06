package pe.isil.dae1.ei.dao.test.productos;

import static org.junit.jupiter.api.Assertions.*;


import java.lang.reflect.Field;
import java.math.BigDecimal;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import pe.isil.dae1.ei.dao.AbstractoDAO;
import pe.isil.dae1.ei.dao.producto.ProductoDAO;
import pe.isil.dae1.ei.model.producto.Producto;


//este test funciona por la extencion en el Pom.xml y tmb un pluggin en el maven
class ProductosDAOTest {
	private static EntityManagerFactory emf; //conexion a la base
	private EntityManager em;
	private ProductoDAO objProductoDAO;
	
	//antes de todo se conecta
	@BeforeAll
	static void inicializarTest() {
			emf = Persistence.createEntityManagerFactory("UnidadPersistenciaEI");
	}
	
	//antes de ejecutar cada prueba
	@BeforeEach
	void inicializarAntesTest() throws Exception{
		em = emf.createEntityManager();
		em.getTransaction().begin();
			
		objProductoDAO = new ProductoDAO();
			
		//Inyectar ese "em" al "objProductoDAO"
		Field emField = AbstractoDAO.class.getDeclaredField("em");
		emField.setAccessible(true);
		emField.set(objProductoDAO, em);
	}
		
	@AfterEach
	void finalizarDespuesTest() {
		if(em.getTransaction().isActive())
			em.getTransaction().rollback(); // rollback para que las operaciones de prueba no se guarden en la base real
		if(em.isOpen())
			em.close();
	}
		
	@AfterAll
	static void finalizarTest() {
		if(emf != null)
			emf.close();
	}

	
	
	@Test
	void testDummyProducto() {
		//base ficticia de prueba
		Producto productoPrueba = new Producto();
		productoPrueba.setId(1);
		productoPrueba.setNombre("gorra");
		productoPrueba.setDescripcion("Gorra marca cualquiera de la peor calidad");
		
		//Uusario se ha obtenido buscando el id=1
		assertNotNull(productoPrueba);
		assertEquals(1,productoPrueba.getId());
		assertEquals("gorra",productoPrueba.getNombre());
		assertEquals("Gorra marca cualquiera de la peor calidad",productoPrueba.getDescripcion());
		
	}
	
	@Test
	void testInsertarProducto() {
		Producto objProducto = new Producto();
		objProducto.setNombre("Pelota Mundial 2026");
		objProducto.setDescripcion("Pelota semi-pro del mundial 2026 que lo ganara españa");
		objProducto.setCategoria("Deportes");
		objProducto.setStock(25);
		objProducto.setPrecio(new BigDecimal("99.00"));
		
		Producto productoInsertado = objProductoDAO.insertar(objProducto);
		
		//Verifica que el objeto devuelto no sea nulo, lo que indica que la insercion fue exitosa
		assertNotNull(productoInsertado);
		
		assertTrue(productoInsertado.getId()>0);

	}
	
	@Test
	void testActualizarProducto(){
		Producto objProducto = new Producto();
		objProducto.setNombre("Libro coquito");
		objProducto.setDescripcion("libro educativo para niños de 4-5 aprox");
		objProducto.setCategoria("Libros");
		objProducto.setStock(78);
		objProducto.setPrecio(new BigDecimal("20.00"));
		
		//se inserta primero para que exista
		objProductoDAO.insertar(objProducto);
		//se modifica un campo en este caso seria el nombre del libraso coquito
		objProducto.setNombre("Libro coquito modificado");
		Producto productoActualizado = objProductoDAO.actualizar(objProducto);
		//Validaciones
		assertNotNull(productoActualizado);
		assertEquals("Libro coquito modificado", productoActualizado.getNombre());
		
	}
	
	@Test
	void testEliminarProducto(){
		Producto objProducto = new Producto();
		objProducto.setNombre("Taza peru");
		objProducto.setDescripcion("taza con los colores blanquirojos");
		objProducto.setCategoria("ceramicos");
		objProducto.setStock(120);
		objProducto.setPrecio(new BigDecimal("30.00"));
		
		// se inserta primero para que exista
		objProductoDAO.insertar(objProducto);
		
		int idGenerado = objProducto.getId();
		int resultado = objProductoDAO.eliminar(idGenerado);
		//validaciones
		assertEquals(1, resultado); // Se elimino correctamente
		assertNull(objProductoDAO.obtenerPorId(idGenerado)); // ya no debe existir
	}

}
