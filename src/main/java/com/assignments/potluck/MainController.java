package com.assignments.potluck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class MainController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ItemRepository itemRepository;

    @RequestMapping("/login")
    public String login(){return "login";}

    @RequestMapping("/")
    public String showIndex(Model model)
    {        model.addAttribute("item",itemRepository.findAll());
        return "index";
    }

    //User registration
    @RequestMapping(value="/registration",method= RequestMethod.GET)
    public String showRegistrationPage(Model model){
        model.addAttribute("user",new User());
        return "registration";
    }
    @RequestMapping(value="/registration",method= RequestMethod.POST)
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult result){

        if(result.hasErrors()){
            return "registration";
        }else{
            userRepository.save(user);
        }
        return "redirect:/";
    }

    //Pledge items
    @RequestMapping(value="/pledgeitems",method= RequestMethod.GET)
    public String showPledgeItemsPage(Model model){
        model.addAttribute("item",new Item());
        return "pledgeitems";
    }
    @RequestMapping(value="/pledgeitems",method= RequestMethod.POST)
    public String processPledgeItemsPage(@Valid @ModelAttribute("item") Item item, BindingResult result){

        if(result.hasErrors()){
            return "pledgeitems";
        }else{
            itemRepository.save(item);
        }
        return "redirect:/";
    }

    @RequestMapping("/user")
    public String usersList(Model model)
    {        model.addAttribute("user",userRepository.findAll());
        return "users";
    }

    @RequestMapping("/privileges/{id}")
    public String grantPrivileges(@PathVariable("id") long id,Model model, User user)
    {
        user=userRepository.findOne(id);
        Role role=roleRepository.findOne(id); //??
        role.setRole("ADMIN");
        roleRepository.save(role);
        user.setUserRole("ADMIN");
        userRepository.save(user);
        model.addAttribute("user",userRepository.findOne(id));
        return "users";
    }

    @RequestMapping("/remove/{id}")
    public String removeItems(@PathVariable("id") long id,Model model, Item item)
    {
        itemRepository.delete(id);
        model.addAttribute("item",itemRepository.findAll());
        return "index";
    }
}
