package ru.gb.api.training;

import org.springframework.stereotype.Repository;
import ru.gb.model.Student;

import java.util.List;
@Repository
public class ScRepo {


    public List<Sc> getAll (){
        List<Sc> scs = List.of(
                new Sc(),
                new Sc(),
                new Sc(),
                new Sc(),
                new Sc());

        return scs;

    }
}
