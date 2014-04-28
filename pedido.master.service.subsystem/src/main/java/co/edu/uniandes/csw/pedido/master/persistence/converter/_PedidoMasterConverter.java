package co.edu.uniandes.csw.pedido.master.persistence.converter;
import co.edu.uniandes.csw.pedido.master.persistence.entity.PedidoOrdenDeFabricacionEntity;
import co.edu.uniandes.csw.ordendefabricacion.logic.dto.OrdenDeFabricacionDTO;
import co.edu.uniandes.csw.ordendefabricacion.persistence.converter.OrdenDeFabricacionConverter;
import co.edu.uniandes.csw.pedido.logic.dto.PedidoDTO;
import co.edu.uniandes.csw.pedido.master.logic.dto.PedidoMasterDTO;
import co.edu.uniandes.csw.pedido.persistence.converter.PedidoConverter;
import co.edu.uniandes.csw.pedido.persistence.entity.PedidoEntity;
import java.util.ArrayList;
import java.util.List;

public abstract class _PedidoMasterConverter {

    public static PedidoMasterDTO entity2PersistenceDTO(PedidoEntity pedidoEntity 
    ,List<PedidoOrdenDeFabricacionEntity> pedidoOrdenDeFabricacionEntity 
    ) {
        PedidoDTO pedidoDTO = PedidoConverter.entity2PersistenceDTO(pedidoEntity);
        ArrayList<OrdenDeFabricacionDTO> ordenDeFabricacionEntities = new ArrayList<OrdenDeFabricacionDTO>(pedidoOrdenDeFabricacionEntity.size());
        for (PedidoOrdenDeFabricacionEntity pedidoOrdenDeFabricacion : pedidoOrdenDeFabricacionEntity) {
            ordenDeFabricacionEntities.add(OrdenDeFabricacionConverter.entity2PersistenceDTO(pedidoOrdenDeFabricacion.getOrdenDeFabricacionEntity()));
        }
        PedidoMasterDTO respDTO  = new PedidoMasterDTO();
        respDTO.setPedidoEntity(pedidoDTO);
        respDTO.setListOrdenDeFabricacion(ordenDeFabricacionEntities);
        return respDTO;
    }

}