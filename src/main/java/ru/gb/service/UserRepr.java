package ru.gb.service;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import ru.gb.persist.Contact;
import ru.gb.persist.Role;
import ru.gb.persist.User;

import java.util.List;

public class UserRepr {

    // а данный класс содержит валидационные аннотации.
    // этот класс целиком отвечает за взаимодействие с пользователем

    private Long id;

    @NotEmpty
    private String username;
    @NotEmpty

    private String password;

    private Integer age;

    @NotEmpty

    private String matchingPassword;
    @Email
    private String email;

    // User связан с контактами отношениями ОнеТуМени

    public UserRepr() {
    }

    public UserRepr(User user) {
        this.id=user.getId();
        this.username=user.getUsername();
        this.password=user.getPassword();
        this.email=user.getEmail();
        this.age=user.getAge();
    }

    public UserRepr(String username) {
        this.username = username;
    }

    public UserRepr(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                //    ", matchingPassword='" + matchingPassword + '\'' +
                ", email='" + email + '\'' +
                '}';
    }



}
