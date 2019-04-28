var vacationApp = angular.module("vacationApp", ['ngRoute']);

vacationApp.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/',{
        templateUrl: '/app/html/employees.html'
    }).when('/employees/edit/:id',{
        templateUrl: '/app/html/edit-employee.html'
    }).when('/employees/add',{
        templateUrl: '/app/html/add-employee.html'
    }).otherwise({
        redirectTo: '/'
    });
}]);

vacationApp.controller("employeesCtrl", function($scope, $http, $location){

	var baseUrlSectors = "/api/sectors";
    var baseUrlEmployees = "/api/employees";
    

    $scope.pageNum = 0;
    $scope.totalPages = 0;

    $scope.sectors = [];
    $scope.employees = [];

    $scope.newEmployee = {};
    $scope.newEmployee.identification = "";
    $scope.newEmployee.name = "";
    $scope.newEmployee.email = "";
    $scope.newEmployee.yearsOfService = "";
    $scope.newEmployee.sectorId = "";
    


    $scope.searchEmployee = {};
    $scope.searchEmployee.identification = "";
    $scope.searchEmployee.name = "";
    $scope.searchEmployee.sectorId = "";

    var getEmployees = function(){

        var config = {params: {}};

        config.params.pageNum = $scope.pageNum;

        if($scope.searchEmployee.identification != ""){
            config.params.identification = $scope.searchEmployee.identification;
        }

        if($scope.searchEmployee.name != ""){
            config.params.name = $scope.searchEmployee.name;
        }

        if($scope.searchEmployee.sectorId != ""){
            config.params.sectorId = $scope.searchEmployee.sectorId;
        }


        $http.get(baseUrlEmployees, config)
            .then(
            	function success(res){
            		$scope.employees = res.data;
            		$scope.totalPages = res.headers('totalPages');
            	},
            	function error(res){
            		alert("Neuspesno dobavljanje radnika!");
            	}
            );
    };

    var getSectors = function(){

        $http.get(baseUrlSectors)
            .then(
            	function success(res){
            		$scope.sectors = res.data;
            	},
            	function error(res){
            		alert("Neuspesno dobavljanje sektora!");
            	}
            );

    };

    getSectors();
    getEmployees();
   

    $scope.back = function(){
        if($scope.pageNum > 0) {
            $scope.pageNum = $scope.pageNum - 1;
            getEmployees();
        }
    };

    $scope.forward = function(){
        if($scope.pageNum < $scope.totalPages - 1){
            $scope.pageNum = $scope.pageNum + 1;
            getEmployees();
        }
    };

    $scope.doAdd = function(){
        $http.post(baseUrlEmployees, $scope.newEmployee)
            .then(
            	function success(res){
            		getEmployees();
            		$location.path("/");
	
            	    $scope.newEmployee.identification = "";
            	    $scope.newEmployee.name = "";
            	    $scope.newEmployee.email = "";
            	    $scope.newEmployee.yearsOfService = "";
            	    $scope.newEmployee.sectorId = "";
            	},
            	function error(res){
            		alert("Neuspesno dodavanje!");
            	}
            );
    };

    $scope.search = function () {
        $scope.pageNum = 0;
        getEmployees();
    }
    
    $scope.cancelSearch = function() {
    	$scope.searchEmployee.identification = "";
        $scope.searchEmployee.name = "";
        $scope.searchEmployee.sectorId = "";
        $scope.pageNum = 0;
        getEmployees();
	}

    $scope.edit = function(id){
        $location.path('/employees/edit/' + id);
    }
    
    $scope.add = function(){
    	$location.path('/employees/add');
    }

    $scope.remove = function(id){
        $http.delete(baseUrlEmployees + "/" + id).then(
            function success(data){
            	getEmployees();
            },
            function error(data){
                alert("Neuspesno brisanje!");
            }
        );
    }
    
});

vacationApp.controller("editEmployeeCtrl", function($scope, $http, $routeParams, $location){

	var baseUrlSectors = "/api/sectors";
    var baseUrlEmployees = "/api/employees";
    
    var getSectors = function(){

        $http.get(baseUrlSectors)
            .then(
            	function success(res){
            		$scope.sectors = res.data;
            		
            	},
            	function error(res){
            		alert("Neuspesno dobavljanje sektora!");
            	}
            );

    };

    getSectors();
    
    $scope.oldEmployee = null;

    var getOldEmployee= function(){

        $http.get(baseUrlEmployees + "/" + $routeParams.id)
            .then(
            	function success(res){
            		$scope.oldEmployee = res.data;
            	},
            	function error(res){
            		alert("Neušpesno dobavljanje radnika.");
            	}
            );

    }
    
    getOldEmployee();
    
    
    
    $scope.doEdit = function(){
        $http.put(baseUrlEmployees + "/" + $scope.oldEmployee.id, $scope.oldEmployee)
            .then(
        		function success(data){
        			alert("Uspešno izmenjen radnik!");
        			$location.path("/");
        		},
        		function error(data){
        			alert("Neuspešna izmena radnika.");
        		}
            );
    }
});

