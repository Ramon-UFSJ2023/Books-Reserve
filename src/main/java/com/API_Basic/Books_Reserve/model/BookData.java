package com.API_Basic.Books_Reserve.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BookData {
    private String name;
    private int ID;
    private boolean disponivel;
    private String author;
}
