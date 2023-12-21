package ru.gb.api.training;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//@ResponseBody
@RequestMapping("/y")
public class ClassForTraining51 {
    //http://localhost:8180/app/y/hi

    @GetMapping("/hi")
    public String hi(){
        return "hi";
    }   // с ResponseBody и без ResponseBody выводит по-разному



    //http://localhost:8180/app/y/gby?name=tonya    ---   работает только с ResponseBody
    @GetMapping("/gby")
    public String goodbye(@RequestParam    String name){
        return "<h1>Goodbye" + name+"!</h1>";

    }



}
