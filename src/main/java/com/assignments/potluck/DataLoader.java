package com.assignments.potluck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ItemRepository itemRepository;

    @Override
    public void run(String... strings) throws Exception{
       System.out.println("Loading data . . .");
       roleRepository.save(new Role("USER"));
       roleRepository.save(new Role("ADMIN"));

        Role adminRole=roleRepository.findByRole("ADMIN");
        Role userRole=roleRepository.findByRole("USER");

        User user=new User("admin@admin.com","password","Dave","Wolf",true,"DaveWolf");
        user.setRoles(Arrays.asList(adminRole));
        userRepository.save(user);

        user=new User("user@user.com","password","Addis","Wondie",true,"user");
        user.setRoles(Arrays.asList(adminRole));
        userRepository.save(user);

        //Item 1
        Item item1=new Item();
        item1.setItemName("Pizza");
        item1.setServes(2);
        item1.setItemType("Food");
        item1.setUser(user);
        item1.setUname(user.getFirstName());
        itemRepository.save(item1);

        //Item 2
        Item item2=new Item();
        item2.setItemName("Juice");
        item2.setServes(2);
        item2.setItemType("Drink");
        item2.setUser(user);
        item2.setUname(user.getFirstName());
        itemRepository.save(item2);

        /*user.getItems().add(item1);
        user.getItems().add(item2);
        userRepository.save(user);*/
    }
}
