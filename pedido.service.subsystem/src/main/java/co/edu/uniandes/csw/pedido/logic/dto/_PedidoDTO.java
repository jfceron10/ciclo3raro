
package co.edu.uniandes.csw.pedido.logic.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public abstract class _PedidoDTO {

	private Long id;
	private String cantidad;
	private String name;
	private String estado;

	public Long getId() {
		return id;
	}
 
	public void setId(Long id) {
		this.id = id;
	}
	public String getCantidad() {
		return cantidad;
	}
 
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
	public String getEstado() {
		return estado;
	}
 
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}