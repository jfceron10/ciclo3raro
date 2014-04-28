package co.edu.uniandes.csw.pedido.master.persistence;
import co.edu.uniandes.csw.ordendefabricacion.logic.dto.OrdenDeFabricacionDTO;
import co.edu.uniandes.csw.pedido.master.persistence.entity.PedidoOrdenDeFabricacionEntity;
import co.edu.uniandes.csw.ordendefabricacion.persistence.converter.OrdenDeFabricacionConverter;
import co.edu.uniandes.csw.pedido.logic.dto.PedidoDTO;
import co.edu.uniandes.csw.pedido.master.logic.dto.PedidoMasterDTO;
import co.edu.uniandes.csw.pedido.master.persistence.api._IPedidoMasterPersistence;
import co.edu.uniandes.csw.pedido.persistence.api.IPedidoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class _PedidoMasterPersistence implements _IPedidoMasterPersistence {

    @PersistenceContext(unitName = "PedidoMasterPU")
    protected EntityManager entityManager;
    
    @Inject
    protected IPedidoPersistence pedidoPersistence;  

    public PedidoMasterDTO getPedido(Long pedidoId) {
        PedidoMasterDTO pedidoMasterDTO = new PedidoMasterDTO();
        PedidoDTO pedido = pedidoPersistence.getPedido(pedidoId);
        pedidoMasterDTO.setPedidoEntity(pedido);
        pedidoMasterDTO.setListOrdenDeFabricacion(getOrdenDeFabricacionListForPedido(pedidoId));
        return pedidoMasterDTO;
    }

    public PedidoOrdenDeFabricacionEntity createPedidoOrdenDeFabricacion(PedidoOrdenDeFabricacionEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    public void deletePedidoOrdenDeFabricacion(Long pedidoId, Long ordenDeFabricacionId) {
        Query q = entityManager.createNamedQuery("PedidoOrdenDeFabricacionEntity.deletePedidoOrdenDeFabricacion");
        q.setParameter("pedidoId", pedidoId);
        q.setParameter("ordenDeFabricacionId", ordenDeFabricacionId);
        q.executeUpdate();
    }

    public List<OrdenDeFabricacionDTO> getOrdenDeFabricacionListForPedido(Long pedidoId) {
        ArrayList<OrdenDeFabricacionDTO> resp = new ArrayList<OrdenDeFabricacionDTO>();
        Query q = entityManager.createNamedQuery("PedidoOrdenDeFabricacionEntity.getOrdenDeFabricacionListForPedido");
        q.setParameter("pedidoId", pedidoId);
        List<PedidoOrdenDeFabricacionEntity> qResult =  q.getResultList();
        for (PedidoOrdenDeFabricacionEntity pedidoOrdenDeFabricacionEntity : qResult) { 
            if(pedidoOrdenDeFabricacionEntity.getOrdenDeFabricacionEntity()==null){
                entityManager.refresh(pedidoOrdenDeFabricacionEntity);
            }
            resp.add(OrdenDeFabricacionConverter.entity2PersistenceDTO(pedidoOrdenDeFabricacionEntity.getOrdenDeFabricacionEntity()));
        }
        return resp;
    }

}
