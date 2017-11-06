app.factory('rendicionService',function($http,$q){
	
	var urlPOST = '/RendicionesCGC/guardar';
	var urlConsulta = '/RendicionesCGC/listar';
	var urlBusqueda = '/RendicionesCGC/busqueda';
	var urlPdf = '/RendicionesCGC/ver/';
		
	var fac = {};
	
	fac.viewPdf = function(param){
		var data = {idRendicion: param};		
		var deferred = $q.defer();		
		$http.get(urlPdf+param,{responseType: 'arraybuffer'})
		.then(function(response){
			/*var data = response.data;
			if(data.code = 200){
				deferred.resolve(data.message);
			}else{
				deferred.reject(data.message);
			}	*/
			deferred.resolve(response.data);
		},function(error){
			deferred.reject(error);
		});
		return deferred.promise;
	}
	
	fac.guardar = function(data){
	
		var deferred = $q.defer();
	    var config = { headers : {'Content-Type' : undefined}};
		$http.post(urlPOST,data, config)
		.then(function(response){
			var data = response.data;
			if(data.code == 200){
				deferred.resolve(data.message);
			}else{
				deferred.reject(data.message);
			}						
		},function(error){
			deferred.reject(error);
		});
		return deferred.promise;
	}
	
	fac.getAllRendiciones = function(){
		var deferred = $q.defer();
		$http.get(urlConsulta)
		.then(function(response){
			deferred.resolve(response.data);
		},function(error){
			deferred.reject(error);
		});
		return deferred.promise;
	}
	
	fac.busqueda = function(texto){
		var deferred = $q.defer();
		$http.get(urlBusqueda, {params: {texto:texto}})
		.then(function(response){
			deferred.resolve(response);
		},function(error){
			deferred.reject(error);
		});
		return deferred.promise;
	}
	
	fac.getAnios = function(){
		var deferred = $q.defer();
		var anios = [];
		var date = new Date();
		var actualY = date.getFullYear();
		
		anios.push(actualY);
		for(var i=1;i<4;i++){
			anios.push(actualY + i);
		}
		deferred.resolve(anios);
		return deferred.promise;
	}
	
	fac.getMeses = function(val){
		var deferred = $q.defer();
		var meses = [];
		for(var i=val;i<=12;i++){
			switch(i){
				case 1:
					meses.push({
						value: 1,
						label: 'Enero'
				   });
					break;
				case 2:
					meses.push({
						value: 2,
						label: 'Febrero'
					});
					break;
				case 3:
					 meses.push({
							value: 3,
							label: 'Marzo'
						});
					 break;
				case 4:
					meses.push({
						value: 4,
						label: 'Abril'
					});
					break;
				case 5:
					 meses.push({
							value: 5,
							label: 'Mayo'
						});
					 break;
				case 6:
					meses.push({
						value: 6,
						label: 'Junio'
					});
					break;
				case 7:
					meses.push({
						value: 7,
						label: 'Julio'
					});
					break;
				case 8: 
					 meses.push({
							value: 8,
							label: 'Agosto'
						});
					 break;
				case 9:
			    	   meses.push({
							value: 9,
							label: 'Septiembre'
						});
			    	   break;
				case 10:
					  meses.push({
							value: 10,
							label: 'Octubre'
						});
					break;
				case 11:
					 meses.push({
							value: 11,
							label: 'Noviembre'
						});
					break;
				case 12:
					   meses.push({
							value: 12,
							label: 'Diciembre'
						});
					break;
			}
		}
	    	  
	    	   
	    	  
	    	   
	    	   
	    	
	    	 
	    	  
	    	
	    	   
	    	   deferred.resolve(meses);
	    	   return deferred.promise;
	       }
	
	return fac;
});
