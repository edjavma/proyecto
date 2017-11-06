app.controller('passwordController',         
        function($scope, usuarioService ,$uibModalInstance, data) {
	
	$scope.info = data;
	$scope.data = {};
	
	
	  $scope.ok = function () {
		  console.log($scope.info.idUsuario);
		 if($scope.info.idUsuario != undefined){
			 $scope.data.idUsuario = $scope.info.idUsuario; 
		 } 
		  		  
		usuarioService.cambioPass($scope.data)
		.then(function(data){
			$uibModalInstance.close(data);
		},function(error){
			alert("error: "+error);
		});
	    
	  };

	  $scope.cancel = function () {
	    $uibModalInstance.dismiss('cancel');
	  };
	  
});