package org.example.userauthenticationservicejuly6.Repositories;

import org.example.userauthenticationservicejuly6.Models.Role;
import org.example.userauthenticationservicejuly6.Models.Status;
import org.example.userauthenticationservicejuly6.Models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserRepoTest {

    @Autowired
    private UserRepo userRepo;

    @Test
    void persistUsersToDb() {
        User user1 = new User();
        user1.setEmail("AnuragKhanna@gmail.com");
        user1.setPassword("ak");
        user1.setRole(Role.ADMIN);
        user1.setStatus(Status.ACTIVE);
        userRepo.save(user1);

        User user2 = new User();
        user2.setEmail("anushkasharma@outlook.com");
        user2.setPassword("ak00000");
        user2.setRole(Role.BUYER);
        user2.setStatus(Status.ACTIVE);
        userRepo.save(user2);

        User user3 = new User();
        user3.setEmail("AishwaryaRai@Aish.com");
        user3.setPassword("ar");
        user3.setRole(Role.BUYER);
        user3.setStatus(Status.ACTIVE);
        userRepo.save(user3);

        User user4 = new User();
        user4.setEmail("RanbirKapoor@RK.com");
        user4.setPassword("RK");
        user4.setRole(Role.SELLER);
        user4.setStatus(Status.ACTIVE);
        userRepo.save(user4);

        User user5 = new User();
        user5.setEmail("KJo@gmail.com");
        user5.setPassword("kjo");
        user5.setRole(Role.SELLER);
        user5.setStatus(Status.INACTIVE);
        userRepo.save(user5);
    }
}