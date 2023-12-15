package ru.gb.api.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {



    private ProductRepository prRep;


    public ProductController(ProductRepository prRep) {
        this.prRep = prRep;
    }


    @GetMapping("/allProd")
     public List<Product> getAllProducts(){
        return prRep.getAll();
    }
}
