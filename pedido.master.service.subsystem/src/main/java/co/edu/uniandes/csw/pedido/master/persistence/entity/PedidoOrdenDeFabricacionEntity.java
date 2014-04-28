package co.edu.uniandes.csw.pedido.master.persistence.entity;

import co.edu.uniandes.csw.ordendefabricacion.persistence.entity.OrdenDeFabricacionEntity;
import co.edu.uniandes.csw.pedido.persistence.entity.PedidoEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn; 
import org.eclipse.persistence.annotations.JoinFetch;

@Entity
@IdClass(PedidoOrdenDeFabricacionEntityId.class)
@NamedQueries({
    @NamedQuery(name = "PedidoOrdenDeFabricacionEntity.getOrdenDeFabricacionListForPedido", query = "SELECT  u FROM PedidoOrdenDeFabricacionEntity u WHERE u.pedidoId=:pedidoId"),
    @NamedQuery(name = "PedidoOrdenDeFabricacionEntity.deletePedidoOrdenDeFabricacion", query = "DELETE FROM PedidoOrdenDeFabricacionEntity u WHERE u.ordenDeFabricacionId=:ordenDeFabricacionId and  u.pedidoId=:pedidoId")
})
public class PedidoOrdenDeFabricacionEntity implements Serializable {

    @Id
    @Column(name = "pedidoId")
    private Long pedidoId;
    @Id
    @Column(name = "ordenDeFabricacionId")
    private Long ordenDeFabricacionId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "ordenDeFabricacionId", referencedColumnName = "id")
    @JoinFetch
    private OrdenDeFabricacionEntity ordenDeFabricacionEntity;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "pedidoId", referencedColumnName = "id")
    @JoinFetch
    private PedidoEntity pedidoEntity;

    public PedidoOrdenDeFabricacionEntity() {
    }

    public PedidoOrdenDeFabricacionEntity(Long pedidoId, Long ordenDeFabricacionId) {
        this.pedidoId = pedidoId;
        this.ordenDeFabricacionId = ordenDeFabricacionId;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Long getOrdenDeFabricacionId() {
        return ordenDeFabricacionId;
    }

    public void setOrdenDeFabricacionId(Long ordenDeFabricacionId) {
        this.ordenDeFabricacionId = ordenDeFabricacionId;
    }

    public OrdenDeFabricacionEntity getOrdenDeFabricacionEntity() {
        return ordenDeFabricacionEntity;
    }

    public void setOrdenDeFabricacionEntity(OrdenDeFabricacionEntity ordenDeFabricacionEntity) {
        this.ordenDeFabricacionEntity = ordenDeFabricacionEntity;
    }

    public PedidoEntity getPedidoEntity() {
        return pedidoEntity;
    }

    public void setPedidoEntity(PedidoEntity pedidoEntity) {
        this.pedidoEntity = pedidoEntity;
    }

}
