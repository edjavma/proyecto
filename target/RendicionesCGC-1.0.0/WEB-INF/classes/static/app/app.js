var app = angular.module("modelApp",['ngRoute', 'ui.bootstrap','mgcrea.ngStrap.datepicker','angularMoment','ngTable','fxpicklist']);

app.config(function ($routeProvider,  $datepickerProvider) {
    $routeProvider        
        .when('/', {
            templateUrl: 'app/views/modelo.html'
           // controller: 'modeloController'
        })
        .when('/ingreso', {
            templateUrl: 'app/views/rendicion.html',
            controller: 'rendicionController'
        })
        .when('/consulta', {
            templateUrl: 'app/views/consulta.html',
            controller: 'consultaController'
        })
        .when('/usuario', {
            templateUrl: 'app/views/usuario.html',
            controller: 'usuarioController'
        })
        .when('/role', {
            templateUrl: 'app/views/role.html',
            controller: 'roleController'
        })
        .when('/asignar', {
            templateUrl: 'app/views/asignacion.html',
            controller: 'asignController'
        })
        .when('/pass', {
        	templateUrl: 'app/views/passwordUser.html',
            controller: 'modeloController'
        })
        .otherwise({redirectTo:'/'});
    
    angular.extend($datepickerProvider.defaults, {
        dateFormat: 'dd/MM/yyyy',
        startWeek: 1,
      //  startView: 1,  
        hasToday: true,
       // startDate: 'today',
     //   daysOfWeekDisabled: '0,6'
    });
});

app.directive("passwordVerify", function() {
	   return {
	      require: "ngModel",
	      scope: {
	        passwordVerify: '='
	      },
	      link: function(scope, element, attrs, ctrl) {
	        scope.$watch(function() {
	            var combined;

	            if (scope.passwordVerify || ctrl.$viewValue) {
	               combined = scope.passwordVerify + '_' + ctrl.$viewValue; 
	            }                    
	            return combined;
	        }, function(value) {
	            if (value) {
	                ctrl.$parsers.unshift(function(viewValue) {
	                    var origin = scope.passwordVerify;
	                    if (origin !== viewValue) {
	                        ctrl.$setValidity("passwordVerify", false);
	                        return undefined;
	                    } else {
	                        ctrl.$setValidity("passwordVerify", true);
	                        return viewValue;
	                    }
	                });
	            }
	        });
	     }
	   };
});