app.controller('roleController',         
        function($scope, roleService, moment,  NgTableParams, $filter,$window) {
	
	  $scope.data = {};
	  $scope.roles = [];	   
	  
      $scope.getCuentadantes = function(val) {

          longitudTexto = val.length;
          if(longitudTexto > 2)
            return roleService.busqueda(encodeURIComponent(val))
            .then(function(response){
            	console.log(response);
                 return response.data.map(function(item){
                                                          return item;
                                          });
            }, function(data){
                alert(data);
            });
                        
      };
      
      
      $scope.guardar = function(){
    	  roleService.guardar($scope.data)
    	  .then(function(data){
    		  console.log(data);
    		  alert(data.message);
    		 $scope.limpiar();
    		  $scope.listar();
    	  },function(error){
    		 alert("error: "+error); 
    	  });
      }
      
      $scope.editar = function(usuario){
    	
      }
 
      
      $scope.listar = function(){
    	  roleService.getAllRoles()
    	  .then(function(data){
    		  console.log(data);
    		  $scope.roles = data;
    		  $scope.userTable = new NgTableParams({count:50}, {counts: [] ,dataset: $scope.roles});
    	  },function(error){
    		  console.log(error);
    	  });
      }
      
      $scope.limpiar = function(){
    	  $scope.data = {};
    	  $scope.form.$setPristine();
      }
});