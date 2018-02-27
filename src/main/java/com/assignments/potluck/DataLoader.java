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

        user=new User("user@user.com","password","User","User",true,"user");
        user.setRoles(Arrays.asList(adminRole));
        userRepository.save(user);

        //Item 1
        Item item=new Item();
        item.setItemName("Pizza");
        item.setServes(2);
        item.setItemType("Food");
        itemRepository.save(item);

        //Item 2
        item=new Item();
        item.setItemName("Juice");
        item.setServes(2);
        item.setItemType("Drink");
        itemRepository.save(item);
    }
}
