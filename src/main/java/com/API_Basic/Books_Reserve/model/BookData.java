package com.API_Basic.Books_Reserve.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookData {
    private String name;
    private int ID;
    private boolean disponivel;
    private String author;
}
