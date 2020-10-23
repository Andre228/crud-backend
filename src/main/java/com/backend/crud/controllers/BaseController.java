package com.backend.crud.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Андрей on 22.09.2020.
 */
public abstract class BaseController {

    ResponseEntity<?> index(@RequestParam() Long id){
        return null;
    };
    void create(){};
    void store(){};
    void edit(){};
    void update(){};
    void destroy(){};

}
