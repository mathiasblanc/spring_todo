var app = angular.module('crudApp', [ 'ui.router', 'ngStorage' ]);

app.constant('urls', {
	BASE : 'http://localhost:8080/todo/',
	SERVICE_API : 'http://localhost:8080/todo/api/todo/'
});

app.config([
		'$stateProvider',
		'$urlRouterProvider',
		function($stateProvider, $urlRouterProvider) {

			$stateProvider.state('home', {
				url : '/',
				templateUrl : 'partials/todo',
				controller : 'TodoController',
				controllerAs : 'ctrl',
				resolve : {
					todos : function($q, TodoService) {
						console.log('Load all todos');

						var deferred = $q.defer();

						TodoService.loadAllTodos().then(deferred.resolve,
								deferred.resolve);
						
						TodoService.loadAllDoneTodos().then(deferred.resolve,
								deferred.resolve);
						return deferred.promise;
					}
				}
			});

			$urlRouterProvider.otherwise('/');

		} ]);