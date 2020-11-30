package com.gokul.contactmanager.controller;


import com.gokul.contactmanager.dao.UserRepository;
import com.gokul.contactmanager.enities.User;
import com.gokul.contactmanager.helper.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
public class HomeController {


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


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
    public Object registerUser(@Valid @ModelAttribute("user") User user,BindingResult result1, @RequestParam(value="agreement",defaultValue = "false") boolean agreement, Model model, HttpSession session){

        try{
            if(!agreement){

                throw new Exception("please agree terms and conditions!");
            }

            else if(result1.hasErrors()){
                System.out.println(result1.toString());
                model.addAttribute("user",user);
                return "signup";
            }
            else {
                user.setRole("ROLE_USER");
                user.setEnabled(true);
                user.setPassword(passwordEncoder.encode(user.getPassword()));


                User result = this.userRepository.save(user);
                System.out.println(result);

                model.addAttribute("user", user);
                session.setAttribute("message", new Message("registration successful", "alert-success"));
                return "signup";
            }

        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("user",user);
            session.setAttribute("message",new Message("something went wrong :"+e.getMessage(),"alert-danger"));
            return "signup";
        }


    }


//    handler for custom login
    @GetMapping("/login")
    public String customLogin(Model model){
        model.addAttribute("title","Login page");
        return "login";
    }
}
