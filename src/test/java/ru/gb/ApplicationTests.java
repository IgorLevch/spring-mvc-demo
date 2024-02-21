package ru.gb;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.gb.persist.User;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ApplicationTests {

	@PersistenceContext
	private EntityManager em;


	@Test
	void contextLoads() {

		em.createQuery("select u from User u " +
				"where (u.username like :username or :username is null) and " +  //если сработает is null - т.е. нет такого
				// услвоия в запросе, то обычно сервер понимает это и прекращает поиск
				// это называется динамический SQL.Его использовать в целом не рекомендуют
				"(u.age >= :minAge or :minAge is null) and " +
				"  (u.age <= :maxAge or :maxAge is null)", User.class).getResultList();  // User тут - это User, который указан
		// в методе createQuery

		// когда запрос нужно конструировать динамически на помощь приходит такая вещь как creteria API
		// CriteriaBuilder применяется, когда запрос может сильно варьироваться.
		// Если же запрос не варьируется, достаточно и  HQL:
		CriteriaBuilder  cb = em.getCriteriaBuilder(); // вызываем критериа билдер
		CriteriaQuery<User> query = cb.createQuery(User.class);// далее создаем запрос и указываем , к каой сущности будет этот запрос (User.class)
		query.from(User.class);				// далее конструируем конструкцию from у этого запроса
		// а этот User , который в "from User"  в select запросе
		// далее необходимо добавить 3 условия :

		Root<User>  root = query.from(User.class);


			List<Predicate> predicates = new ArrayList<>();                // Jakarta Persistance Creteria
		//далее в этот список предикатов добавляем все условия, которые бы нам хотелось:

		predicates.add(cb.like(root.get("username"),"%a%"));  // root - это поле
		predicates.add(cb.greaterThanOrEqualTo(root.get("age"),10));
		predicates.add(cb.lessThanOrEqualTo(root.get("age"),25));

		// после списка предикатов формируем сам запрос:

		em.createQuery(query.select(root).where(predicates.toArray(new Predicate[0])))
				.getResultList();

	//	resultList.forEach(System.out::println);  чего-то не выводится

	}

}
