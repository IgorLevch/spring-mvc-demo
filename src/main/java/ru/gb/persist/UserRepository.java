package ru.gb.persist;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.service.UserRepr;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    // нужен  JPA репозиторий для сущности User и с  ключом Long

    // JpaSpecificationExecutor - это для работы CriteriaBuilder


    // Теперь берем и все, что ниже удаляем (я закомментирую), после чего делаем из класса интерфейс и наследуемся от :
    // extends JpaRepository<User, Long>
    // этого будет достаточно, чтобы Спринг сделал репозиторий для работы с классом Юзер

  /*  @PersistenceContext  //аннотация дает: каждый раз , когда будет доходить до использования данного поля,
    // в нем будет оказываться правильный экземпляр этого EntityManager
    private EntityManager em;


    public List<User> findAll(){
        return em.createQuery("from User", User.class)
                .getResultList();
    }

    public User findById(long id){
        return em.find(User.class, id);
    }

    //    @Transactional // аннотацию добавляем Спринговую (по этой аннотации спринг пойемт, что нужно открыть транзакцию
        //, потом выполнить данный метод , потом закрыть транзакцию )
        public void insert(User user){
         em.persist(user);
            // для того, чтобы откатить (сделать Роллбек)  ставим исключение
        }
    //    @Transactional  -- эти аннотации выносим на сервисный уровень
        public void update(User user){
        em.merge(user);
        }
     //   @Transactional
        public void delete(long id){
        em.createQuery("delete from User where id = :id")
                .setParameter("id",id)
                .executeUpdate();

        }*/



    // ниже добавляем метод после добавления JPA:

       List<User> findUserByUsernameLike(String username);  // spring data JPA по имени метода понимает, какого рода запрос
    // мы от него хотим

    @Query("select u from User u " +
            "where (u.username like :username or :username is null) and " +  //если сработает is null - т.е. нет такого
                // услвоия в запросе, то обычно сервер понимает это и прекращает поиск
            // это называется динамический SQL.Его использовать в целом не рекомендуют
             "(u.age >= :minAge or :minAge is null) and " +
              "  (u.age <= :maxAge or :maxAge is null)")
    List<User> findWithFilter(@Param("username") String usernameFilter,
                              @Param("minAge") Integer minAge,
                              @Param("maxAge") Integer maxAge);
    // двоеточие  - это мы указваем, что это внешний параметр, который задается после @Param



}
