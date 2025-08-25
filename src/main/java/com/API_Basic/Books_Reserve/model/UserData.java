package com.API_Basic.Books_Reserve.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Setter
@Getter
public class UserData {
    private String name;
    private String email;
    private int password;
    private int id;
    private final boolean adm;

}
