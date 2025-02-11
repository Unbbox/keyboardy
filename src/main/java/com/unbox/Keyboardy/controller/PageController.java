package com.unbox.Keyboardy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j2;


@Log4j2
@Controller
public class PageController {
    
    @GetMapping("/")
    public String getHome() {
        log.info("홈 화면 요청");
        return "index";
    }

    

    
    
}
