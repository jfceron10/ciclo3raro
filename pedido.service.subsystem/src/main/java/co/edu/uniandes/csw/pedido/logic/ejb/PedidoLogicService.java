
package co.edu.uniandes.csw.pedido.logic.ejb;

import co.edu.uniandes.csw.pedido.logic.api.IPedidoLogicService;
import co.edu.uniandes.csw.pedido.logic.dto.PedidoDTO;
import co.edu.uniandes.csw.pedido.persistence.api.IPedidoPersistence;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless; 
import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Default
@Stateless
@LocalBean
public class PedidoLogicService extends _PedidoLogicService implements IPedidoLogicService {
    
    public void satisfacerPedido(Long id) {
        PedidoDTO indicado = getPedido(id);
        
        Date hoy = new Date ();
        indicado.setEstado(hoy.toGMTString());
        
        persistance.updatePedido(indicado);
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}