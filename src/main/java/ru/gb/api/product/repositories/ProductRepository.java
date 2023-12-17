package ru.gb.api.product.repositories;

import org.springframework.stereotype.Repository;
import ru.gb.api.product.data.Product;

import java.util.List;


// Репозитории - это слой доступа к данным. Как правило, задача репозиториев - это взаимодействовать с БД.
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
                new Product(1L,"ggh",45L,80),
                new Product(2L,"hhj",78L,80),
                new Product(78L,"jjhh",876L,80),
                new Product(9876L,"hgfre",98L,80));


        return products;

    }



//    public void add(Product p){
//        products.add(p);
//    }

    public void deleteById(Long Id){
        products.removeIf(s->s.getId().equals(Id));
    }





}
