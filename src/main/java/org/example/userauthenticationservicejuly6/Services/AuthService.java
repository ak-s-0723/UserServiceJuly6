package org.example.userauthenticationservicejuly6.Services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.antlr.v4.runtime.misc.Pair;
import org.example.userauthenticationservicejuly6.Models.Role;
import org.example.userauthenticationservicejuly6.Models.Session;
import org.example.userauthenticationservicejuly6.Models.SessionStatus;
import org.example.userauthenticationservicejuly6.Models.User;
import org.example.userauthenticationservicejuly6.Repositories.SessionRepo;
import org.example.userauthenticationservicejuly6.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SessionRepo sessionRepo;

    @Autowired
    private SecretKey secretKey;

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
    public Pair<User,MultiValueMap<String,String>> login(String email, String password) {
        Optional<User> optionalUser = userRepo.findByEmail(email);
        //User has never signed up
        if(optionalUser.isEmpty()) {
            return null;
        }

        //User has passed wrong password while logging
        if(!bCryptPasswordEncoder.
                matches(password,optionalUser.get().getPassword())) {
            return null;
        }

        //Token Generation

//        String message = "{\n" +
//                "   \"email\": \"anurag@gmail.com\",\n" +
//                "   \"roles\": [\n" +
//                "      \"instructor\",\n" +
//                "      \"buddy\"\n" +
//                "   ],\n" +
//                "   \"expirationDate\": \"10thJuly2024\"\n" +
//                "}";
//
//        byte[] content = message.getBytes(StandardCharsets.UTF_8);

        Map<String,Object> claims = new HashMap<>();
        claims.put("user email",optionalUser.get().getEmail());
        claims.put("user roles",optionalUser.get().getRole());
        Long nowInMillis = System.currentTimeMillis();
        claims.put("iat",nowInMillis);
        claims.put("exp",nowInMillis+1000000);


        String token = Jwts.builder().claims(claims).signWith(secretKey).compact();

        MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
        headers.add(HttpHeaders.SET_COOKIE,token);

        Session session = new Session();
        session.setUser(optionalUser.get());
        session.setToken(token);
        session.setSessionStatus(SessionStatus.ACTIVE);
        sessionRepo.save(session);

        return new Pair<User,MultiValueMap<String,String>>(optionalUser.get(),headers);
    }

    @Override
    public Boolean validateToken(String userId, String token) {
       Optional<Session> session = sessionRepo.findByToken(token);
       if(session.isEmpty()) {
           return false;
       }

        JwtParser jwtParser = Jwts.parser().verifyWith(secretKey).build();
        Claims claims = jwtParser.parseSignedClaims(token).getPayload();

        Long expiry = (Long) claims.get("exp");

        System.out.println(expiry);
        System.out.println(System.currentTimeMillis());

        if(System.currentTimeMillis() > expiry) {
            //set session state as expired
            return false;
        }

        //More checks on User -> HW

        return true;
    }
}
