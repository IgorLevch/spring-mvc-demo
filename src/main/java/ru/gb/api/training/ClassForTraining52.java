package ru.gb.api.training;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//@ResponseBody
@RequestMapping("/yy")
public class ClassForTraining52 {


    /*@GetMapping("/hi")
    public String hi(){
        return "hi";
    }
*/

//  http://localhost:8180/app/yy/gby?name=olya

    @GetMapping("/gby")
    public String goodbye(Model model, @RequestParam    String name){
        model.addAttribute("name", name);
        return "hellohi";

    }



}
