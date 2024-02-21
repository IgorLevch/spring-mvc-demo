package ru.gb.controler;

import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.gb.persist.User;
import ru.gb.service.UserRepr;
import ru.gb.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    // для запуска сюда надо добавить файлы user.html и user_form.html

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public String listPage(Model model,
                           @RequestParam("usernameFilter") Optional<String> usernameFilter,
                           @RequestParam("ageMinFilter") Optional<Integer> ageMinFilter,
                           @RequestParam("ageMaxFilter") Optional<Integer> ageMaxFilter,
                           @RequestParam("page") Optional<Integer> page, // два параметра для пагинации сюда приходят
                           @RequestParam("size") Optional<Integer> size

                                                                                              ){
        logger.info("List page requested");

        //для пагинации добавляем 2 параметра: номер страницы
        // и размер страницы(сколько записей будет отображено на 1й странице).

        Page<UserRepr> users=userService.findWithFilter(
                usernameFilter.filter(s->!s.isBlank()).orElse(null),
                ageMinFilter.orElse(null),
                ageMaxFilter.orElse(null),
                page.orElse(1)-1,  // на фронте странийцы будут отображаться с 1-цы, но данный метод
                // требует передавать страницы с нуля
                size.orElse(3) // по 3 на стр-це отображаем
        );

        model.addAttribute("users",users);
        return "user";
    }

    // вот как было до наследования от JPARepository:
 /*    @GetMapping("/{id}")
        public String editPage(@PathVariable("id") Long id,Model model){
        logger.info("Edit page for id {} requested");// здесь было id в первоисточнике
        model.addAttribute("user", userService.findById(id));
        return "user_form";

    }*/
    @GetMapping("/{id}")
    public String editPage(@PathVariable("id") Long id,Model model){
        logger.info("Edit page for id {} requested");// здесь было id в первоисточнике

        model.addAttribute("user", userService.findById(id).orElseThrow(NotFoundException::new));
        return "user_form";

    }


    // вот как было до наследования от JPARepository (getPassword):
   /* @PostMapping("/update")
    public String update(@Valid UserRepr user, BindingResult result){

        logger.info("Update endpoint requested");

        if (result.hasErrors()){
            return "user_form";
        }
        if (user.getId()!=null){
            logger.info("Updating user with id {}");
            userService.update(user);
        }  else {
            logger.info("creating a new user");
            userService.save(user);
        }
        return "redirect:/user";

    }*/


    @PostMapping("/update")
    public String update(@Valid UserRepr user, BindingResult result){

        logger.info("Update endpoint requested");

        if (result.hasErrors()){
            return "user_form";
        }

        logger.info("Updating user with id {}");
        userService.save(user);

        return "redirect:/user";

    }

    // было:
/*    @GetMapping("/new")
    public String create(Model model) {
    logger.info("new user request");

    model.addAttribute("user",new User());
    return "user_form";

    }*/


    @GetMapping("/new")
    public String create(Model model) {
        logger.info("new user request");

        model.addAttribute("user",new UserRepr());
        return "user_form";

    }



    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") Long id ){
        logger.info("delete request");

        userService.delete(id);
        return "redirect:/user";
    }
        // метод для того, чтобы наше исключение NotFoundException  что-то выводило. а не пустой экран:
    @ExceptionHandler
     public ModelAndView notFoundExceptionHandler(NotFoundException ex){

        ModelAndView mav = new ModelAndView("not_found");// в качестве параметра - имя представления,
        // которое мы будем выводить
         mav.setStatus(HttpStatus.NOT_FOUND);       // указываем статус ошибки
        return mav;                     // возвращаем имя представления, которое должно отобразиться,
        // если возникло данное исключение

     }

}
