app.controller('usuarioController',         
        function($scope, usuarioService, moment,  NgTableParams, $filter,$window, $uibModal) {
	
	  $scope.data = {};
	  $scope.meses = [];
	  $scope.anios = [];
	  $scope.usuarios = [];	   
	  $scope.codigo = {};
      $scope.maxSizeError = false;
      $scope.confirm = null;
	  
      $scope.getCuentadantes = function(val) {

          longitudTexto = val.length;
          if(longitudTexto > 2)
            return usuarioService.busqueda(encodeURIComponent(val))
            .then(function(response){
            	console.log(response);
                 return response.data.map(function(item){
                                                          return item;
                                          });
            }, function(data){
                alert(data);
            });
                        
      };
      
      $scope.open = function (data) {
      	       	
    	    var modalInstance = $uibModal.open({
    	      templateUrl: 'app/views/password.html',
    	      controller: 'passwordController',
    	      size: 'md',
    	      backdrop: 'static',
    	      resolve: {
    	        data: function () {
    	          return data;
    	        }
    	      }
    	    });

    	    modalInstance.result.then(function (selectedItem) {
    	    	alert(selectedItem);
    	    }, function () {
    	    });
    	  };
      
      
      $scope.guardar = function(){
    	  usuarioService.guardar($scope.data)
    	  .then(function(data){
    		  console.log(data);
    		  alert(data.message);
    		 $scope.limpiar();
    		  $scope.listar();
    	  },function(error){
    		 alert("error: "+error); 
    	  });
      }
      
      $scope.estado = function(usuario){
    	  usuarioService.estado(usuario)
    	  .then(function(data){    		  
    		  alert(data.message);    		 
    		  $scope.listar();
    	  },function(error){
    		 alert("error: "+error); 
    	  });
      }
 
      
      $scope.listar = function(){
    	  usuarioService.getAllUsuarios()
    	  .then(function(data){
    		  console.log(data);
    		  $scope.usuarios = data;
    		  $scope.userTable = new NgTableParams({count:50}, {counts: [] ,dataset: $scope.usuarios});
    	  },function(error){
    		  console.log(error);
    	  });
      }
      
      $scope.limpiar = function(){
    	  $scope.data = {};
    	  $scope.confirm = null;
    	  $scope.form.$setPristine();
      }
});