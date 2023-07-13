package org.yinyinwu.findaplace.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.Param;
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

    @Autowired
    private UserServiceImpl userService;

    private RedirectAttributes redirectAttributes;
    // private RoleService roleService;

    Logger logger = LoggerFactory.getLogger(LocationController.class);

//    @Autowired
//    public UserController(UserServiceImpl userService) {
//        this.userService = userService;
//    }


    // display a list of users
    @GetMapping("/profile")
    public String listAllUsers(Model model){
        List<User> listProfile = userService.listAllUsers();
        model.addAttribute("listProfile", listProfile);
        return "profile";
    }

//    @PostMapping("/login")
//    public String login(){
//        return "redirect:/profile";
//    }

    // add new user
    @GetMapping("/profile/add")
    public String addUser(Model model){
        User user = new User();
        // List<Role> listAllRoles = userService.listAllUsers();
        user.setLoggedin(true);

        model.addAttribute("user", user);
        model.addAttribute("pageTitle", "Create New Account");
        // model.addAttribute("listAllRoles", listAllRoles);
        return "register";
    }

    // register form for new users
    @GetMapping("/register")
    public String registerForm(Model model){
        User user = new User();
        user.setLoggedin(true);
        model.addAttribute("user", user);
        model.addAttribute("pageTitle", "Create New Account");
        return "register";
    }

    // handles login
    @GetMapping("/login")
    public String displayLogin(Model model){
        User user = new User();
        model.addAttribute("user", user);
        System.out.println("creating user login form"); // for debugging
        return "login";
    }

    // save user and redirect to profile page
    @PostMapping("/profile/save")
    public String saveUser(User user, RedirectAttributes redirectAttributes){
        System.out.println(user);
        User savedUser = userService.saveUser(user);
        userService.saveUser(savedUser);
        redirectAttributes.addFlashAttribute("message", "account created and saved");
        return "redirect:/profile";
    }

    //  delete user
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

    // check if email is unique
    @PostMapping("/accounts/check_email")
    public String checkAccountDupe(@Param("id") Integer id, @Param("email") String email) {
        // check if email unique or not
        return userService.isEmailUnique(id, email) ? "OK" : "Duplicated";
    }





}
