package com.API_Basic.Books_Reserve.service;
import com.API_Basic.Books_Reserve.model.BookData;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceApplication {
    private List<BookData> listOfBooks = new ArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String JSON_BOOK_PATH = "classpath:SavesJSON/booksSaves.json";

    @Autowired
    private ResourceLoader resourceLoader;

    @PostConstruct
    public void readBooks(){
        try {
            Resource resource = resourceLoader.getResource(JSON_BOOK_PATH);
            InputStream inputStream = resource.getInputStream();
            if(inputStream.available() >0){
                this.listOfBooks = objectMapper.readValue(inputStream, new TypeReference <List<BookData>>(){});
            }
        }catch (IOException e){
            System.err.println("Erro ao ler livros do JSON "+ e.getMessage());
        }
    }
    private void saveBooks(){
        try{
            Resource resource = resourceLoader.getResource(JSON_BOOK_PATH);
            File file = resource.getFile();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, this.listOfBooks);
        }catch (IOException e){
            System.err.println("Erro ao salvar no arquivo JSON "+ e.getMessage());
        }
    }

    public BookData createBook(BookData newBook){
        newBook.setID(listOfBooks.stream().mapToInt(BookData::getID).max().orElse(0)+1);
        listOfBooks.add(newBook);
        saveBooks();
        return newBook;
    }

    public Optional<BookData> getBookByID(int id){
        return listOfBooks.stream().filter(SearchId -> SearchId.getID() == id).findFirst();
    }

    public void deleteBookByID(int id){
        getBookByID(id).ifPresent(bookData -> listOfBooks.remove(bookData));
        saveBooks();
    }


}
