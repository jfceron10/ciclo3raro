
package co.edu.uniandes.csw.ordendefabricacion.logic.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public abstract class _OrdenDeFabricacionDTO {

	private Integer cantidad;
	private String name;
	private Long id;
	private String estado;

	public Integer getCantidad() {
		return cantidad;
	}
 
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
 
	public void setId(Long id) {
		this.id = id;
	}
	public String getEstado() {
		return estado;
	}
 
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}