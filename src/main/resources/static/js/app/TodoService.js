'use strict';
 
angular.module('crudApp').factory('TodoService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {
 
            var factory = {
                loadAllTodos: loadAllTodos,
                getAllTodos: getAllTodos,
                getTodo: getTodo
            };
 
            return factory;
 
            function loadAllTodos() {
                console.log('Fetching all todos');
                var deferred = $q.defer();
                $http.get(urls.USER_SERVICE_API)
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
 
            function getAllTodos(){
                return $localStorage.todos;
            }
 
            function getTodo(id) {
                console.log('Fetching todo with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.USER_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully todo with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading todo with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
            function removeTodo(id) {
                console.log('Removing todo with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.USER_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllTodos();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing todo with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
        }
    ]);