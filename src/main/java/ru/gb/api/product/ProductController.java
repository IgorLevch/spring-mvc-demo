package ru.gb.api.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {



    private ProductRepository prRep;

    @Autowired
    public ProductController(ProductRepository prRep) {
        this.prRep = prRep;
    }

    @GetMapping("/all")
    public String getAllProducts(Model model){
        List<Product> products = prRep.getAll();
        model.addAttribute("products", products);
        return "products2";

    }






    @PostMapping("/update")
    public String update(Product product){
    prRep.update(product);
    return "redirect:/product";
    }
    @GetMapping("/{id}")
    public String editPage(@PathVariable("id")Long id,Model model){
        // Конструкция из @PathVariable и шаблона {id} заставит Спринг МВС извлечь элемент {id} из УРЛ
        // и преобразовать его в тип ЛОнг и передать в виде параметра этого метода
        model.addAttribute("product",prRep.getById(id));
        return "products3";
    }




}
