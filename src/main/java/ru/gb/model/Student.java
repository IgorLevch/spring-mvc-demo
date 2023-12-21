package ru.gb.model;

public class Student {

    private static Long counter = 1L;

    private Long id;
    private String name;

    public Student(){
        this.id = counter++;
        this.name = "Student #" +id;
    }

    public static Long getCounter() {
        return counter;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
