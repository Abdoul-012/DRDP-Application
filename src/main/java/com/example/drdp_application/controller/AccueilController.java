package com.example.drdp_application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")

public class AccueilController {
    @GetMapping("/accueil")
    public String index() {
        return "accueil";
    }
}
