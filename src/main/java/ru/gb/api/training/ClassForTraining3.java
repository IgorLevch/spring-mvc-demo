package ru.gb.api.training;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class ClassForTraining3 {

    //http://localhost:8180/app/fun/serjjjj


    @GetMapping("/fun/{name}")
    public String funny(
            @PathVariable String name
    ){
        return "<h1>Hi. How are you  " + name+"!</h1>";

    }

}
