package ru.gb.api.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@ResponseBody
@RequestMapping("/sc/rest")
public class ClassForTraining8 {
//http://localhost:8180/app/sc/rest/all


    // это отрисовка таблицы на статике (без хтмл)

    @GetMapping("/all")
    public List<Sc> getAlls (){
        List<Sc> scs = List.of(
                new Sc(),
                new Sc(),
                new Sc(),
                new Sc(),
                new Sc());

        return scs;

    }



}
