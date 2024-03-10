package ru.gb.service;

import ch.qos.logback.core.joran.conditional.IfAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.persist.User;
import ru.gb.persist.UserRepository;
import ru.gb.persist.UserSpecification;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Autowired // аннотация, чтобы Спринг внедрил ЮзерРепозитори
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserRepr> findAll() {
        return userRepository.findAll().stream()
                .map(UserRepr::new)
                .collect(Collectors.toList());
    }


    // после добавления наследования от JPARepository добавляем Optional
    // у Optional есть методы как и у Stream API
    //  Optional это контейнер для какого -то значения, которое может быть , а может и не быть
     // поэтому метод   findById может как вернуть экземпляр с таким айдишником, так и не вернуть ничего

    // map проверит: если что -то былдо возвращено, то он пользователя "преобразует в его представление"
    // если ничего не было возвращено, то он просто вернет пустой Optional
    @Transactional
    @Override
    public Optional<UserRepr> findById(long id) {
      return userRepository.findById(id)
              .map(UserRepr::new);
    }


    // вот как было до наследования от JPARepository:
 /*   @Transactional
    @Override
    public UserRepr findById(long id) {
        User user  = userRepository.findById(id);
        if(user!=null){
            return new UserRepr(user);
        } return null;
    }
    */


    @Transactional
    @Override
    public void save(UserRepr user) {
        User userToSave = new User(user);
        userRepository.save(userToSave);
        if (user.getId()==null){
        user.setId(userToSave.getId());
    }}




    // вот как было до наследования от JPARepository (было 2 метода -- insert и update , но мы оставляем только один -- save):

 /*   @Transactional
    @Override
    public void insert(UserRepr user) {
        userRepository.insert(new User(user));
    }
    @Transactional
    @Override
    public void update(UserRepr user) {
        userRepository.update(new User(user));

    }*/


    // вот как было до наследования от JPARepository:
  /*  @Transactional
    @Override
    public void delete(long id) {
        userRepository.delete(id);   // это мы просто прокинули вызов на метод delete класса UR
    }*/




    @Transactional
    @Override
    public void delete(long id) {
        userRepository.deleteById(id);   // это мы просто прокинули вызов на метод delete класса UR
    }

    /* @Override
   public List<UserRepr> findWithFilter(String usernameFilter) {
        return userRepository.findUserByUsernameLike(usernameFilter).stream()
                .map(UserRepr::new)
                .collect(Collectors.toList());
    }  */   // используем метод ниже




      // Класс Page - это для пагинации
    @Override
    public Page<UserRepr> findWithFilter(String usernameFilter, Integer minAge, Integer maxAge,
                                         Integer page, Integer size
    ) {

        // ниже для criteria API после указания JpaSpecificationExecutor<User> в UserRepository:
        //userRepository.findAll()   // выбираем Specification User Specification


        Specification<User> spec = Specification.where(null);
        if (usernameFilter!=null && !usernameFilter.isBlank()){
            spec=spec.and(UserSpecification.usernameLike(usernameFilter));
        }

        if (minAge !=null){

            spec=spec.and(UserSpecification.minAge(minAge));
        }

        if (maxAge !=null){

            spec=spec.and(UserSpecification.maxAge(maxAge));
        }


            return userRepository.findAll(spec, PageRequest.of(page,size)) // переделано под пагинацию
                    .map(UserRepr::new);


// выше -- для criteria API

      /*  return userRepository.findWithFilter(usernameFilter,minAge,maxAge).stream()
                .map(UserRepr::new)
                .collect(Collectors.toList());*/
    }
}