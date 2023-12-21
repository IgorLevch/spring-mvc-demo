package ru.gb.api.training;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class ClassForTraining2 {

    //http://localhost:8180/app/gbye?name=Igoryan

    @GetMapping("/gbye")
    public String goodbye(@RequestParam(name =  "name-addd",required = false,defaultValue = " ") String name){
        return "<h1>Goodbye" + name+"!</h1>";

    }

    //http://localhost:8180/app/gbye?name-addd=Igorjan
}
