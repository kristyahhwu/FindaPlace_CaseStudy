package org.yinyinwu.findaplace.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.yinyinwu.findaplace.dto.UserDTO;
import org.yinyinwu.findaplace.exceptions.AuthencationException;
import org.yinyinwu.findaplace.model.Role;
import org.yinyinwu.findaplace.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
// import org.yinyinwu.findaplace.service.RoleService;
import org.yinyinwu.findaplace.service.UserServiceImpl;

import java.util.List;

// logic to manage user accounts

@Controller
public class UserController {


    private UserServiceImpl userService;

    private RedirectAttributes redirectAttributes;
    // private RoleService roleService;

    Logger logger = LoggerFactory.getLogger(LocationController.class);

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    // display a list of users
    @GetMapping("/profile")
    public String listAllUsers(Model model){
        List<User> profiles = userService.listAllUsers();
        model.addAttribute("listProfile", profiles);
        return "profile";
    }

    // register form for new users
    @GetMapping("/register")
    public String registerForm(Model model){
        UserDTO user = new UserDTO();
        user.setLoggedin(true);
        model.addAttribute("user", user);
        return "register";
    }

    // add new user
    @GetMapping("/profile/add")
    public String addUser(Model model){
        UserDTO user = new UserDTO();
       // List<Role> listAllRoles = userService.listAllUsers();
        user.setLoggedin(true);

        model.addAttribute("user", user);
       // model.addAttribute("listAllRoles", listAllRoles);
        return "profile";
    }

    // save user and redirect to profile page
    @PostMapping("/profile/save")
    public String saveUser(User user, RedirectAttributes redirectAttributes){
        userService.saveUser(user);
        redirectAttributes.addFlashAttribute("message", "account created and saved");
        return "redirect:/profile";
    }

    @GetMapping("/profile/delete/{id}")
    public String deleteUser(@PathVariable(name="id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try{
            userService.deleteUser(id);
            redirectAttributes.addFlashAttribute("message", "user deleted");

        } catch (AuthencationException ex) {
            redirectAttributes.addFlashAttribute("message", ex.getMessage());
            return "redirect:/profile";
        }
        return "redirect:/profile";

    }





}
