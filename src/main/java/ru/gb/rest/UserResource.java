package ru.gb.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.controler.BadRequestException;
import ru.gb.controler.NotFoundException;
import ru.gb.service.UserRepr;
import ru.gb.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

// Этот класс будет контроллером, который будет использоваться для РЕСт Сервисов
@RestController
@RequestMapping("/api/v1/users")
public class UserResource {

    private final UserService userService;



    @Autowired  // инжектим через параметры конструктора
    public UserResource(UserService userService) {
        this.userService = userService;
    }
    @GetMapping(path="/all", produces="application/json") // application/json -- дефолтное значение , но просто в явном виде пропишем, каким образом указывается формат, в котором эта информация подается
    public List<UserRepr> findAll(){
        return userService.findAll().stream()
                .peek(u ->u.setPassword(null))
                .collect(Collectors.toList());   // мы можем передать пароль для записи, но не будем его видеть при отображении
    }


    @GetMapping(path="/{id}") // в РЕСТ-сервисах ГЕТ параметры тоже используются ,
    // но для таких параметров как идентификтор сущности чаще испольуется часть ЮРЛ
    public UserRepr findById(@PathVariable("id") Long id) {
        UserRepr userRepr = userService.findById(id)
                .orElseThrow(NotFoundException::new);
                userRepr.setPassword(null);
            return userRepr;    // мы можем передать пароль для записи, но не будем его видеть при отображении

    }

    @PostMapping(consumes="application/json")  // потому что создаем, а не изменяем (когда изменяем : put)
    // формат такой по умолчанию, но мы просто так напишем
    public UserRepr create(@RequestBody UserRepr userRepr){

        if (userRepr.getId() !=null){
            throw new BadRequestException();
        }
        userService.save(userRepr);
        return userRepr;

    }

    @PutMapping(consumes="application/json")
    public void update(@RequestBody UserRepr userRepr){
        if (userRepr.getId() == null){
            throw new BadRequestException();
        }

        userService.save(userRepr);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        userService.delete(id);

    }

    @ExceptionHandler
    public ResponseEntity<String> notFoundException(NotFoundException ex){
     return new ResponseEntity<>("Entity not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<String> badRequestException(BadRequestException ex){
        return new ResponseEntity<>("Bad request", HttpStatus.NOT_FOUND);
    }


}
