var app = angular.module("mainPage", [ "ngRoute" ]); // initializes the angular
// app
app.config(function($routeProvider, $locationProvider) {
	$locationProvider.hashPrefix(""); // don't use !# in the URL
	$routeProvider.when("/viewReimb", {
		templateUrl : "allReimb.html",
		controller : "viewAllReimbCtrl"
	}).when("/view", {
		templateUrl : "view.html",
		controller : "viewCtrl"
	}).when("/createReimb", {
		templateUrl : "createReimbursement.html",
		controller : "createReimbCtrl"
	}).when("/manageTable", {
		templateUrl : "managerPage.html",
		controller : "managerCtrl"
	}).otherwise({
		templateUrl: "reimburseImage.html"
	});
});

app.controller("viewAllReimbCtrl", function($scope, $http) {
	$http({
		method : "post",
		url : "allReimb.do"
	}).then(function(response) {
		if (response.status == 200) {
			$scope.reimbursements = response.data; 
			
		} else {
			console.log("No user logged in.");
		}
	});
});
app.controller("viewCtrl", function($scope, $http) {
	$http({
		method : "POST",
		url : "view.do"
	}).then(function(response) {
		if (response.status == 200) {
			$scope.users = response.data;
		} else {
			console.log("No user logged in.");
		}
	});
});

app.controller("createReimbCtrl", function($scope, $http) {
	$scope.addReimbursement = function() {
		console.log($scope.newReimb);
		// data: request body
		$http({
			method : "post",
			url : "createReimb.do",
			data : $scope.newReimb
		}).then(function(response) {
			if (response.status == 200) {
				$scope.newReimb = response.data;
			} else {
				console.log("No user logged in.");
			}
		});
	}
})

app.controller("managerCtrl", function($scope, $http) {
	$http({
		method : "POST",
		url : "manage.do"
	}).then(function(response) {
		if (response.status == 200) {
			$scope.reimbursements = response.data;
		} else {
			console.log("No user logged in.");
		}
	});
	
	$scope.accept = function(reimbursement){
		console.log(reimbursement);
		$http({
			method : "POST",
			url : "manageAccept.do",
			data : reimbursement
		}).then(function(response) {
			if (response.status == 200) {
				console.log(response);
				location.reload();
			} else {
				console.log("No user logged in.");
			}
		});
	}
	
	$scope.deny = function(reimbursement){
		$http({
			method : "POST",
			url : "managerDeny.do",
			data : reimbursement
		}).then(function(response) {
			if (response.status == 200) {
				console.log(response);
				location.reload();
			} else {
				console.log("No user logged in.");
			}
		});
	}
});

function checkTextField(field) {
	 if(document.getElementById('createAmt').value === "" || document.getElementById('createDescr').value === "")
	 { window.alert("Must be valid reimbursement.");
	 	return false; }
	 else
		 {console.log("good to go.")
		 	return true;}
	}
