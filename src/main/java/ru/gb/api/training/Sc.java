package ru.gb.api.training;

public class Sc {
    private static Integer counter = 1;
   private Integer id;
    private String name;

    public Sc() {
        this.id = counter++;
        this.name = "Sc #" +id;
    }

    public Integer getId() {
        return id;
    }



    public String getName() {
        return name;
    }



    public static Integer getCounter() {
        return counter;
    }
}
