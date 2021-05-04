package com.demo.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.demo.dao.ContactRepository;
import com.demo.dao.UserRepository;
import com.demo.entities.Contact;
import com.demo.entities.User;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/index")
    public String dashboard(Model model, Principal principal) {
        model.addAttribute("title", "User-Dashboard");
        return "user_dashboard";
    }

    @GetMapping("/addContact")
    public String addContact(Model model) {
        model.addAttribute("title", "Add_Contact");
        return "add_contact";
    }

    /*
     * @ModelAttribute public void addCommonData(Model model, Principal principal) {
     * 
     * String username = principal.getName(); System.out.println("username::" + username);
     * 
     * User user = userRepository.getUserByUserName(username); System.out.println("user::" + user);
     * model.addAttribute("user", user);
     * 
     * }
     */


    @PostMapping("/registerContact")
    public String registerContact(@ModelAttribute("contact") Contact contact, Model model, HttpSession session, Principal principal) {
        try {
            System.out.println("contact::" + contact);
            String username = principal.getName();
            User user = this.userRepository.getUserByUserName(username);

            contact.setUser(user);
            user.getContacts()
                    .add(contact);

            this.userRepository.save(user);
            session.setAttribute("messege", "Successfully Registered !!");
            return "add_contact";
        } catch (Exception e) {
            session.setAttribute("messege", "something went wrong !!");
            return "add_contact";
        }


    }

    @GetMapping("/showContact")
    public String showContact(Model model, Principal principal) {

        String username = principal.getName();
        User user = this.userRepository.getUserByUserName(username);
        List<Contact> contactList = contactRepository.findContactByUser(user.getUserId());
        model.addAttribute("contactList", contactList);
        model.addAttribute("title", "Show_Contact");
        return "show_contact";
    }


    @GetMapping("/deleteContact/{cid}")
    public String deleteContact(@PathVariable("cid") Integer cid, Model model, Principal principal, HttpSession session) {
        Optional<Contact> contactOptional = this.contactRepository.findById(cid);
        Contact contact = contactOptional.get();
        String username = principal.getName();
        User user = userRepository.getUserByUserName(username);
        if (user.getUserId() == contact.getUser()
                .getUserId()) {
            contact.setUser(null);
            this.contactRepository.delete(contact);
            System.out.println("Contact deleted...");

            session.setAttribute("messege", "Contact deleted successfully.....");
        }

        return "redirect:/user/showContact";
    }


    @PostMapping("/update-contact/{cid}")
    public String openUpdateForm(@PathVariable("cid") Integer cid, Model model) {
        Contact contact = this.contactRepository.findById(cid)
                .get();
        model.addAttribute("title", "Update Contact");
        model.addAttribute("contact", contact);
        return "update_contact";
    }



    @RequestMapping(value = "/process-update", method = RequestMethod.POST)
    public String updateContact(@ModelAttribute("contact") Contact contact, HttpSession session, Principal principal) {
        try {

            Contact oldContactDetails = this.contactRepository.findById(contact.getContactId())
                    .get();
            System.out.println("oldContactDetails::" + oldContactDetails);

            User user = this.userRepository.getUserByUserName(principal.getName());

            contact.setUser(user);

            this.contactRepository.save(contact);
            session.setAttribute("messege", "Your Contact is updated !!");

        } catch (Exception e) {
            session.setAttribute("messege", "something went wrong !!");

        }

        return "redirect:/user/showContact";

    }

}
