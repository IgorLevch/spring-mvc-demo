package ru.gb.api.product;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;


@Repository
public class ProductRepository {

    private List<Product> products;

    // @PostConstruct   эта аннотация означает, что сразу, после того, как данный бин создан и инициализирован,
    // будет вызван данный метод (перед выполнением каких-либо еще действий).


    public List<Product> getAll(){
        products =List.of(
             new Product(),
             new Product(),
             new Product(),
             new Product());

        return products;
    }



    public void update(Product product) {
        products.add(product);
    }
    public Product getById(Long Id){
        return products.get(Math.toIntExact(Id));
        //return products.stream().filter(p->p.getId().equals(Id)).findFirst().orElseThrow(()->new RuntimeException());
    }





}
