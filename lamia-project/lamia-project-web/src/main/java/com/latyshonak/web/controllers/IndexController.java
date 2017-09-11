package com.latyshonak.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping(value = "/index.jsp")
    public String indexReturn () {
        return "redirect:index";
    }
}
