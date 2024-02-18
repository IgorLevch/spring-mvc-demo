

angular.module('app', []).controller('indexController', function($scope,$http){
/*app - название как и в файле индекс.хтмл*/
/*'app', []  -  []  - обязательны. Означает, что создаем новое приложение без каких-либо зависимостей дополнительных  */
/*'indexController', function($scope,$http){  --  'indexController' - это то, как называется, а function - это внутренность контроллера*/

/*$scope,$http  - добавляются 2 стандартные зависимости
$scope - это некий контекст, куда можно складывать данные и обмениваться этими данными с фронтом
т.е. например, если я положу в scope переменную: $scope.a=10; (это мы положили в контекст)
В index.html где-нибудь в начале body можно отпечатать букву {{a}}*/
/*(все, чем хотим обмениваться с html, складываем в $scope)*/

/*$http - модуль для того, тчобы отправлять POST, GET запросы ( взаимодействие с бекендом)*/

/*все, что внутри function(){} - это реализация контролера*/

    const contextPath = 'http://localhost:8180/app';
    /*это константа - мы создали, чтобы знать корень нашего приложения*/

    console.log(123);
    // это выводим в лог, чтобы понять , загружается что-то или нет.

    $scope.loadProducts = function() {

    $http.get(contextPath+'/allProd')
    // http.get - означает, что я хочу послать get запрос
        .then(function(response){ // а это ответ на наш запрос какой должен быть
        console.log(response.data)
        // выводим в лог то, что попадет в этот список всех продуктов
            console.log(response);
            $scope.ProductList =response.data;  // data - это стандартное поле в ответе: это тот JSON, который отдал бекэнд
        });
    };



    $scope.deleteProduct = function (productId) {
        $http.get(contextPath + '/allProd/delete/' + productId)
            .then(function (response) {
                $scope.loadProducts();
                 console.log(response.data)
                 // тоже проверяем работу метода
            });

    }


    $scope.changeLevel = function (productId, delta) {
        console.log('Click!');
        // это очень важно! нужно для проверки того, срабатывает ли кнопка после ее нажатия (результат смотрим в графе
        // )

        $http({
            url: contextPath + '/allProd/change_level',
            method: 'GET',
            params: {
                productId: productId,
                delta: delta
            }
        }).then(function (response) {
                $scope.loadProducts();
            });

    }

    $scope.createProductJson = function(){
        console.log($scope.newProductJson);

         $http.post(contextPath+'/allProd', $scope.newProductJson)
            // http.get - означает, что я хочу послать get запрос
                .then(function(response){ // а это ответ на наш запрос какой должен быть
                $scope.loadProducts();
                });

    }


      $scope.sumTwoNumbers = function(){
            console.log($scope.calcAdd);


        $http({
            url: contextPath + '/calc/add',
            method: 'get',
            params: {
                a: $scope.calcAdd.a,
                b: $scope.calcAdd.b
            }
        }).then(function (response) {
               alert('the sum is '+response.data.value);
               $scope.calcAdd = null;
               /*это чтобы из полей калькулятора удалялись цифры*/
            });
           /* alert - это js-вский показ всплывающего окошка*/

        }



    // $scope - это связочка. Если мы что-то положим тут (в джава Скрипте) в $scope, то мы это увидим в ХТМЛ
    // scope - это коробка для обмена данными между ХТМЛ и ДжиЭс
    // это просто пример как раз scope:
    $scope.hh =     'uhahah';
    $scope.loadProducts();
    // функцию мы вызываем либо с хтмл файла, либо прямо в коде говорим:  $scope.loadProducts();
});