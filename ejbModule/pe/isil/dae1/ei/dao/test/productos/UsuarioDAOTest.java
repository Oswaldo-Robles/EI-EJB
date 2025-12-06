package pe.isil.dae1.ei.dao.test.productos;

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

//este test funciona por la extencion en el Pom.xml y tmb un pluggin en el maven
class UsuarioDAOTest {
	private static EntityManagerFactory emf; // conexion a la base
	private EntityManager em;
	private UsuarioDAO objUsuarioDAO;
	
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
		
		objUsuarioDAO = new UsuarioDAO();
		
		//Inyectar ese "em" al "objUsuarioDAO"
		Field emField = AbstractoDAO.class.getDeclaredField("em");
		emField.setAccessible(true);
		emField.set(objUsuarioDAO, em);
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

	
	// este es un test que no es de la base de datos, solo para tantear si el test funciona
	@Test
	void testDummy() {
		//fail("Not yet implemented");
		int numero = 5;
		String cadena = "cadena";
		
		assertTrue(numero == 5); // Si es 5 la prueba esta bien
		assertEquals(5, numero); // si son iguales el numero y 5 estan bien
		
		assert(cadena != null); // verifica si la cadena es distinta de nulo
		assertNotNull(cadena); // la cadena no tiene que ser nula
	}
	
	// otro test de prueba sin base real
	@Test
	void testDummyUsuario() {
		// base ficticia de prueba
		Usuario usuarioPrueba = new Usuario();
		usuarioPrueba.setId(1);
		usuarioPrueba.setNombre("Salvador");
		usuarioPrueba.setApellido("Velasquez");
		
		//Usuario se ha obtenido buscando el id = 1
		assertNotNull(usuarioPrueba);
		assertEquals(1,usuarioPrueba.getId());
		assertEquals("Salvador", usuarioPrueba.getNombre());
	    assertEquals("Velasquez", usuarioPrueba.getApellido());
		
		
	}
	
	@Test
	void testValidarUsuarioCorrecto() {
	    Usuario u = new Usuario();
	    u.setNombre("Pedro");
	    u.setApellido("Gonzales");
	    u.setEmail("pedri@gmail.com");
	    u.setPassword("MagicPedri8");

	    // se inserta primero el usuario de prueba que se borrara luego con el rollback del afterEach
	    objUsuarioDAO.insertar(u);

	    boolean esValido = objUsuarioDAO.validarUsuario("pedri@gmail.com", "MagicPedri8");
	    assertTrue(esValido);
	}

	
	@Test
	void testValidarUsuarioIncorrecto() {
		String nombreUsuario = "agarcia@isil.pe";
		String clave = "jejeje shiuuu";
		
		boolean esValido = objUsuarioDAO.validarUsuario(nombreUsuario, clave);
		assertFalse(esValido); // si el usuario no es valido la prueba fue correcta
	}
	
	@Test
	void testInsertarUsuario() {
		Usuario objUsuario = new Usuario();
		objUsuario.setNombre("Dubi");
		objUsuario.setApellido("Velez");
		objUsuario.setEmail("Velez@gmail.com");
		objUsuario.setPassword("Velez12345");
		
		Usuario usuarioInsertado = objUsuarioDAO.insertar(objUsuario);
		
		// Verifica que el objeto devuelto no sea nulo, lo que indica que la insercion fue exitosa
		assertNotNull(usuarioInsertado); 
		
		assertTrue(usuarioInsertado.getId()>0);
	}
	
	@Test
	void testActualizarUsuario() {
	    Usuario objUsuario = new Usuario();
	    objUsuario.setNombre("Vinicius");
	    objUsuario.setApellido("Junior");
	    objUsuario.setEmail("Vinijr@gmail.com");
	    objUsuario.setPassword("Clave123");

	    // se inserta primero para que exista
	    objUsuarioDAO.insertar(objUsuario);

	    // se modifica un campo en este seria el nombre de vinii
	    objUsuario.setNombre("Viniiii modificado");

	    Usuario usuarioActualizado = objUsuarioDAO.actualizar(objUsuario);

	    // Validaciones
	    assertNotNull(usuarioActualizado);
	    assertEquals("Viniiii modificado", usuarioActualizado.getNombre());
	}
	
	@Test
	void testEliminarUsuario() {
	    Usuario objUsuario = new Usuario();
	    objUsuario.setNombre("Lamine");
	    objUsuario.setApellido("Yamal");
	    objUsuario.setEmail("lamine@gmail.com");
	    objUsuario.setPassword("Lamine304");
	    
	    // se inserta primero para que exista
	    objUsuarioDAO.insertar(objUsuario);
	    
	    int idGenerado = objUsuario.getId();

	    int resultado = objUsuarioDAO.eliminar(idGenerado);

	    // Validaciones
	    assertEquals(1, resultado); // Se eliminó correctamente
	    assertNull(objUsuarioDAO.obtenerPorId(idGenerado)); // Ya no debe existir
	}


}
