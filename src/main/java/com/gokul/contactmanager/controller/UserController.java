package com.gokul.contactmanager.controller;


import com.gokul.contactmanager.dao.UserRepository;
import com.gokul.contactmanager.enities.Contact;
import com.gokul.contactmanager.enities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @ModelAttribute
    public void addCommonData(Model m,Principal principal){
        String userName=principal.getName();
        System.out.println("Username "+userName);
        User user=userRepository.getUserByUserName(userName);
//        get the user using email(username)
        System.out.println("user "+user);
        m.addAttribute("user",user);
//
    }


    @RequestMapping("/dashboard")
    public String dashboard(Model model, Principal principal){
        model.addAttribute("title","User Dashboard");
        return "normal/user_dashboard";
    }

//    open add form handler
    @GetMapping("/add-contact")
    public String openAddContactForm(Model model){

        model.addAttribute("title","Add Contact");
        model.addAttribute("contact",new Contact());

        return "normal/add_contact_form";
    }

}
