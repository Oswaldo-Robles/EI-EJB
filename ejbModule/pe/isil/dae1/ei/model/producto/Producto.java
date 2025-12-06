package pe.isil.dae1.ei.model.producto;

import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import pe.isil.dae1.ei.model.BaseEntity;
import pe.isil.dae1.ei.model.Usuario;

@Entity
@Table(name = "Productos")
public class Producto extends BaseEntity {

    public Producto() {}


    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 255)
    private String descripcion;

    @Column(nullable = false)
    private BigDecimal precio;

    @Column(nullable = false)
    private int stock;

    @Column(length = 50)
    private String categoria;

    // Relaciones con Usuario (FK)
    @ManyToOne
    @JoinColumn(name = "usuario_creacion", referencedColumnName = "id_usuario")
    private Usuario usuarioCreacion;

    @ManyToOne
    @JoinColumn(name = "usuario_actualizacion", referencedColumnName = "id_usuario")
    private Usuario usuarioActualizacion;

    // Getters y Setters
    public Usuario getUsuarioActualizacion() {
        return usuarioActualizacion;
    }

    public void setUsuarioActualizacion(Usuario usuarioActualizacion) {
        this.usuarioActualizacion = usuarioActualizacion;
    }

    public Usuario getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(Usuario usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}