app.factory('roleService',function($http,$q){
	
	var urlListar = '/RendicionesCGC/role/listar';
	var urlRegistro = '/RendicionesCGC/role/registro';
	var urlModificar = '/RendicionesCGC/role/modificar';	
		
	var fac = {};
	
	fac.getAllRoles = function(){
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
	
	fac.modificar = function(data){		
		var deferred = $q.defer();
		$http.post(urlModificar,data)
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
	
	
	
	return fac;
});