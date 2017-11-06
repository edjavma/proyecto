app.controller('asignController',         
        function($scope, asignService, moment,  NgTableParams, $filter,$window) {
	
	  $scope.data = {};
	  $scope.usuarios = [];	 
	  $scope.roles = [];
	//  $scope.toptions = new Array();
	  $scope.tselected = new Array();
	  
      $scope.getCuentadantes = function(val) {

          longitudTexto = val.length;
          if(longitudTexto > 2)
            return asignService.busqueda(encodeURIComponent(val))
            .then(function(response){
                 return response.data.map(function(item){
                                                          return item;
                                          });
            }, function(data){
                alert(data);
            });
                        
      };
      
      $scope.$watch('data.usuario', function(NewValue, OldValue) {
    	  $scope.callRoles();
    	  $scope.listarRoles();
      }, true);
      
      
      $scope.guardar = function(){
    	  asignService.guardar($scope.data)
    	  .then(function(data){
    		  alert(data.message);
    		  $scope.data.role = undefined;
    		  $scope.callRoles();
    		  $scope.listarRoles();
    	  },function(error){
    		 alert("error: "+error); 
    	  });
      }
      
      
      $scope.listarUsuarios = function(){
    	  asignService.getAllUsuarios()
    	  .then(function(data){
    		  $scope.usuarios = data;
    	  },function(error){
    		  console.log(error);
    	  });
      }
      
      $scope.listarRoles = function(){
    	  asignService.getAllRolesByUser($scope.data.usuario)
    	  .then(function(data){
    		 // $scope.toptions = data.source;
    		  $scope.tselected = data;
    		  $scope.userTable = new NgTableParams({count:10}, {counts: [] ,dataset:  $scope.tselected});
    		  //$scope.roles = data;
    	  },function(error){
    		  console.log(error);
    	  });
      }
      
      $scope.estado = function(usuario){
    	  asignService.estado(usuario)
    	  .then(function(data){    		  
    		  alert(data.message);    		 
    		  $scope.callRoles();
    		  $scope.listarRoles();
    	  },function(error){
    		 alert("error: "+error); 
    	  });
      }
      
      $scope.callRoles = function(){
    	  asignService.getAllRoles($scope.data.usuario)
    	  .then(function(data){
    		  $scope.roles = data;
    	  },function(error){
    		  console.log(error);
    	  });
      }
      
      
     
});