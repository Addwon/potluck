package com.assignments.potluck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    //Add items to user
   @RequestMapping("additems/{id}")
    public String addItem(@PathVariable("id") long id,Model model)
    {
        User user=userRepository.findOne(id);
        Item item=new Item();
        item.setUser(user);
        item.setUname(user.getFirstName());
        //item.getUser().setId(id);

        /*item.setItemName("Coffee");
        item.setServes(10);
        item.setItemType("Drink");*/
        itemRepository.save(item);
        model.addAttribute("thisuser",userRepository.findOne(id));
        model.addAttribute("item",itemRepository.findAll());
        return "redirect:/additems";
    }
  //Testing add items
   @GetMapping("/additems")
    public String addMovie(Model model)
    {
        model.addAttribute("item",new Item());
        model.addAttribute("user",userRepository.findAll());
        //model.addAttribute("user",new User());
        return "additems";
    }
    @PostMapping("/additems")
    public String saveMovie(@ModelAttribute("item") Item item, BindingResult result, Model model)
    {
        if(result.hasErrors())
        {
            return "additems";
        }
        itemRepository.save(item);
        userRepository.save(item.getUser());
        return "redirect:/";
    }

   /* @GetMapping("/additems/{id}")
    public String addActorToMovie(Model model, @PathVariable("id") long id)
    {
        System.out.println("user Id: "+id);
        model.addAttribute("item",itemRepository.findAll());
        model.addAttribute("user",userRepository.findOne(id));
        return "additems";
    }*/
    /*@RequestMapping("additems")
    public String addItem(Model model)
    {
        model.addAttribute("user",userRepository.findAll());
        return "additems";
    }*/
    //.................................................................
/*
    @RequestMapping(value="/additems",method= RequestMethod.GET)
    public String showItemsPage(Model model){
        model.addAttribute("item",new Item());
        return "additems";
    }
    @RequestMapping(value="/additems",method= RequestMethod.POST)
    public String processAddItemsPage(@Valid @ModelAttribute("item") Item item, BindingResult result){

        if(result.hasErrors()){
            return "additems";
        }else{
            *//*User user=userRepository.findOne(id);
            //item.getUser().setId(id);
            item.setUser(user);
            System.out.println("User id: "+id);
            //System.out.println(user.getFirstName());
            //item.setUname(user.getFirstName());*//*
            itemRepository.save(item);
        }
        return "redirect:/";
    }*/
    //--------------------------------------------//------------------------------------------------------
    @RequestMapping("/users")
    public String usersList(Model model)
    {        model.addAttribute("user",userRepository.findAll());
        return "users";
    }
    @RequestMapping("/privileges/{id}")
    public String grantPrivileges(@PathVariable("id") long id,Model model, User user,Role role)
    {
        user=userRepository.findOne(id);
        role=roleRepository.findOne(id); //??
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
