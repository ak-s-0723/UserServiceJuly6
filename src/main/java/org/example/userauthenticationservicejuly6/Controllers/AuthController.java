package org.example.userauthenticationservicejuly6.Controllers;

import org.example.userauthenticationservicejuly6.Dtos.SignupRequestDto;
import org.example.userauthenticationservicejuly6.Dtos.UserDto;
import org.example.userauthenticationservicejuly6.Models.User;
import org.example.userauthenticationservicejuly6.Services.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private UserDto from(User user) {
        UserDto userDto = new UserDto();
        userDto.setRole(user.getRole());
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
