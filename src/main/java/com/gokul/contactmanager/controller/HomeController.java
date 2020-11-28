package com.gokul.contactmanager.controller;


import com.gokul.contactmanager.dao.UserRepository;
import com.gokul.contactmanager.enities.User;
import com.gokul.contactmanager.helper.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
public class HomeController {


    @Autowired
    private UserRepository userRepository;


    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("title","Home page");
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model){
        model.addAttribute("title","About page");
        return "about";
    }

    @GetMapping("/signup")
    public String signup(Model model){
        model.addAttribute("title","SignUp page");
        model.addAttribute("user",new User());
        return "signup";
    }

    //this handler for regitering user
    @RequestMapping(value = "/do_register",method= RequestMethod.POST)
    public String registerUser(@ModelAttribute("user") User user, @RequestParam(value="agreement",defaultValue = "false") boolean agreement, Model model, HttpSession session){

        try{
            if(!agreement){

                throw new Exception("please agree terms and conditions!");
            }
            user.setRole("ROLE_USER");
            user.setEnabled(true);

            System.out.println(agreement);
            System.out.println(user);
            System.out.println(model);
            User result=this.userRepository.save(user);
            System.out.println(result);

            model.addAttribute("user",new User());
            session.setAttribute("message",new Message("registration successful","alert-success"));
            return "signup";

        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("user",user);
            session.setAttribute("message",new Message("Server error","alert-danger"));
            return "signup";
        }


    }

}
