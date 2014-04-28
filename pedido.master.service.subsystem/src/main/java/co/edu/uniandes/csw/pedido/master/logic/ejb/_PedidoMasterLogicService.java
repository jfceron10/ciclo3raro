package co.edu.uniandes.csw.pedido.master.logic.ejb;

import co.edu.uniandes.csw.ordendefabricacion.logic.dto.OrdenDeFabricacionDTO;
import co.edu.uniandes.csw.ordendefabricacion.persistence.api.IOrdenDeFabricacionPersistence;
import co.edu.uniandes.csw.pedido.logic.dto.PedidoDTO;
import co.edu.uniandes.csw.pedido.master.logic.api._IPedidoMasterLogicService;
import co.edu.uniandes.csw.pedido.master.logic.dto.PedidoMasterDTO;
import co.edu.uniandes.csw.pedido.master.persistence.api.IPedidoMasterPersistence;
import co.edu.uniandes.csw.pedido.master.persistence.entity.PedidoOrdenDeFabricacionEntity;
import co.edu.uniandes.csw.pedido.persistence.api.IPedidoPersistence;
import javax.inject.Inject;

public abstract class _PedidoMasterLogicService implements _IPedidoMasterLogicService {

    @Inject
    protected IPedidoPersistence pedidoPersistance;
    @Inject
    protected IPedidoMasterPersistence pedidoMasterPersistance;
    @Inject
    protected IOrdenDeFabricacionPersistence ordenDeFabricacionPersistance;

    public PedidoMasterDTO createMasterPedido(PedidoMasterDTO pedido) {
        PedidoDTO persistedPedidoDTO = pedidoPersistance.createPedido(pedido.getPedidoEntity());
        if (pedido.getCreateOrdenDeFabricacion() != null) {
            for (OrdenDeFabricacionDTO ordenDeFabricacionDTO : pedido.getCreateOrdenDeFabricacion()) {
                OrdenDeFabricacionDTO persistedOrdenDeFabricacionDTO = ordenDeFabricacionPersistance.createOrdenDeFabricacion(ordenDeFabricacionDTO);
                PedidoOrdenDeFabricacionEntity pedidoOrdenDeFabricacionEntity = new PedidoOrdenDeFabricacionEntity(persistedPedidoDTO.getId(), persistedOrdenDeFabricacionDTO.getId());
                pedidoMasterPersistance.createPedidoOrdenDeFabricacion(pedidoOrdenDeFabricacionEntity);
            }
        }
        return pedido;
    }

    public PedidoMasterDTO getMasterPedido(Long id) {
        return pedidoMasterPersistance.getPedido(id);
    }

    public void deleteMasterPedido(Long id) {
        pedidoPersistance.deletePedido(id);
    }

    public void updateMasterPedido(PedidoMasterDTO pedido) {
        pedidoPersistance.updatePedido(pedido.getPedidoEntity());

        //---- FOR RELATIONSHIP
        // persist new ordenDeFabricacion
        if (pedido.getCreateOrdenDeFabricacion() != null) {
            for (OrdenDeFabricacionDTO ordenDeFabricacionDTO : pedido.getCreateOrdenDeFabricacion()) {
                OrdenDeFabricacionDTO persistedOrdenDeFabricacionDTO = ordenDeFabricacionPersistance.createOrdenDeFabricacion(ordenDeFabricacionDTO);
                PedidoOrdenDeFabricacionEntity pedidoOrdenDeFabricacionEntity = new PedidoOrdenDeFabricacionEntity(pedido.getPedidoEntity().getId(), persistedOrdenDeFabricacionDTO.getId());
                pedidoMasterPersistance.createPedidoOrdenDeFabricacion(pedidoOrdenDeFabricacionEntity);
            }
        }
        // update ordenDeFabricacion
        if (pedido.getUpdateOrdenDeFabricacion() != null) {
            for (OrdenDeFabricacionDTO ordenDeFabricacionDTO : pedido.getUpdateOrdenDeFabricacion()) {
                ordenDeFabricacionPersistance.updateOrdenDeFabricacion(ordenDeFabricacionDTO);
            }
        }
        // delete ordenDeFabricacion
        if (pedido.getDeleteOrdenDeFabricacion() != null) {
            for (OrdenDeFabricacionDTO ordenDeFabricacionDTO : pedido.getDeleteOrdenDeFabricacion()) {
                pedidoMasterPersistance.deletePedidoOrdenDeFabricacion(pedido.getPedidoEntity().getId(), ordenDeFabricacionDTO.getId());
                ordenDeFabricacionPersistance.deleteOrdenDeFabricacion(ordenDeFabricacionDTO.getId());
            }
        }
    }
}
