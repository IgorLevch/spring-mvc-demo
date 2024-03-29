package ru.gb.persist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import ru.gb.service.UserRepr;

import java.util.List;


// Это класс сущностей, который только содержит аннотации из пакета персистанс
// и отвечает он только за взаимоедйствие с БД

@Entity
@Table(name="users")
@NamedQueries({   // это когда хотим сделать короткую запись часто отправляемого запроса
        @NamedQuery(name="userByName", query="from User u where u.username=:username"),
        @NamedQuery(name="AllUsers", query="from User")
        // см.ниже em.createNamedQuery
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false)
  //  @NotEmpty
    private String username;
  //  @NotEmpty

    @JsonIgnore  // если не хотим, чтобы выводилось
    @Column(nullable = false,length = 512)
    private String password;

 //   @NotEmpty
    @Transient // поле не будет сохраняться в БД , в соответствии с этой аннотацией
    private String matchingPassword;
  //  @Email
   @Column
    private String email;


   @Column
   private Integer age;

    // User связан с контактами отношениями ОнеТуМени
    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL)  // это имя поля в классе Contact, в котором хранится ссылка (чтобы не образовывалась промежуточная таблица)
    private List<Contact> contacts; // в поле будут содержаться все контакты, которые привязаны к данному пользователю
    // каскадирование - это когда вместе с например удалением пользователя удаляются все его контакты
    //  обычно либо ALL Type, либо вообще не ставится
    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }


    public User(UserRepr user) {
        this.id=user.getId();
        this.username=user.getUsername();
        this.password=user.getPassword();
        this.email=user.getEmail();
        this.age =user.getAge();
    }


    @ManyToMany(mappedBy = "users") // это нужно для того, чтобы не создавались лишние таблицы
    private List<Role> roles;



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


    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }


    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
