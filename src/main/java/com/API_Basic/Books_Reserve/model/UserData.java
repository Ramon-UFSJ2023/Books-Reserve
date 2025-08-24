package com.API_Basic.Books_Reserve.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class UserData {
    private String userName;
    private String email;
    private int password;
    private int ID;
    private final boolean ADM;
}
