package pe.isil.dae1.ei.service.test.producto;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import pe.isil.dae1.ei.dao.AbstractoDAO;
import pe.isil.dae1.ei.dao.UsuarioDAO;
import pe.isil.dae1.ei.model.Usuario;
import pe.isil.dae1.ei.service.UsuarioService;

class UsuarioServiceTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private UsuarioDAO objUsuarioDAO;
	private UsuarioService objUsuarioService;
	
	//antes de todo se conecta
	@BeforeAll
	static void inicializarTest() {
			emf = Persistence.createEntityManagerFactory("UnidadPersistenciaEI");
	}

	@AfterAll
	static void finalizarTest(){
		if(emf != null)
			emf.close();
	}
	
	@BeforeEach
	void inicializarAntesTest() throws Exception{
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		objUsuarioDAO = new UsuarioDAO();
		Field emField = AbstractoDAO.class.getDeclaredField("em");
		emField.setAccessible(true);
		emField.set(objUsuarioDAO, em);
		
		//Lo nuevo: inyectar objUsuarioDAO en objUsuarioService
		objUsuarioService = new UsuarioService();
		Field daoField = UsuarioService.class.getDeclaredField("usuarioDAO");
		daoField.setAccessible(true);
		daoField.set(objUsuarioService, objUsuarioDAO);
	}
	
	@AfterEach
	void finalizarDespuesTest() {
		if(em.getTransaction().isActive())
			em.getTransaction().rollback(); // rollback para que las operaciones de prueba no se guarden en la base real
		if(em.isOpen())
			em.close();
	}
	
	@Test
	void testValidarUsuarioCorrecto(){
		Usuario objUsuario = new Usuario();
		objUsuario.setEmail("lmartinez@isil.pe");
		objUsuario.setPassword("abcde");		
		
		boolean esValido = objUsuarioService.validarUsuario(objUsuario);
		assertTrue(esValido);
	}
	
	@Test
	void testObtenerIdUsuarioCorrecto(){
	    Usuario objUsuario = new Usuario();
	    objUsuario.setEmail("lmartinez@isil.pe");
	    objUsuario.setPassword("abcde");

	    int id = objUsuarioService.obtenerIdUsuario(objUsuario);

	    assertTrue(id > 0); // Debe retornar un ID válido
	}

	@Test
	void testObtenerIdUsuarioIncorrecto(){
	    Usuario objUsuario = new Usuario();
	    objUsuario.setEmail("noexiste123@gmail.com");
	    objUsuario.setPassword("Constraseña123");

	    int id = objUsuarioService.obtenerIdUsuario(objUsuario);

	    assertEquals(-1, id); // Si usuario no existe → debe retornar -1
	}
	
	@Test
	void testObtenerUsuarioPorId() {
	    // inserta los datos de prueba
	    Usuario u = new Usuario();
	    u.setNombre("Prueba");
	    u.setApellido("Test");
	    u.setEmail("prueba@test.com");
	    u.setPassword("123456");

	    objUsuarioDAO.insertar(u);
	    int idGenerado = u.getId();

	    // llama al servicio
	    Usuario obtenido = objUsuarioService.obtenerPorId(idGenerado);

	    // verificaciones
	    assertNotNull(obtenido);
	    assertEquals("Prueba", obtenido.getNombre());
	}
	
	@Test
	void testObtenerUsuarioPorIdIncorrecto() {
	    Usuario obtenido = objUsuarioService.obtenerPorId(-19);
	    assertNull(obtenido);
	}




}

