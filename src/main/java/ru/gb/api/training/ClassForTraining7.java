package ru.gb.api.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gb.model.Student;

import java.util.List;

@Controller
//@ResponseBody
@RequestMapping("/sc")
public class ClassForTraining7 {
@Autowired
private ScRepo scr;


//http://localhost:8180/app/sc/all
    @GetMapping("/all")
    public String getAllSc(Model model){

        List<Sc> scs = scr.getAll();
        model.addAttribute("scs",scs);

        return "scs";
    }



}
