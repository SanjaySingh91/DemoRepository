package com.demo.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.demo.dao.UserRepository;
import com.demo.entities.User;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Home- My Application");
        return "home";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("title", "Register-My Application");
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model, HttpSession session) {
        try {
            if ("active".equals(user.getStatus()))
                user.setRole("ROLE_USER");
            else
                user.setRole("ROLE_ADMIN");

            if (null != user.getPassword() && "" != user.getPassword()) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                System.out.println("user::" + user);
            }

            User result = userRepository.save(user);
            model.addAttribute("user", result);
            session.setAttribute("messege", "Successfully Registered !!");
            return "signup";
        } catch (Exception e) {
            model.addAttribute("user", user);
            session.setAttribute("messege", "something went wrong !!");
            return "signup";
        }


    }

    @GetMapping("/showAllUsers")
    public String showUsers(Model model) {

        List<User> userList = userRepository.findAll();
        model.addAttribute("userList", userList);
        return "showuser";
    }

    /*
     * @GetMapping("/searchUser/{search}") public String showUserByName(@PathVariable("search")
     * String search, Model model) {
     * 
     * System.out.println("name::"+search); List<User> userList =
     * userRepository.getAllUserByUserName("active",search);
     * 
     * model.addAttribute("userList",userList); System.out.println("userList::"+userList);
     * //model.addAttribute("search", studentService.listStudentsBySurname(name)); return
     * "showuser"; }
     */

    @PostMapping("/searchUser")
    public String showUserByName(@ModelAttribute("user") User user, Model model) {

        System.out.println("name::" + user.getUsername());
        List<User> userList = userRepository.getAllUserByUserName("active", user.getUsername());

        model.addAttribute("userList", userList);
        System.out.println("userList::" + userList);
        // model.addAttribute("search", studentService.listStudentsBySurname(name));
        return "showuser";
    }



    @GetMapping("/signin")
    public String userLogin(Model model) {
        model.addAttribute("title", "Login - My Application");
        model.addAttribute("user", new User());
        return "login";
    }

}
