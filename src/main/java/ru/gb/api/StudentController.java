package ru.gb.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.api.product.Product;
import ru.gb.model.Student;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

        @Autowired
        private StudentRepository studentRepository;


        @GetMapping("/all")
        public String getAllStudents(Model model){
              /*  List<Student> students = List.of(
                        new Student(),
                        new Student(),
                        new Student(),
                        new Student(),
                        new Student());
*/
                List<Student> students = studentRepository.getAll();
                model.addAttribute("students",students);

                return "students"; //  можно было написать: return "students.html";  --- но напишем без расширения.
        }

       /* @PostMapping("/getPStudent")
        @ResponseBody
        public Student getStudent(@RequestBody Student s){
                s.setId(20L);
                return  s;
        }
*/


}
