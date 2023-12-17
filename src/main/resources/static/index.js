

angular.module('app', []).controller('indexController', function($scope,$http){

    const contextPath = 'http://localhost:8180/app';

    $scope.loadProducts = function() {
    };
    $http.get(contextPath+'/allProd')
        .then(function(response){
            $scope.ProductList =response.data;
        });

    $scope.deleteProduct = function (productId) {
        $http.get(contextPath + '/allProd/delete/' + productId)
            .then(function (response) {
                $scope.loadProducts();
            });

    }

    // $scope - это связочка. Если мы что-то положим тут (в джава Скрипте) в $scope, то мы это увидим в ХТМЛ
    // scope - это коробка для обмена данными между ХТМЛ и ДжиЭс
    // это просто пример как раз scope:
    $scope.hh =     'uhahah';
    $scope.loadProducts();
});