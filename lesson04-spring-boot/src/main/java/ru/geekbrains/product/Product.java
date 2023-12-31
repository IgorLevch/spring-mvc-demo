package ru.geekbrains.product;

public class Product {

    private static Long counter = 1L;

    private Long id;
    private String title;
    private Long cost;


    public Product(){
        this.id = counter++;
        this.cost= counter+15 ;
        this.title = "Product #" +id;

    }

    public static Long getCounter() {
        return counter;
    }


    public Long getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public Long getCost() {
        return cost;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public Product(Long id, String title, Long cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }
}
