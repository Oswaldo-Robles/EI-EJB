package pe.isil.dae1.ei.test.libro;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import pe.isil.dae1.ei.dao.AbstactDAO;
import pe.isil.dae1.ei.dao.libro.LibroDAO;
import pe.isil.dae1.ei.dao.libro.UsuarioDAO;
import pe.isil.dae1.ei.model.libro.Libro;
import pe.isil.dae1.ei.model.libro.Usuario;
import pe.isil.dae1.ei.service.libro.LibroService;
import pe.isil.dae1.ei.service.libro.LibroServiceLocal;

class LibroServiceTest {

    private static EntityManagerFactory emf;
    private EntityManager em;
    private LibroDAO libroDAO;
    private UsuarioDAO usuarioDAO;
    private LibroServiceLocal libroService;

    @BeforeAll
    static void inicializarTest() {
        emf = Persistence.createEntityManagerFactory("ProyectoEJBDAE1Test");
    }

    @AfterAll
    static void finalizarTest() {
        if (emf != null)
            emf.close();
    }

    @BeforeEach
    void inicializarAntesTest() throws Exception {
        em = emf.createEntityManager();
        em.getTransaction().begin();

        // 1. Inicializar DAOs
        libroDAO = new LibroDAO();
        usuarioDAO = new UsuarioDAO();

        // Inyectar EntityManager en ambos DAOs (AbstactDAO)
        Field emField = AbstactDAO.class.getDeclaredField("em");
        emField.setAccessible(true);
        emField.set(libroDAO, em);
        emField.set(usuarioDAO, em);

        // 2. Inicializar Service e inyectar DAOs por reflexión
        libroService = new LibroService();

        Field libroDaoField = LibroService.class.getDeclaredField("libroDAO");
        libroDaoField.setAccessible(true);
        libroDaoField.set(libroService, libroDAO);

        Field usuarioDaoField = LibroService.class.getDeclaredField("usuarioDAO");
        usuarioDaoField.setAccessible(true);
        usuarioDaoField.set(libroService, usuarioDAO);
    }

    @AfterEach
    void finalizarDespuesTest() {
        if (em.getTransaction().isActive())
            em.getTransaction().commit();
        if (em.isOpen())
            em.close();
    }

    @Test
    void testListarLibrosService() {
        List<Libro> lista = libroService.listar();
        assertNotNull(lista);
    }

    @Test
    void testInsertarLibroService() {
        // Usuario de sesión "ficticio": asume que existe en BD con id = 1
        Usuario uSesion = new Usuario();
        uSesion.setId(1); // ajusta al nombre real del id en Usuario

        Libro l = new Libro();
        l.setTitulo("Libro Service Test");
        l.setAutor("Autor Service");
        // setea otros campos requeridos si tu BD los exige

        Libro insertado = libroService.insertar(l, uSesion);

        assertNotNull(insertado);
        assertTrue(insertado.getId() > 0); // ajusta nombre del id
    }

    @Test
    void testActualizarLibroService() {
        Usuario uSesion = new Usuario();
        uSesion.setId(1);

        // 1. Insertar un libro
        Libro l = new Libro();
        l.setTitulo("Libro Original");
        l.setAutor("Autor Original");
        Libro insertado = libroService.insertar(l, uSesion);

        // 2. Modificar y actualizar
        insertado.setTitulo("Libro Modificado");
        Libro actualizado = libroService.actualizar(insertado, uSesion);

        assertEquals("Libro Modificado", actualizado.getTitulo());
    }

    @Test
    void testEliminarLibroService() {
        Usuario uSesion = new Usuario();
        uSesion.setId(1);

        // 1. Insertar libro
        Libro l = new Libro();
        l.setTitulo("Libro a eliminar Service");
        l.setAutor("Autor X");
        Libro insertado = libroService.insertar(l, uSesion);
        int id = insertado.getId(); // ajusta nombre de id

        // 2. Eliminar
        libroService.eliminar(id);

        // 3. Confirmar que ya no exista
        Libro eliminado = libroService.obtenerPorId(id);
        assertNull(eliminado);
    }
}
