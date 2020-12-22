package com.example.wis.comtroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class MainController {
    @GetMapping("/")
    public String index(){
        return "redirect:product";
    }

    @GetMapping("/upload")
    public String uploadPage(Map<String, Object> model) {
        return "upload";
    }
}
