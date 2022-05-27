package com.sw.filterwatermark.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Suaxi
 * @date 2022/5/27 17:25
 */
@RestController
@RequestMapping("/test")
public class IndexController {

    @GetMapping("/demo")
    public String demo() {
        return "demo";
    }
}
