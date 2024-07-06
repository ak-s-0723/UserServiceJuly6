package org.example.userauthenticationservicejuly6.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User extends BaseModel {
    private String email;

    private String password;

    //0 -> ADMIN
    //1 -> SELLER
    //2 -> BUYER
    @Enumerated(EnumType.ORDINAL)
    private Role role;
}
