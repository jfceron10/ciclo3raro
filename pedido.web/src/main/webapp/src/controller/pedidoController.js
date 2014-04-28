define(['controller/_pedidoController','delegate/pedidoDelegate'], function() {
    App.Controller.PedidoController = App.Controller._PedidoController.extend({

        postInit: function(options){
           var self=this;
            this.selection=new App.Controller.SelectionController({});
           this.selectionTemplate = _.template($('#pedidoSelectionList').html());
        
            Backbone.on('satisfacer-pedido',function(params){
                self.resetPassword(params);
            });
        },
 
        satisfacer: function(params){
            console.log('satisfacer pedido' + params.id);
            var self=this;
            App.Delegate.pedidoDelegate.satisfacer(params.id,function(data){
                alert('Satisfecho');
            },function(data){
                Backbone.trigger(self.componentId + '-' + 'error', {event: 'satisfacer-pedido', view: self, id: params.id, data: data, error: 'Error in satisfacer-pedido'});
            });
        }
    });
    return App.Controller.PedidoController;
}); 