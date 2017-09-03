<div class="container">
	<div class="starter-template">
		<div class="row rounded">
			<div class="col-md-6">
				<div class="todolist not-done">
					<h1>Todos</h1>

					<form ng-submit="ctrl.submit()" name="createForm"
						class="input-group">

						<span class="input-group-btn">
							<button id="create" class="btn btn-success" ng-disabled="!ctrl.todo.description">Add</button>
						</span> <input type="text" class="form-control add-todo"
							placeholder="so many things ..." ng-model="ctrl.todo.description">

					</form>

					<hr>

					<ul id="sortable" class="list-unstyled"
						ng-repeat="t in ctrl.getAllTodos()">

						<li class="ui-state-default">
							<div class="checkbox">
								<label> <input type="checkbox"
									ng-click="ctrl.setDone(t.id)" /> {{t.description}}
								</label>
							</div>
						</li>

					</ul>

					<div class="todo-footer">
						<strong><span class="count-todos"></span>{{ctrl.todos.length}}</strong>
						Items Left
					</div>

				</div>
			</div>

			<div class="col-md-6">

				<div class="todolist">
					<h1>Already Done</h1>	
					
					<div ng-show="!ctrl.getAllDoneTodos().length">Start doing stuff already</div>

					<ul id="done-items" class="list-unstyled"
						ng-repeat="t in ctrl.getAllDoneTodos()">

						<li>{{t.description}}
							<button class="remove-item btn btn-default btn-xs pull-right" ng-click="ctrl.deleteTodo(t.id)">
								<span class="glyphicon glyphicon-remove"></span>
							</button>
						</li>
					</ul>
				</div>

			</div>
		</div>
	</div>
</div>
