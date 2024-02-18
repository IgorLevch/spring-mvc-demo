package ru.gb.api.product.controllers;

import org.springframework.web.bind.annotation.*;
import ru.gb.api.product.data.Product;
import ru.gb.api.product.dto.ResultDto;
import ru.gb.api.product.services.ProductService;

import java.util.List;

@RestController
public class MainController {
// контроллер - это верхний слой. Как правило, взаимодействует с Сервисным слоем, а не с репозиторием.

    @GetMapping("calc/add")
    public ResultDto calculateAdd(@RequestParam Integer a,@RequestParam Integer b){

        return  new ResultDto(a+b);

    }




}
