package org.example.userauthenticationservicejuly6.Controllers;

import org.example.userauthenticationservicejuly6.Dtos.UserDto;
import org.example.userauthenticationservicejuly6.Models.User;
import org.example.userauthenticationservicejuly6.Services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/{userId}")
    public UserDto getUser(@PathVariable("userId") Long userId) {
      try {
          System.out.println("userId "+userId);
          User user = userService.getUserById(userId);
          if(user == null) {
              throw new RuntimeException("User not found");
          }
          System.out.println("userEmail "+user.getEmail());
          System.out.println("userRole "+user.getRole());

          return from(user);
      }catch(Exception ex) {
         throw ex;
      }
    }

    private UserDto from(User user) {
       UserDto userDto = new UserDto();
       userDto.setRole(user.getRole());
       userDto.setId(user.getId());
       userDto.setEmail(user.getEmail());
       return userDto;
    }
}
