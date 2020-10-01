package com.backend.crud.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Андрей on 22.09.2020.
 */
public interface BaseController {

    ResponseEntity<?> index(@RequestParam() Long id);
    void create();
    void store();
    void edit();
    void update();
    void destroy();

}
