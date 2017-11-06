app.factory('asignService',function($http,$q){
	
	var urlListar = '/RendicionesCGC/usuario/listar';
	var urlRegistro = '/RendicionesCGC/asignar/guardar';
//	var urlRoles = '/RendicionesCGC/asignar/listar';
	var urlRoles = '/RendicionesCGC/role/usuario';
	var urlRolesUser = '/RendicionesCGC/asignar/listar';
	var urlActivar = '/RendicionesCGC/asignar/estado';
		
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
	
	fac.getAllRolesByUser = function(data){
		var deferred = $q.defer();
		$http.get(urlRolesUser,{params:{usuario:data}})		
		.then(function(response){
			deferred.resolve(response.data);
		},function(error){
			deferred.reject(error);
		});
		return deferred.promise;
	}
	
	fac.getAllRoles = function(data){
		var deferred = $q.defer();
		$http.get(urlRoles,{params:{usuario:data}})
		//$http.get(urlRoles)
		.then(function(response){
			deferred.resolve(response.data);
		},function(error){
			deferred.reject(error);
		});
		return deferred.promise;
	}
	
	fac.guardar = function(data){
		console.log(data);
		var deferred = $q.defer();
		$http.post(urlRegistro+"?usuario="+data.usuario+"&role="+data.role,data)
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
		$http.post(urlActivar+"?usuarioRole="+data,data)
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