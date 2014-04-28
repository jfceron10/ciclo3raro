define([], function() {
    App.Model._OrdenDeFabricacionModel = Backbone.Model.extend({
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

    App.Model._OrdenDeFabricacionList = Backbone.Collection.extend({
        model: App.Model._OrdenDeFabricacionModel,
        initialize: function() {
        }

    });
    return App.Model._OrdenDeFabricacionModel;
});