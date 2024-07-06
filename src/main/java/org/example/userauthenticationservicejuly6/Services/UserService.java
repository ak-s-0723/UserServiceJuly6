package org.example.userauthenticationservicejuly6.Services;

import org.example.userauthenticationservicejuly6.Models.User;
import org.example.userauthenticationservicejuly6.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public User getUserById(Long id) {
        Optional<User> userOptional = userRepo.findById(id);

        if(userOptional.isPresent()) {
            return userOptional.get();
        }

        return null;
    }
}
