package org.yinyinwu.findaplace.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.yinyinwu.findaplace.model.User;

@Controller
public class MainController {

    // return to index page
    @GetMapping("/")
    public String home(){
        System.out.println("going back to home");
        return "index";
    }

    // handles login
    @GetMapping("/login")
    public String login(Model model){
        User user = new User();
        model.addAttribute("user", user);
        System.out.println("creating user login form"); // for debugging
        return "login";
    }

}
