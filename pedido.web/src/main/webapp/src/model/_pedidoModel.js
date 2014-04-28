define([], function() {
    App.Model._PedidoModel = Backbone.Model.extend({
        defaults: {
 
		 'cantidad' : ''
 ,  
		 'name' : ''
 ,  
		 'estado' : ''
        },
        initialize: function() {
        },
        getDisplay: function(name) {
         return this.get(name);
        }
    });

    App.Model._PedidoList = Backbone.Collection.extend({
        model: App.Model._PedidoModel,
        initialize: function() {
        }

    });
    return App.Model._PedidoModel;
});