app.controller('modeloController',         
        function($scope, usuarioService, $uibModal) {
	
	$scope.data = {};
	$scope.confirm = "";
	
	 $scope.ok = function () {		  		  
		usuarioService.cambioPass($scope.data)
		.then(function(data){
			alert(data);
			$scope.limpiar();
		},function(error){
			alert("error: "+error);
			$scope.limpiar();
		});
	    
	  };
     
     
	  $scope.limpiar = function(){
		$scope.data = {};
		$scope.confirm = "";
		 $scope.form.$setPristine();
	  };
	  
});