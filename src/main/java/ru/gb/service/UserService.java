package ru.gb.service;




import java.util.List;

public interface UserService {
     List<UserRepr> findAll();

     UserRepr findById(long id);

    void insert(UserRepr user);

    void update(UserRepr user);

    void delete(long id);

}
