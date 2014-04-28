
package co.edu.uniandes.csw.pedido.logic.mock;
import javax.enterprise.inject.Alternative;
import javax.inject.Singleton;

import co.edu.uniandes.csw.pedido.logic.api.IPedidoLogicService;

@Alternative
@Singleton
public class PedidoMockLogicService extends _PedidoMockLogicService implements IPedidoLogicService {

    public void satisfacerPedido(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
	
}