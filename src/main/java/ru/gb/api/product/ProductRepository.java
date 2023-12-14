package ru.gb.api.product;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;


@Repository
public class ProductRepository {

    private List<Product> products;

    // @PostConstruct   эта аннотация означает, что сразу, после того, как данный бин создан и инициализирован,
    // будет вызван данный метод (перед выполнением каких-либо еще действий).


 /*   public List<Product> getAll(){
        products =List.of(
             new Product(),
             new Product(),
             new Product(),
             new Product());

        return products;
    }*/

    public List<Product> getAll(){
        products =List.of(
                new Product(1L,"ggh",45L),
                new Product(2L,"hhj",78L),
                new Product(78L,"jjhh",876L),
                new Product(9876L,"hgfre",98L));


        return products;

    }



    public void add(Product p){
        products.add(p);
    }


    public void update(Product product) {
        products.add(product);
    }
    public Product getById(Long Id){
        //return products.get(Math.toIntExact(Id));
        return products.stream().filter(p->p.getId().equals(Id)).findFirst().orElseThrow(()->new RuntimeException());

    }





}
