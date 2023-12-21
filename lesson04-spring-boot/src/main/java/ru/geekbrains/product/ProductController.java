package ru.geekbrains.product;

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
        return "products";

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
    @PostMapping("/getProduct")
    @ResponseBody
    public Product getProduct(@RequestBody Product p){
        p.setId(20L);
        return  p;
    }


        @GetMapping("/show_form")
    public String showFormPage (){
        return "simple_form";
        }


    @GetMapping("/prodAdd")
    public String prodAdd (@RequestParam Long id, @RequestParam  String title, @RequestParam Long cost){
        // здесь указываем ссылочное значение, потому что если параметр необязательный, то придет null (если пользователь это значение не указал).
        //  а в  случае с примитовом если придет ноль, то будет вопрос - это ноль от пользователя или он не указал.

        Product pr = new Product(id, title, cost);
        prRep.add(pr);

        return "redirect:/all";
    }
    //http://localhost:8180/app/products/resp
    @GetMapping("/resp")
    @ResponseBody
   public Product respMethod(){
        return new Product(123L,"ggg",987L);
    }

    @GetMapping("/allProd")
    @ResponseBody
    public List<Product> getAllProducts(){
        return prRep.getAll();
    }

    @GetMapping("/new")
    public String create(Model model){
        model.addAttribute("products", new Product());
        return "simple_form";
    }


}
