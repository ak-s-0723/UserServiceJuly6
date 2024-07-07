package org.example.userauthenticationservicejuly6.Controllers;

import org.antlr.v4.runtime.misc.Pair;
import org.example.userauthenticationservicejuly6.Dtos.LoginRequestDto;
import org.example.userauthenticationservicejuly6.Dtos.SignupRequestDto;
import org.example.userauthenticationservicejuly6.Dtos.UserDto;
import org.example.userauthenticationservicejuly6.Dtos.ValidateTokenRequestDto;
import org.example.userauthenticationservicejuly6.Models.User;
import org.example.userauthenticationservicejuly6.Services.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IAuthService authService;

    @PostMapping("/signup")
    public UserDto signup(@RequestBody SignupRequestDto signupRequestDto) {
        User user = authService.signup(signupRequestDto.getEmail(),signupRequestDto.getPassword());
        return from(user);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        Pair<User, MultiValueMap<String,String>> userWithHeaders =
                authService.login(loginRequestDto.getEmail(),
                        loginRequestDto.getPassword());

        if(userWithHeaders == null) {
            throw new RuntimeException("invalid credentials or user has not signed in");
        }
        User user = userWithHeaders.a;
        return new ResponseEntity<>(from(user),userWithHeaders.b, HttpStatus.OK);
    }

    @PostMapping("/validate")
    public Boolean validateToken(@RequestBody ValidateTokenRequestDto validateTokenRequestDto)
    {
        return authService.validateToken(null, validateTokenRequestDto.getToken());
    }

    private UserDto from(User user) {
        UserDto userDto = new UserDto();
        userDto.setRole(user.getRole());
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
