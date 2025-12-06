package pe.isil.dae1.ei.model.producto;
	
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


@Entity
@SqlResultSetMapping(
		 name = "ReporteProductosMapping", // en el mio seria reporteProductosMapping(pero es un nombre general en si)
		  entities = @EntityResult(entityClass = ReporteGeneral.class)
)
public class ReporteGeneral {
		
	 // Constructor vacío
	  public ReporteGeneral() {}
	   
	   // Usamos id como @Id (solo por requerimiento de JPA)
	   @Id
	   @Column(name = "id")
	   private Long idProducto;
	   
	   @Column(name = "nombre_producto")
	   private String nombreProducto;
	   
	   @Column(name = "descripcion_producto")
	   private String descripcionProducto;
	   
	   @Column(name = "precio_producto")
	   private BigDecimal precioProducto;
	   
	   @Column(name = "stock_producto")
	   private Integer stockProducto;
	   
	   @Column(name = "categoria_producto")
	   private String categoriaProducto;
	   
	   @Temporal(TemporalType.TIMESTAMP)
	   @Column(name = "fecha_creacion_producto")
	   private Date fechaCreacionProducto;
	   
	   @Temporal(TemporalType.TIMESTAMP)
	   @Column(name = "fecha_actualizacion_producto")
	   private Date fechaActualizacionProducto;
	   
	   @Column(name = "id_usuario_creacion")
	   private Long idUsuarioCreacion;
	   
	   @Column(name = "nombre_usuario_creacion")
	   private String nombreUsuarioCreacion;
	   
	   @Column(name = "apellido_usuario_creacion")
	   private String apellidoUsuarioCreacion;
	   
	   @Column(name = "email_usuario_creacion")
	   private String emailUsuarioCreacion;
	   
	   @Column(name = "id_usuario_actualizacion")
	   private Long idUsuarioActualizacion;
	   
	   @Column(name = "nombre_usuario_actualizacion")
	   private String nombreUsuarioActualizacion;
	   
	   @Column(name = "apellido_usuario_actualizacion")
	   private String apellidoUsuarioActualizacion;
	   
	   @Column(name = "email_usuario_actualizacion")
	   private String emailUsuarioActualizacion;
	   
	   
	   
	   // Getters y setters (puedes generar con tu IDE)
	   
	       public String getEmailUsuarioActualizacion() {
			return emailUsuarioActualizacion;
		   }

		   public void setEmailUsuarioActualizacion(String emailUsuarioActualizacion) {
			this.emailUsuarioActualizacion = emailUsuarioActualizacion;
		   }

		   public String getApellidoUsuarioActualizacion() {
			return apellidoUsuarioActualizacion;
		   }

		   public void setApellidoUsuarioActualizacion(String apellidoUsuarioActualizacion) {
			this.apellidoUsuarioActualizacion = apellidoUsuarioActualizacion;
		   }

		   public String getNombreUsuarioActualizacion() {
			return nombreUsuarioActualizacion;
		   }

		   public void setNombreUsuarioActualizacion(String nombreUsuarioActualizacion) {
			this.nombreUsuarioActualizacion = nombreUsuarioActualizacion;
		   }

		   public Long getIdUsuarioActualizacion() {
			return idUsuarioActualizacion;
		   }

		   public void setIdUsuarioActualizacion(Long idUsuarioActualizacion) {
			this.idUsuarioActualizacion = idUsuarioActualizacion;
		   }

		   public String getEmailUsuarioCreacion() {
			return emailUsuarioCreacion;
		   }

		   public void setEmailUsuarioCreacion(String emailUsuarioCreacion) {
			this.emailUsuarioCreacion = emailUsuarioCreacion;
		   }

		   public String getApellidoUsuarioCreacion() {
			return apellidoUsuarioCreacion;
		   }

		   public void setApellidoUsuarioCreacion(String apellidoUsuarioCreacion) {
			this.apellidoUsuarioCreacion = apellidoUsuarioCreacion;
		   }

		   public String getNombreUsuarioCreacion() {
			return nombreUsuarioCreacion;
		   }

		   public void setNombreUsuarioCreacion(String nombreUsuarioCreacion) {
			this.nombreUsuarioCreacion = nombreUsuarioCreacion;
		   }

		   public Long getIdUsuarioCreacion() {
			return idUsuarioCreacion;
		   }

		   public void setIdUsuarioCreacion(Long idUsuarioCreacion) {
			this.idUsuarioCreacion = idUsuarioCreacion;
		   }

		   public Date getFechaActualizacionProducto() {
			return fechaActualizacionProducto;
		   }

		   public void setFechaActualizacionProducto(Date fechaActualizacionProducto) {
			this.fechaActualizacionProducto = fechaActualizacionProducto;
		   }

		   public Date getFechaCreacionProducto() {
			return fechaCreacionProducto;
		   }

		   public void setFechaCreacionProducto(Date fechaCreacionProducto) {
			this.fechaCreacionProducto = fechaCreacionProducto;
		   }

		   public String getCategoriaProducto() {
			return categoriaProducto;
		   }

		   public void setCategoriaProducto(String categoriaProducto) {
			this.categoriaProducto = categoriaProducto;
		   }

		   public Integer getStockProducto() {
			return stockProducto;
		   }

		   public void setStockProducto(Integer stockProducto) {
			this.stockProducto = stockProducto;
		   }

		   public BigDecimal getPrecioProducto() {
			return precioProducto;
		   }

		   public void setPrecioProducto(BigDecimal precioProducto) {
			this.precioProducto = precioProducto;
		   }

		   public String getDescripcionProducto() {
			return descripcionProducto;
		   }

		   public void setDescripcionProducto(String descripcionProducto) {
			this.descripcionProducto = descripcionProducto;
		   }

		   public String getNombreProducto() {
			return nombreProducto;
		   }

		   public void setNombreProducto(String nombreProducto) {
			this.nombreProducto = nombreProducto;
		   }

		   public Long getIdProducto() {
			return idProducto;
		   }

		   public void setIdProducto(Long idProducto) {
			this.idProducto = idProducto;
		   }
}
