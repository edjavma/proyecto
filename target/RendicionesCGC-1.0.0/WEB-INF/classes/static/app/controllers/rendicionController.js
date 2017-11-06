app.controller('rendicionController',         
        function($scope, rendicionService, moment,  NgTableParams, $filter,$window,  $uibModal) {
	
	  $scope.data = {};
	  $scope.meses = [];
	  $scope.anios = [];
	  $scope.rendiciones = [];	   
	  $scope.codigo = {};
      $scope.maxSizeError = false;
	  
      $scope.getCuentadantes = function(val) {

          longitudTexto = val.length;
          if(longitudTexto > 2)
            return rendicionService.busqueda(encodeURIComponent(val))
            .then(function(response){
                 return response.data.map(function(item){
                                                          return item;
                                          });
            }, function(data){
                alert(data);
            });
                        
      };
      
      $scope.open = function (tipo,mensaje) {
   	   
    	if(tipo == 'C'){
    		mensaje = $scope.data;
    	} 
    	
  	    var modalInstance = $uibModal.open({
  	      templateUrl: 'app/views/modal.html',
  	      controller: 'modalController',
  	      size: 'sm',
  	      backdrop: 'static',
  	      resolve: {
  	        message: function () {
  	          return mensaje;
  	        },
  	        tipo: function () {
  	        	return tipo;
  	        }
  	      }
  	    });

  	    modalInstance.result.then(function (selectedItem) {
  	      $scope.guardar();
  	    }, function () {
  	    });
  	  };
      
      
      $scope.viewPdf = function(value) {
         rendicionService.viewPdf(value)
         .then(function(data){    
        	    /*var objbuilder = '';
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
        	 //win.close();
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
         rendicionService.viewPdf(value)
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
    	  rendicionService.getAnios()
    	  .then(function(data){
    		 $scope.anios = data; 
    	  },function(error){
    		  console.log(error);
    	  });
      }
      
      $scope.fileChange = function(file){
       var maxSize = 5 * 1024 * 1024;
       var fileSize = file[0].size;
   	   $scope.data.file = file[0];
		   	if(fileSize>maxSize){
		        $scope.maxSizeError = true;
		    }else{
		    	$scope.maxSizeError = false;
		    }
		   	
		   	$scope.$apply();
      }
      
      $scope.listar = function(){
    	  rendicionService.getAllRendiciones()
    	  .then(function(data){
    		  $scope.rendiciones = data;
    		  $scope.userTable = new NgTableParams({count:50}, {counts: [] ,dataset: $scope.rendiciones});
    	  },function(error){
    		  console.log(error);
    	  });
      }
      
      
      $scope.guardar = function(){
   	   if($scope.data != null && $scope.data != undefined && $scope.data.file != undefined && $scope.data.file != null){
   		   
   		  	var fd = new FormData();
   	        //Take the first selected file
   	        fd.append("file", $scope.data.file, $scope.data.file.name);	   
   	        fd.append("mes", $scope.data.mes);
   	        fd.append("anio", $scope.data.anio);
   	        fd.append("cuentadante", $scope.data.cuentadante);
   	        
   	        
   	     rendicionService.guardar(fd)
   		   .then(function(data){
   			   //alert(data);
   			   $scope.open('S',data);
   			   $scope.listar();
   			   $scope.codigo = {};
   			   $scope.data = {};
   			   $('#tipeahead').val('');
   			   $('#myFile').val('');
   		   },function(error){
   			   $scope.codigo = {};
			   $scope.data = {};
			   $('#tipeahead').val('');
			   $('#myFile').val('');
   			  // $scope.values = [];   
   			   $scope.open('E',error);
   		   });
   	   }else{
			   $scope.open('E','Debe completar todos los campos');
   	   }
      }
      
      $scope.$watch('data.anio', function(NewValue, OldValue) {		
		  var date = new Date();
		  $scope.data.mes = undefined;
		  if(date.getFullYear() == NewValue){
			  var mesActual = date.getMonth()
			  if(date.getMonth == 0 || date.getMonth == 11){
				  mesActual = date.getMonth() + 1;
			  }
			  
			  rendicionService.getMeses(mesActual)
			  .then(function(data){
				  $scope.meses = data;
			  });			  
		  }else{
			  rendicionService.getMeses(1)
			  .then(function(data){
				  $scope.meses = data;
			  });
		  }
      }, true);
      
      $scope.limpiar = function(){
    	  $scope.data = {};
    	  $('#myFile').val('');
    	  $scope.maxSizeError = false;
      }
});