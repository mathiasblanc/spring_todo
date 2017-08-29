<div class="generic-container">

	<div class="panel panel-default">

		<div class="panel-heading">
			<span class="lead">Todos </span>
		</div>
		<div class="panel-body">
			<div class="table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>NAME</th>
							<th>DESCRIPTION</th>
							<th>CREATED AT</th>
							<th>DONE AT</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="t in ctrl.getAllTodos()">
							<td>{{t.name}}</td>
							<td>{{t.description}}</td>
							<td>{{t.createdAt}}</td>
							<td>{{t.doneAt}}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>