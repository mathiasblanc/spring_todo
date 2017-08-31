'use strict';
 
angular.module('crudApp').factory('TodoService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {
 
            var factory = {
                loadAllTodos: loadAllTodos,
                getAllTodos: getAllTodos,
                loadAllDoneTodos: loadAllDoneTodos,
                getAllDoneTodos: getAllDoneTodos,
                getTodo: getTodo,
                createTodo: createTodo,
                deleteTodo: deleteTodo,
                setDone : setDone
            };
 
            return factory;
 
            function loadAllTodos() {
                console.log('Fetching all todos');
                var deferred = $q.defer();
                
                $http.get(urls.SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all todos');
                            $localStorage.todos = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading todos');
                            deferred.reject(errResponse);
                        }
                    );
                
                return deferred.promise;
            }
            
            function loadAllDoneTodos() {
                console.log('Fetching all done todos');
                var deferred = $q.defer();
                
                $http.get(urls.SERVICE_API + 'done')
                    .then(
                        function (response) {
                            console.log('Fetched successfully all done todos');
                            $localStorage.doneTodos = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading todos');
                            deferred.reject(errResponse);
                        }
                    );
                
                return deferred.promise;
            }
 
            function getAllTodos(){
                return $localStorage.todos;
            }
            
            function getAllDoneTodos(){
            	return $localStorage.doneTodos;
            }
 
            function getTodo(id) {
                console.log('Fetching todo with id :'+id);
                var deferred = $q.defer();
               
                $http.get(urls.SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully todo with id :' + id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading todo with id :' + id);
                            deferred.reject(errResponse);
                        }
                    );
                
                return deferred.promise;
            }
            
            function createTodo(todo){
            	var deferred = $q.defer();
            	
            	$http.post(urls.SERVICE_API, todo)
            		.then(function(response){
            			loadAllTodos();
            			deferred.resolve(response.data);
            		},
            		function(errResponse){
            			deferred.reject(errResponse);
            	});
            	
            	return deferred.promise;
            }
            
            function setDone(id){
            	console.log('Updating todo with id ' + id);
            	var deferred = $q.defer();
            	
            	$http.put(urls.SERVICE_API + id).then(function (response) {
            		loadAllTodos();
            		loadAllDoneTodos();
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                	console.error('Error while updating todo with id :' + id);
                    deferred.reject(errResponse);
                });
            	
            	return deferred.promise;
            }
            	
            	
 
            function deleteTodo(id) {
                console.log('Removing todo with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllTodos();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing todo with id :' + id);
                            deferred.reject(errResponse);
                        }
                    );
                
                return deferred.promise;
            }
 
        }
    ]);