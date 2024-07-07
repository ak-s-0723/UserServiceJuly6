package org.example.userauthenticationservicejuly6.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {
    private String email;
    private String password;
}
