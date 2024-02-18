package ru.gb.api.product.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.gb.api.product.data.Product;
import ru.gb.api.product.repositories.ProductRepository;
import ru.gb.api.product.services.ProductService;


import java.util.List;

@RestController
public class ProductController {
// контроллер - это верхний слой. Как правило, взаимодействует с Сервисным слоем, а не с репозиторием.


    private ProductService proSer;


    public ProductController(ProductService proSer) {
        this.proSer = proSer;
    }

    @GetMapping("/allProd")
     public List<Product> getAllProducts(){
        return proSer.getAllProducts();
    }

/*    @PostMapping("/allProd") // Спринг 2 этих метода не перепутает. Если посылваем ПОСТ запрос - содадим нового
    // если ГЕТ запрос - выведет всех
    public Product saveNewProduct(@RequestBody Product product){
        // @RequestBody означает, что в теле запроса, пришдешего с фронта будет Product
        // и мы просим Стринг на автомате из тела запроса собрать объект Product / Это сделает бибилотека jackson
        return proSer.save(product);
    }*/

    @GetMapping("/allProd/delete/{id}")
    public void deleteById(@PathVariable Long id){
        proSer.deleteById(id);
    }


    @GetMapping("/allProd/change_level")
    public void changeLevel(@RequestParam Long productId, @RequestParam Integer delta){
        proSer.changeLevel(productId, delta);
    }



}
