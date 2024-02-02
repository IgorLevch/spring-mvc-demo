package ru.gb.persist;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserRepository {
    @PersistenceContext  //аннотация дает: каждый раз , когда будет доходить до использования данного поля,
    // в нем будет оказываться правильный экземпляр этого EntityManager
    private EntityManager em;


    public List<User> findAll(){
        return em.createQuery("from User", User.class)
                .getResultList();
    }

    public User findById(long id){
        return em.find(User.class, id);
    }

        @Transactional // аннотацию добавляем Спринговую (по этой аннотации спринг пойемт, что нужно открыть транзакцию
        //, потом выполнить данный метод , потом закрыть транзакцию )
        public void insert(User user){
         em.persist(user);
            // для того, чтобы откатить (сделать Роллбек)  ставим исключение
        }
        @Transactional
        public void update(User user){
        em.merge(user);
        }
        @Transactional
        public void delete(long id){
        em.createQuery("delete from User where id = :id")
                .setParameter("id",id)
                .executeUpdate();

        }

}
