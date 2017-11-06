app.controller('consultaController',         
        function($scope, consultaService, moment,  NgTableParams, $filter,$window) {
	
	  $scope.data = {};
	  $scope.meses = [];
	  $scope.anios = [];
	  $scope.rendiciones = [];	   
	  $scope.codigo = {};
      $scope.maxSizeError = false;
           
	  
      $scope.getCuentadantes = function(val) {

          longitudTexto = val.length;
          if(longitudTexto > 2)
            return consultaService.busqueda(encodeURIComponent(val))
            .then(function(response){
            	console.log(response);
                 return response.data.map(function(item){
                                                          return item;
                                          });
            }, function(data){
                alert(data);
            });
                        
      };
      
      
      $scope.viewPdf = function(value) {
    	  consultaService.viewPdf(value)
         .then(function(data){    
        	   /* var objbuilder = '';
        	    objbuilder += ('<object width="100%" height="100%"      data="data:application/pdf;base64,');
        	    objbuilder += (data);
        	    objbuilder += ('" type="application/pdf" class="internal">');
        	    objbuilder += ('<embed src="data:application/pdf;base64,');
        	    objbuilder += (data);
        	    objbuilder += ('" type="application/pdf" />');
        	    objbuilder += ('</object>');
        	    
        	    var win = window.open("","_blank","titlebar=yes");
                win.document.title = "My Title";
                win.document.write('<html><body>');
                win.document.write(objbuilder);
                win.document.write('</body></html>');*/
        	 var file = new Blob([data], { type: 'application/pdf' });
             var fileURL = URL.createObjectURL(file);

             $window.open(fileURL);
        	 
         },function(error){
        	// win.close();
        	alert(error); 
         });
    	  
      }
      
      
      $scope.evalMonth = function(month){
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
      
      $scope.downloadPdf = function(value) {
    	  consultaService.viewPdf(value)
         .then(function(data){    
        	 
        	 $window.open("/RendicionesCGC/descarga/"+ value);
         },function(error){
        	 win.close();
        	alert(error); 
         });
    	  
      }
      
      
      $scope.onSelect = function(event, value, index, elem){
    	  $scope.limpiar();
    	  $scope.codigo = event;
          $scope.data.cuentadante = event.codigoCuentadancia;
      }
      
      $scope.callAnios = function(){
    	  consultaService.getAnios()
    	  .then(function(data){
    		 $scope.anios = data; 
    	  },function(error){
    		  console.log(error);
    	  });
      }
      
      $scope.callMeses = function(){
    	  consultaService.getMeses()
    	  .then(function(data){
    		 $scope.meses = data; 
    	  });
      }
      

	  $scope.$watch('data', function(NewValue, OldValue) {		
		  $scope.listar();
      }, true);
 
      
      $scope.listar = function(){
    	  consultaService.getAllRendiciones($scope.data)
    	  .then(function(data){
    		  $scope.rendiciones = data;
    		  $scope.userTable = new NgTableParams({count:50}, {counts: [] ,dataset: $scope.rendiciones});
    	  },function(error){
    		  console.log(error);
    	  });
      }
      
});