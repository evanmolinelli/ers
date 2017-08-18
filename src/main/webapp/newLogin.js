var app = angular.module("login", ["ngRoute"]);

app.config(function($routeProvider, $locationProvider) {
	$locationProvider.hashPrefix(""); // don't use !# in the URL
	$routeProvider.when("/", {
		templateUrl : "login.html",
		controller : "loginCtrl"
	}).otherwise({
		redirect: "login.html"
	})
});

app.controller("loginCtrl", function($scope, $http) {
		$http({
			method : "post",
			url : "login.do",
			data : $scope.user
		}).then(function(response) {
			if (response.status == 200) {
				$scope.user = response.data;
				window.alert("You good bro.");
			} else {
				console.log("No user logged in.");
				window.alert("C'mon guy number 4!");
			}
		});
});

function checkTextField(field) {
	 if(document.getElementById('username').value === "")
	 { window.alert("Must be valid username and password.");
	 	return false; }
	 else
		 {console.log("good to go.")
		 	return true;}
	}
