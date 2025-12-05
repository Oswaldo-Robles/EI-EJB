package pe.isil.dae1.ei.model.libro;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Libros")
public class Libro extends BaseEntity {

    @NotNull
    @Column(nullable = false, length = 200)
    private String titulo;

    @NotNull
    @Column(nullable = false, length = 150)
    private String autor;

    @Column(length = 20)
    private String isbn;

    @Column(length = 100)
    private String editorial;

    @Column(name = "anio_publicacion")
    private Integer anioPublicacion;

    @Column(name = "numero_paginas")
    private Integer numeroPaginas;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_creacion", referencedColumnName = "id_usuario")
    private Usuario usuarioCreacion;

    @ManyToOne
    @JoinColumn(name = "usuario_actualizacion", referencedColumnName = "id_usuario")
    private Usuario usuarioActualizacion;

    // getters y setters...

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getEditorial() { return editorial; }
    public void setEditorial(String editorial) { this.editorial = editorial; }

    public Integer getAnioPublicacion() { return anioPublicacion; }
    public void setAnioPublicacion(Integer anioPublicacion) { this.anioPublicacion = anioPublicacion; }

    public Integer getNumeroPaginas() { return numeroPaginas; }
    public void setNumeroPaginas(Integer numeroPaginas) { this.numeroPaginas = numeroPaginas; }

    public Usuario getUsuarioCreacion() { return usuarioCreacion; }
    public void setUsuarioCreacion(Usuario usuarioCreacion) { this.usuarioCreacion = usuarioCreacion; }

    public Usuario getUsuarioActualizacion() { return usuarioActualizacion; }
    public void setUsuarioActualizacion(Usuario usuarioActualizacion) { this.usuarioActualizacion = usuarioActualizacion; }
}
