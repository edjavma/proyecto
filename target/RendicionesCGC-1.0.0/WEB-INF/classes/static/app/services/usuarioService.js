app.factory('usuarioService',function($http,$q){
	
	var urlListar = '/RendicionesCGC/usuario/listar';
	var urlRegistro = '/RendicionesCGC/usuario/registro';
	var urlModificar = '/RendicionesCGC/usuario/modificar';
	var urlAnular = '/RendicionesCGC/usuario/anular';
	var urlCambio = '/RendicionesCGC/password/cambio';
		
	var fac = {};
	
	fac.getAllUsuarios = function(){
		var deferred = $q.defer();
		$http.get(urlListar)
		.then(function(response){
			deferred.resolve(response.data);
		},function(error){
			deferred.reject(error);
		});
		return deferred.promise;
	}
	
	fac.guardar = function(data){
		var deferred = $q.defer();
		$http.post(urlRegistro,data)
		.then(function(response){
			if(response.data.code == 200){
				deferred.resolve(response.data);
			}else{
				deferred.reject(response.data.message)
			}			
		},function(error){
			deferred.reject(error);
		});
		return deferred.promise;
	}
	
	fac.estado = function(data){		
		var deferred = $q.defer();
		$http.post(urlAnular,data)
		.then(function(response){
			if(response.data.code == 200){
				deferred.resolve(response.data);
			}else{
				deferred.reject(response.data.message)
			}			
		},function(error){
			deferred.reject(error);
		});
		return deferred.promise;
	}
	
	fac.cambioPass = function(data){
		console.log(data);
		var deferred = $q.defer();
		$http.post(urlCambio,data)
		.then(function(response){
			if(response.data.code == 200){
				deferred.resolve(response.data.message);
			}else{
				deferred.reject(response.data.message)
			}			
		},function(error){
			deferred.reject(error);
		});
		return deferred.promise;
	}
	
	return fac;
});