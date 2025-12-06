package pe.isil.dae1.ei.service.test.producto;

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
import pe.isil.dae1.ei.service.producto.ProductoService;

class ProductosServiceTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private ProductoDAO objProductoDAO;
	private ProductoService objProductoService;
	
	//antes de todo se conecta
	@BeforeAll
	static void inicializarTest() {
			emf = Persistence.createEntityManagerFactory("UnidadPersistenciaEI");
	}
	
	@AfterAll
	static void finalizarTest() {
		if(emf != null)
			emf.close();
	}
	
	@BeforeEach
	void inicializarAntesTest() throws Exception{
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		objProductoDAO = new ProductoDAO();
		Field emField = AbstractoDAO.class.getDeclaredField("em");
		emField.setAccessible(true);
		emField.set(objProductoDAO, em);
		
		//Lo nuevo: inyectar objProductoDAO en objProductoService
		objProductoService = new ProductoService();
		Field daoField = ProductoService.class.getDeclaredField("productoDAO");
		daoField.setAccessible(true);
		daoField.set(objProductoService, objProductoDAO);
		
		Field emServiceField = ProductoService.class.getDeclaredField("em");
		emServiceField.setAccessible(true);
		emServiceField.set(objProductoService, em);
	}
	
	@AfterEach
	void finalizarDespuesTest(){
		if(em.getTransaction().isActive())
		em.getTransaction().rollback(); //rollback para que las operaciones de prueba no se guarden en la base real
		
		if(em.isOpen())
			em.close();
	}
	

	@Test
	void testObtenerProductoPorId() {
	    // inserta los datos de prueba
	    Producto p = new Producto();
	    p.setNombre("Prueba");
	    p.setCategoria("Test");
	    p.setDescripcion("producto de prueba");
	    p.setStock(10);
	    p.setPrecio(new BigDecimal("99.00"));
	    objProductoDAO.insertar(p);
	    int idGenerado = p.getId();
	    // llama al servicio
	    Producto obtenido = objProductoService.obtenerPorId(idGenerado);
	    // verificaciones
	    assertNotNull(obtenido);
	    assertEquals("Prueba", obtenido.getNombre());
	}
	
	@Test
	void testObtenerPorCategoriaCorrecto() {
	    Producto p = new Producto();
	    p.setNombre("Balon Champions 2026");
	    p.setCategoria("Deportes");
	    p.setDescripcion("Balon oficial UEFA");
	    p.setStock(15);
	    p.setPrecio(new BigDecimal("150.00"));

	    objProductoDAO.insertar(p);

	    Producto obtenido = objProductoService.obtenerPorCategoria("Deportes");
	    assertNotNull(obtenido);
	    assertEquals("Balon Champions 2026", obtenido.getNombre());
	}

	@Test
	void testObtenerPorCategoriaIncorrecto() {
	    Producto obtenido = objProductoService.obtenerPorCategoria("CategoriaQueNoExiste");
	    assertNull(obtenido); // No debe encontrar nada
	}

	@Test
	void testObtenerPorNombreCorrecto() {
	    Producto p = new Producto();
	    p.setNombre("Zapatillas Nike");
	    p.setCategoria("Ropa");
	    p.setDescripcion("Zapatillas deportivas");
	    p.setStock(25);
	    p.setPrecio(new BigDecimal("250.50"));

	    objProductoDAO.insertar(p);

	    Producto obtenido = objProductoService.obtenerPorNombre("Zapatillas Nike");
	    assertNotNull(obtenido);
	    assertEquals("Ropa", obtenido.getCategoria());
	}

	@Test
	void testObtenerPorNombreIncorrecto() {
	    Producto obtenido = objProductoService.obtenerPorNombre("ProductoInexistente");
	    assertNull(obtenido);
	}

	@Test
	void testBuscarPorCampoNombre() {
	    Producto p = new Producto();
	    p.setNombre("Mouse Gamer Logitech");
	    p.setCategoria("Tecnología");
	    p.setDescripcion("Mouse RGB gamer");
	    p.setStock(50);
	    p.setPrecio(new BigDecimal("120.99"));

	    objProductoDAO.insertar(p);

	    var lista = objProductoService.buscarPorCampo("nombre", "Mouse Gamer Logitech");
	    assertFalse(lista.isEmpty()); // Debe encontrarlo
	}

	@Test
	void testBuscarPorCampoCategoria() {
	    Producto p = new Producto();
	    p.setNombre("Audífonos Sony");
	    p.setCategoria("Electrónica");
	    p.setDescripcion("Audífonos Bluetooth");
	    p.setStock(20);
	    p.setPrecio(new BigDecimal("200.00"));

	    objProductoDAO.insertar(p);

	    var lista = objProductoService.buscarPorCampo("categoria", "Electrónica");
	    assertFalse(lista.isEmpty());
	}

	@Test
	void testBuscarPorCampoDescripcion() {
	    Producto p = new Producto();
	    p.setNombre("Camiseta Adidas");
	    p.setCategoria("Ropa");
	    p.setDescripcion("Camiseta deportiva");
	    p.setStock(40);
	    p.setPrecio(new BigDecimal("89.99"));

	    objProductoDAO.insertar(p);

	    var lista = objProductoService.buscarPorCampo("descripcion", "Camiseta deportiva");
	    assertFalse(lista.isEmpty());
	}

	
	// este test es si el campo no existe, debe devolver la lista completa sin errores.
	@Test
	void testBuscarPorCampoInvalido() {
	    var lista = objProductoService.buscarPorCampo("campoNoValido", "x");
	    assertNotNull(lista);
	    assertTrue(lista.size() >= 0); // No debe romper la consulta
	}


}

