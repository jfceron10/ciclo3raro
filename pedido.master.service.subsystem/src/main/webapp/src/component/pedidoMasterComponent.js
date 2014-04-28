define(['controller/selectionController', 'model/cacheModel', 'model/pedidoMasterModel', 'component/_CRUDComponent', 'controller/tabController', 'component/pedidoComponent',
 'component/ordenDeFabricacionComponent'
 
 ],function(SelectionController, CacheModel, PedidoMasterModel, CRUDComponent, TabController, PedidoComponent,
 OrdenDeFabricacionComponent
 ) {
    App.Component.PedidoMasterComponent = App.Component.BasicComponent.extend({
        initialize: function() {
            var self = this;
            this.configuration = App.Utils.loadComponentConfiguration('pedidoMaster');
            var uComponent = new PedidoComponent();
            uComponent.initialize();
            uComponent.render('main');
            Backbone.on(uComponent.componentId + '-post-pedido-create', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(uComponent.componentId + '-post-pedido-edit', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(uComponent.componentId + '-pre-pedido-list', function() {
                self.hideChilds();
            });
            Backbone.on('pedido-master-model-error', function(error) {
                Backbone.trigger(uComponent.componentId + '-' + 'error', {event: 'pedido-master-save', view: self, error: error});
            });
            Backbone.on(uComponent.componentId + '-instead-pedido-save', function(params) {
                self.model.set('pedidoEntity', params.model);
                if (params.model) {
                    self.model.set('id', params.model.id);
                } else {
                    self.model.unset('id');
                }
                var ordenDeFabricacionModels = self.ordenDeFabricacionComponent.componentController.ordenDeFabricacionModelList;
                self.model.set('listOrdenDeFabricacion', []);
                self.model.set('createOrdenDeFabricacion', []);
                self.model.set('updateOrdenDeFabricacion', []);
                self.model.set('deleteOrdenDeFabricacion', []);
                for (var i = 0; i < ordenDeFabricacionModels.models.length; i++) {
                    var m = ordenDeFabricacionModels.models[i];
                    var modelCopy = m.clone();
                    if (m.isCreated()) {
                        //set the id to null
                        modelCopy.unset('id');
                        self.model.get('createOrdenDeFabricacion').push(modelCopy.toJSON());
                    } else if (m.isUpdated()) {
                        self.model.get('updateOrdenDeFabricacion').push(modelCopy.toJSON());
                    }
                }
                for (var i = 0; i < ordenDeFabricacionModels.deletedModels.length; i++) {
                    var m = ordenDeFabricacionModels.deletedModels[i];
                    self.model.get('deleteOrdenDeFabricacion').push(m.toJSON());
                }
                self.model.save({}, {
                    success: function() {
                        uComponent.componentController.list();
                    },
                    error: function(error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'pedido-master-save', view: self, error: error});
                    }
                });
            });
        },
        renderChilds: function(params) {
            var self = this;
            this.tabModel = new App.Model.TabModel(
                    {
                        tabs: [
                            {label: "OrdenDeFabricacion", name: "ordenDeFabricacion", enable: true},
                        ]
                    }
            );

            this.tabs = new TabController({model: this.tabModel});

            this.tabs.render('tabs');
            App.Model.PedidoMasterModel.prototype.urlRoot = this.configuration.context;
            var options = {
                success: function() {
					self.ordenDeFabricacionComponent = new OrdenDeFabricacionComponent();
                    self.ordenDeFabricacionModels = App.Utils.convertToModel(App.Utils.createCacheModel(App.Model.OrdenDeFabricacionModel), self.model.get('listOrdenDeFabricacion'));
                    self.ordenDeFabricacionComponent.initialize({
                        modelClass: App.Utils.createCacheModel(App.Model.OrdenDeFabricacionModel),
                        listModelClass: App.Utils.createCacheList(App.Model.OrdenDeFabricacionModel, App.Model.OrdenDeFabricacionList, self.ordenDeFabricacionModels)
                    });
                    self.ordenDeFabricacionComponent.render(self.tabs.getTabHtmlId('ordenDeFabricacion'));
                    Backbone.on(self.ordenDeFabricacionComponent.componentId + '-post-ordenDeFabricacion-create', function(params) {
                        params.view.currentOrdenDeFabricacionModel.setCacheList(params.view.ordenDeFabricacionModelList);
                    });
                    self.ordenDeFabricacionToolbarModel = self.ordenDeFabricacionComponent.toolbarModel.set(App.Utils.Constans.referenceToolbarConfiguration);
                    self.ordenDeFabricacionComponent.setToolbarModel(self.ordenDeFabricacionToolbarModel);                    
                	
                     
                
                    $('#tabs').show();
                },
                error: function() {
                    Backbone.trigger(self.componentId + '-' + 'error', {event: 'pedido-edit', view: self, id: id, data: data, error: error});
                }
            };
            if (params.id) {
                self.model = new App.Model.PedidoMasterModel({id: params.id});
                self.model.fetch(options);
            } else {
                self.model = new App.Model.PedidoMasterModel();
                options.success();
            }


        },
        hideChilds: function() {
            $('#tabs').hide();
        }
    });

    return App.Component.PedidoMasterComponent;
});