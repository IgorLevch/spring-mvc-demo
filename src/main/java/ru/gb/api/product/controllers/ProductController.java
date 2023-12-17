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

    @GetMapping("/allProd/delete/{id}")
    public void deleteById(@PathVariable Long id){
        proSer.deleteById(id);
    }


}
