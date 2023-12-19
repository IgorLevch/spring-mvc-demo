package ru.gb.api.product.services;

import org.springframework.stereotype.Service;
import ru.gb.api.product.data.Product;
import ru.gb.api.product.repositories.ProductRepository;

import java.util.List;


// Сервисный слой помечается аннотацией:(это прямой наследник Компонента и ничем от компонента не отличается)
@Service
public class ProductService {
    // это второй слой, после контроллера.
    // если нужно как-то обработать запрос-данные -- это задача сервисного слоя.
    // сервисы работают с др. сервисами и с репозиториями

    private ProductRepository prRepo;

    // инжектим через конструктор:
    public ProductService(ProductRepository prRepo) {
        this.prRepo = prRepo;
    }

    public List<Product> getAllProducts(){
        return prRepo.getAll();
    }

    public void deleteById(Long id){
        prRepo.deleteById(id);
    }


    public void changeLevel(Long productId, Integer delta) {
    Product product = prRepo.findById(productId);
    product.setLevel(product.getLevel()+delta);



    }
}
