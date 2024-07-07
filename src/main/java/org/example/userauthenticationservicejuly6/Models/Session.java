package org.example.userauthenticationservicejuly6.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Session extends BaseModel {
    @ManyToOne
    private User user;

    private String token;

    @Enumerated(EnumType.ORDINAL)
    private SessionStatus sessionStatus;
}

//1        m
//user   sesion
//1        1
