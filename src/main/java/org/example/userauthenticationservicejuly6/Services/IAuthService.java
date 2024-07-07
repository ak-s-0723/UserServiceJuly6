package org.example.userauthenticationservicejuly6.Services;

import org.antlr.v4.runtime.misc.Pair;
import org.example.userauthenticationservicejuly6.Models.User;
import org.springframework.util.MultiValueMap;

public interface IAuthService {
    User signup(String email,String password);

    Pair<User, MultiValueMap<String,String>> login(String email, String password);

    Boolean validateToken(String userId,String token);
}
