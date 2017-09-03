<!DOCTYPE html>

<html lang="en" ng-app="crudApp">
<head>
<title>${title}</title>
<link href="css/bootstrap.min.css" rel="stylesheet" />
<link href="css/app.css" rel="stylesheet" />
<link href="css/todo.css" rel="stylesheet" />
<link rel="icon" type="image/png" href="img/icon.png" />
</head>
<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">

			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">cdtilde.io</a>
			</div>

			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Todo</a></li>
					<li><a href="#about">About</a></li>
					<li><a href="#contact">Contact</a></li>
				</ul>
			</div>

		</div>
	</nav>

	<div ui-view></div>


	<script src="js/lib/angular.min.js"></script>
	<script src="js/lib/angular-ui-router.min.js"></script>
	<script src="js/lib/localforage.min.js"></script>
	<script src="js/lib/ngStorage.min.js"></script>
	<script src="js/app/app.js"></script>
	<script src="js/app/TodoService.js"></script>
	<script src="js/app/TodoController.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="js/lib/bootstrap.min.js"></script>

</body>
</html>