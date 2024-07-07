package org.example.userauthenticationservicejuly6.Services;

import org.example.userauthenticationservicejuly6.Models.User;

public interface IAuthService {
    User signup(String email,String password);

    User login(String email,String password);

    Boolean validateToken(String userId,String token);
}
