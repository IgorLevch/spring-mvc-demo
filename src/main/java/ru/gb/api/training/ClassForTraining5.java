package ru.gb.api.training;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClassForTraining5 {
    //http://localhost:8180/app/hi

    @GetMapping("/hi")
    public String hi(){
        return "hi";
    }

}
