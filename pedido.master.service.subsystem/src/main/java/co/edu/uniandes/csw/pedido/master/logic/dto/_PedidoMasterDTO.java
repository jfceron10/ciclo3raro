package co.edu.uniandes.csw.pedido.master.logic.dto;

import co.edu.uniandes.csw.ordendefabricacion.logic.dto.OrdenDeFabricacionDTO;
import co.edu.uniandes.csw.pedido.logic.dto.PedidoDTO;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public abstract class _PedidoMasterDTO {

 
    protected PedidoDTO pedidoEntity;
    protected Long id;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public PedidoDTO getPedidoEntity() {
        return pedidoEntity;
    }

    public void setPedidoEntity(PedidoDTO pedidoEntity) {
        this.pedidoEntity = pedidoEntity;
    }
    
    public List<OrdenDeFabricacionDTO> createOrdenDeFabricacion;
    public List<OrdenDeFabricacionDTO> updateOrdenDeFabricacion;
    public List<OrdenDeFabricacionDTO> deleteOrdenDeFabricacion;
    public List<OrdenDeFabricacionDTO> listOrdenDeFabricacion;	
	
	
	
    public List<OrdenDeFabricacionDTO> getCreateOrdenDeFabricacion(){ return createOrdenDeFabricacion; };
    public void setCreateOrdenDeFabricacion(List<OrdenDeFabricacionDTO> createOrdenDeFabricacion){ this.createOrdenDeFabricacion=createOrdenDeFabricacion; };
    public List<OrdenDeFabricacionDTO> getUpdateOrdenDeFabricacion(){ return updateOrdenDeFabricacion; };
    public void setUpdateOrdenDeFabricacion(List<OrdenDeFabricacionDTO> updateOrdenDeFabricacion){ this.updateOrdenDeFabricacion=updateOrdenDeFabricacion; };
    public List<OrdenDeFabricacionDTO> getDeleteOrdenDeFabricacion(){ return deleteOrdenDeFabricacion; };
    public void setDeleteOrdenDeFabricacion(List<OrdenDeFabricacionDTO> deleteOrdenDeFabricacion){ this.deleteOrdenDeFabricacion=deleteOrdenDeFabricacion; };
    public List<OrdenDeFabricacionDTO> getListOrdenDeFabricacion(){ return listOrdenDeFabricacion; };
    public void setListOrdenDeFabricacion(List<OrdenDeFabricacionDTO> listOrdenDeFabricacion){ this.listOrdenDeFabricacion=listOrdenDeFabricacion; };	
	
	
}

