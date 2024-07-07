package org.example.userauthenticationservicejuly6.Services;

import org.example.userauthenticationservicejuly6.Models.Role;
import org.example.userauthenticationservicejuly6.Models.User;
import org.example.userauthenticationservicejuly6.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User signup(String email, String password) {
        Optional<User> userOptional = userRepo.findByEmail(email);
        if(userOptional.isPresent()) {
            return userOptional.get();
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setRole(Role.BUYER);
        userRepo.save(user);

        return user;
    }

    @Override
    public User login(String email, String password) {
        return null;
    }

    @Override
    public Boolean validateToken(String userId, String token) {
        return null;
    }
}
