app.controller('modalController',         
        function($scope, $uibModalInstance,$sce, message, tipo) {
	
	$scope.data = {};
	
	//$scope.data.message = message;
	$scope.data.tipo = tipo;
	
	switch(tipo){
		case 'C': $scope.data.titulo = 'Confirmacion de Registro'; $scope.data.clase = 'info'; $scope.data.message = $sce.trustAsHtml('Esta seguro que desea Guardar la rendicion para el cuentadante <b>"'+message.cuentadante+'"</b> en el mes de <b>'+evalMonth(message.mes)+'</b> del año <b>'+message.anio+'</b>'); break;
		case 'S': $scope.data.titulo = 'Mensaje del Sistema'; $scope.data.message = $sce.trustAsHtml(message); $scope.data.clase = 'success'; break;
		case 'E': $scope.data.titulo = 'Error'; $scope.data.message = $sce.trustAsHtml(message); $scope.data.clase = 'danger'; break;
	}
	
	 // $scope.titulo = tipo == 'C' ? 'Coeficiente de Correlacion' : 'Error Estandar de Estimacion';
	
	  $scope.ok = function () {
	    $uibModalInstance.close('closed');
	  };

	  $scope.cancel = function () {
	    $uibModalInstance.dismiss('cancel');
	  };
	  
	   function evalMonth(month){
    	  var text = "";
    	  switch(month){
    	  	case '1': text = 'Enero';break;
    	  	case '2': text = 'Febrero';break;
    	  	case '3': text = 'Marzo';break;
    	  	case '4': text = 'Abril';break;
    	  	case '5': text = 'Mayo';break;
    	  	case '6': text = 'Junio';break;
    	  	case '7': text = 'Julio';break;
    	  	case '8': text = 'Agosto';break;
    	  	case '9': text = 'Septiembre';break;
    	  	case '10': text = 'Octubre';break;
    	  	case '11': text = 'Noviembre';break;
    	  	case '12': text = 'Diciembre';break;    	  	
    	  }
    	  return text;
      }
});