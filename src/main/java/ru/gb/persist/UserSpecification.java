package ru.gb.persist;

import org.springframework.data.jpa.domain.Specification;

public final class UserSpecification {

    // этот класс для  criteria API


        public static Specification<User>  usernameLike(String name){
            return (root,query,cb) -> cb.like(root.get("username"),"%"+name+"%");

        }

        public static Specification<User> minAge(Integer minAge){
            return (root,query,cb) -> cb.ge(root.get("age"),minAge);    // ge - то же самое , что и GreaterorEquals

        }

    public static Specification<User> maxAge(Integer maxAge){
        return (root,query,cb) -> cb.le(root.get("age"),maxAge);    // le - то же самое , что и lessThan EqualTo

    }



}
