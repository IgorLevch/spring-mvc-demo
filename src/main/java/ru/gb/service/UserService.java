package ru.gb.service;




import java.util.List;
import java.util.Optional;

public interface UserService {
     List<UserRepr> findAll();

    // после добавления наследования от JPARepository добавляем Optional
     Optional<UserRepr> findById(long id);

    void save(UserRepr user);

  //  void update(UserRepr user);  после добавления JPARep -- нам этот метод не нужен

    void delete(long id);

    List<UserRepr> findWithFilter(String usernameFilter);

}
