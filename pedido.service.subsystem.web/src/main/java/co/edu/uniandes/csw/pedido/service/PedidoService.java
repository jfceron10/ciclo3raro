package co.edu.uniandes.csw.pedido.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/Pedido")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PedidoService extends _PedidoService {
    
    @PUT
    @Path("{id}/satisfacer")
    public void satisfacer(@PathParam("id") Long id) {
        
        pedidoLogicService.satisfacerPedido(id);
    }


}