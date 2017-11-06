app.factory('consultaService',function($http,$q){
	
	var urlConsulta = '/RendicionesCGC/filtro';
	var urlBusqueda = '/RendicionesCGC/busqueda';
	var urlPdf = '/RendicionesCGC/ver/';
		
	var fac = {};
	
	fac.viewPdf = function(param){
		var data = {idRendicion: param};		
		var deferred = $q.defer();		
		$http.get(urlPdf+param,{responseType: 'arraybuffer'})
		.then(function(response){
			console.log(response);
			//var data = response.data;
			/*if(data.code = 200){
				deferred.resolve(data.message);
			}else{
				deferred.reject(data.message);
			}*/
			deferred.resolve(response.data);
		},function(error){
			deferred.reject(error);
		});
		return deferred.promise;
	}
	
	fac.getAllRendiciones = function(data){
		var values = data;
		
		var deferred = $q.defer();
		var params = {params: values};
		$http.get(urlConsulta,params)
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
	
	fac.getMeses = function(){
		var deferred = $q.defer();
		var meses = [];
	    	   meses.push({
					value: 1,
					label: 'Enero'
			   });
	    	   meses.push({
					value: 2,
					label: 'Febrero'
				});
	    	   meses.push({
					value: 3,
					label: 'Marzo'
				});
	    	   meses.push({
					value: 4,
					label: 'Abril'
				});
	    	   meses.push({
					value: 5,
					label: 'Mayo'
				});
	    	   meses.push({
					value: 6,
					label: 'Junio'
				});
	    	   meses.push({
					value: 7,
					label: 'Julio'
				});
	    	   meses.push({
					value: 8,
					label: 'Agosto'
				});
	    	   meses.push({
					value: 9,
					label: 'Septiembre'
				});
	    	   meses.push({
					value: 10,
					label: 'Octubre'
				});
	    	   meses.push({
					value: 11,
					label: 'Noviembre'
				});
	    	   meses.push({
					value: 12,
					label: 'Diciembre'
				});
	    	   
	    	   deferred.resolve(meses);
	    	   return deferred.promise;
	       }
	
	return fac;
});