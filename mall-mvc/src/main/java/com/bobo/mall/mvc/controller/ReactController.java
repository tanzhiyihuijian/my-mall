package com.bobo.mall.mvc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/react")
public class ReactController {


    @GetMapping("/demo1")
    public ModelAndView reactDemo1() {
        return new ModelAndView("react/demo1");
    }


}
