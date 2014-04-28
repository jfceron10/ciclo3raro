package co.edu.uniandes.csw.pedido.master.persistence.entity;

import java.io.Serializable;

/**
 *
 * @author cadmilo
 */
public class PedidoOrdenDeFabricacionEntityId implements Serializable{

    private Long pedidoId;
    private Long ordenDeFabricacionId;

    @Override
    public int hashCode() {
        return (int) (pedidoId + ordenDeFabricacionId);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof PedidoOrdenDeFabricacionEntityId) {
            PedidoOrdenDeFabricacionEntityId otherId = (PedidoOrdenDeFabricacionEntityId) object;
            return (otherId.pedidoId == this.pedidoId) && (otherId.ordenDeFabricacionId == this.ordenDeFabricacionId);
        }
        return false;
    }

}
