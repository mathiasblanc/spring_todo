
'use strict';
 
angular.module('crudApp').controller('TodoController',
    ['TodoService', '$scope',  function( TodoService, $scope) {
 
        var self = this;
        self.todo = {};
        self.todos=[];
        self.doneTodo= {};
        self.doneTodos={};
 
        self.submit = submit;
        self.getAllTodos = getAllTodos;
        self.getAllDoneTodos = getAllDoneTodos;
        self.createTodo = createTodo;
        self.setDone = setDone;
        self.deleteTodo = deleteTodo;
        self.editTodo = editTodo;
        self.reset = reset;
        
        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;
 
        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;
 
        function submit() {
            console.log('Submitting');
            
            if (self.todo.id === undefined || self.todo.id === null) {
                console.log('Saving New Todo', self.todo);
                createTodo(self.todo);
            } else {
                setDone(self.todo, self.todo.id);
                console.log('Todo updated with id ', self.todo.id);
            }
        }
 
        function createTodo(todo) {
            console.log('About to create todo');
            
            TodoService.createTodo(todo)
                .then(
                    function (response) {
                        console.log('Todo created successfully');
                        self.successMessage = 'Todo created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.todo={};
                        $scope.createForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating Todo');
                        self.errorMessage = 'Error while creating Todo: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }
 
 
        function setDone(id){
            console.log('About to update todo');
            
            TodoService.setDone(id)
                .then(
                    function (response){
                        console.log('Todo updated successfully');
                        self.successMessage='Todo updated successfully';
                        self.errorMessage='';
                        self.done = true;
                    },
                    function(errResponse){
                        console.error('Error while updating Todo');
                        self.errorMessage='Error while updating Todo '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }
 
 
        function deleteTodo(id){
            console.log('About to remove Todo with id '+id);
            TodoService.deleteTodo(id)
                .then(
                    function(){
                        console.log('Todo ' +id + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing todo ' +id +', Error :'+errResponse.data);
                    }
                );
            
            $scope.createForm.$setPristine();
        }
 
 
        function getAllTodos(){
            self.todos = TodoService.getAllTodos();
            return self.todos;
        }
        
        function getAllDoneTodos(){
            self.doneTodos = TodoService.getAllDoneTodos();
            return self.doneTodos;
        }
 
        function editTodo(id) {
            self.successMessage='';
            self.errorMessage='';
            TodoService.getTodo(id).then(
                function (todo) {
                    self.todo = todo;
                },
                function (errResponse) {
                    console.error('Error while removing todo ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.todo={};
            $scope.createForm.$setPristine();
        }
    }
    ]);