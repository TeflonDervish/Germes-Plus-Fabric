package ru.semenov.germesplusfabric.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Main {

    @GetMapping
    public String index() {
        return "redirect:/login";
    }
}
