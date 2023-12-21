package ru.gb.api.training;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@ResponseBody
public class ClassForTraining {

    // http://localhost:8180/app/fine

    @GetMapping("/fine")
    public String fine(){
        return "Hi everybody. I m Lucy";
    }

}
