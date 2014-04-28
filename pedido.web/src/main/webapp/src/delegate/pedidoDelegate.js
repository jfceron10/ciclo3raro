define(['delegate/_pedidoDelegate'], function() {
    App.Delegate.PedidoDelegate = {
        satisfacer: function(id,callback,callbackError){
	    console.log('satisfacer pedido: '+id);
            $.ajax({
	          url: '/pedido.service.subsystem/webresources/Pedido/'+id+'/satisfacer',
	          type: 'PUT',
	          data: {},
	          contentType: 'application/json'
	      }).done(_.bind(function(data){
	    	  callback(data);
	      },this)).error(_.bind(function(data){
	    	  callbackError(data);
	      },this));
	}
    };
});