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
import pe.isil.dae1.ei.model.libro.Libro;

class LibroDAOTest {

    private static EntityManagerFactory emf;
    private EntityManager em;
    private LibroDAO libroDAO;

    @BeforeAll
    static void inicializarTest() {
        // PU de pruebas (igual que en UsuarioDAOTest del profe)
        emf = Persistence.createEntityManagerFactory("ProyectoEJBDAE1Test");
    }

    @BeforeEach
    void inicializarAntesTest() throws Exception {
        em = emf.createEntityManager();
        em.getTransaction().begin();

        libroDAO = new LibroDAO();

        // Inyectar el EntityManager en el AbstactDAO usando reflexión
        Field emField = AbstactDAO.class.getDeclaredField("em");
        emField.setAccessible(true);
        emField.set(libroDAO, em);
    }

    @AfterEach
    void finalizarDespuesTest() {
        if (em.getTransaction().isActive())
            em.getTransaction().commit();
        if (em.isOpen())
            em.close();
    }

    @AfterAll
    static void finalizarTest() {
        if (emf != null)
            emf.close();
    }

    @Test
    void testListarLibros() {
        List<Libro> lista = libroDAO.listar();
        assertNotNull(lista);
        // Si sabes que hay datos, podrías hacer:
        // assertTrue(lista.size() > 0);
    }

    @Test
    void testInsertarLibro() {
        Libro l = new Libro();
        // Ajusta estos campos a los que tu entidad realmente tiene
        l.setTitulo("Libro de prueba JUnit");
        l.setAutor("Autor JUnit");
        // Si tienes más campos NOT NULL en la BD, setéalos aquí:
        // l.setIsbn("1234567890");
        // l.setEditorial("Editorial Test");
        // l.setAnioPublicacion(2024);
        // etc.

        Libro insertado = libroDAO.insertar(l);

        // Ajusta getId() si tu campo se llama distinto (idLibro, etc.)
        assertNotNull(insertado);
        assertTrue(insertado.getId() > 0);
    }

    @Test
    void testActualizarLibro() {
        // 1. Insertar un libro de prueba
        Libro l = new Libro();
        l.setTitulo("Titulo original");
        l.setAutor("Autor original");
        Libro insertado = libroDAO.insertar(l);

        // 2. Modificar y actualizar
        insertado.setTitulo("Titulo modificado");
        Libro actualizado = libroDAO.actualizar(insertado);

        assertEquals("Titulo modificado", actualizado.getTitulo());
    }

    @Test
    void testEliminarLibro() {
        // 1. Insertar un libro de prueba
        Libro l = new Libro();
        l.setTitulo("Libro a eliminar");
        l.setAutor("Autor X");
        Libro insertado = libroDAO.insertar(l);
        int id = insertado.getId(); // ajusta si tu id tiene otro nombre

        // 2. Eliminarlo
        libroDAO.eliminar(id);

        // 3. Verificar que ya no exista
        Libro eliminado = libroDAO.obtenerPorId(id);
        assertNull(eliminado);
    }
}
