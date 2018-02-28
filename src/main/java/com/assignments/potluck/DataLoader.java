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

        User user1=new User("admin@admin.com","password","Dave","Wolf",true,"DaveWolf");
        user1.setRoles(Arrays.asList(adminRole));
        user1.setUserRole(adminRole.getRole());
        userRepository.save(user1);

        User user2=new User("user@user.com","password","Addis","Wondie",true,"user");
        user2.setRoles(Arrays.asList(userRole));
        user2.setUserRole(userRole.getRole());
        userRepository.save(user2);

        User user3 = new User("bob@bob.com", "password", "Bob", "Marley", true, "BobMarley");
        user3.setRoles(Arrays.asList(userRole));
        user3.setUserRole(userRole.getRole());
        userRepository.save(user3);

        //Item 1
        Item item1=new Item();
        item1.setItemName("Pizza");
        item1.setServes(2);
        item1.setItemType("Food");
        item1.setUser(user2);
        item1.setUname(user2.getFirstName());
        itemRepository.save(item1);

        //Item 2
        Item item2=new Item();
        item2.setItemName("Juice");
        item2.setServes(2);
        item2.setItemType("Drink");
        item2.setUser(user3);
        item2.setUname(user3.getFirstName());
        itemRepository.save(item2);

        /*user.getItems().add(item1);
        user.getItems().add(item2);
        userRepository.save(user);*/
    }
}
