package ru.gb.api;

import org.springframework.stereotype.Repository;
import ru.gb.model.Student;

import java.util.List;

@Repository
public class StudentRepository {

    public List<Student> getAll (){
        List<Student> students = List.of(
                new Student(),
                new Student(),
                new Student(),
                new Student(),
                new Student());

        return students;

    }
}
