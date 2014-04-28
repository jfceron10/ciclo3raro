package co.edu.uniandes.csw.pedido.master.persistence.api;

import co.edu.uniandes.csw.pedido.master.persistence.entity.PedidoOrdenDeFabricacionEntity;
import co.edu.uniandes.csw.ordendefabricacion.logic.dto.OrdenDeFabricacionDTO;
import co.edu.uniandes.csw.pedido.master.logic.dto.PedidoMasterDTO;
import java.util.List;

public interface _IPedidoMasterPersistence {

    public PedidoOrdenDeFabricacionEntity createPedidoOrdenDeFabricacion(PedidoOrdenDeFabricacionEntity entity);

    public void deletePedidoOrdenDeFabricacion(Long pedidoId, Long ordenDeFabricacionId);
    
    public List<OrdenDeFabricacionDTO> getOrdenDeFabricacionListForPedido(Long pedidoId);
    
    public PedidoMasterDTO getPedido(Long pedidoId);

}
