package ru.gb.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody  // это означает  в частности, что ответ будет идти в теге body.
// отключаю при подключении public ResponseEntity<String> hello( @PathVariable String name){  -- потому что возвращаем не тело ответа, а сам ответ
// если подключаем @GetMapping("/hello/{name}")
//    public  String hello(
//            @PathVariable String name    --- то включаем аннотацию.
@RequestMapping("/v1") //общий префикс для всех запросов, которые относятся к этому контроллеру
// т.е. при наборе ....app/v1....  --- всегда будем попадать в этот контроллер
public class HelloBodyController {
  /*  @GetMapping("/hello/{name}")
    public  String hello(
            @PathVariable String name
    //        @RequestParam(name = "name-arc", required = false) String name
    ){
        return "<h1>hello,  "+ name +"!</h1>";
    }*/
  /*  @GetMapping("/hello/{name}")
public ResponseEntity<String> hello( @PathVariable String name){

            return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN).body("<h1>hello,  "+ name +"!</h1>");
}*/
@GetMapping("/hello")
    public String hello(@RequestParam String name){
    return "<h1>hello,  "+ name +"!</h1>";
}  // т.к. стоит аннотация @ResponseBody   - отдаем само тело ответа.
    // т.е. текст, который мы тут напишем, в браузере и покажется. (но мы хотим работать с html,которые у нас в проекте -- см. /v2)


@GetMapping("/home")
public String home(){
        return "home page";
}

}
