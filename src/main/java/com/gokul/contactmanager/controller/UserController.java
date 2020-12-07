package com.gokul.contactmanager.controller;


import com.gokul.contactmanager.dao.UserRepository;
import com.gokul.contactmanager.enities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @RequestMapping("/dashboard")
    public String dashboard(Model model, Principal principal){
        String userName=principal.getName();
        System.out.println("Username "+userName);
        User user=userRepository.getUserByUserName(userName);
//        get the user using email(username)
        System.out.println("user "+user);
        model.addAttribute("user",user);
        return "normal/user_dashboard";
    }

}
