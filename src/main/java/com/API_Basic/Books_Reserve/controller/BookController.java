package com.API_Basic.Books_Reserve.controller;

import com.API_Basic.Books_Reserve.model.BookData;
import com.API_Basic.Books_Reserve.service.BookServiceApplication;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class BookController {

    private final BookServiceApplication bookServiceApplication;

    @GetMapping
    public ResponseEntity<List<BookData>> getAllBooks(){
        List<BookData> listBook = bookServiceApplication.getAllBooks();
        return ResponseEntity.ok(listBook);
    }
    @GetMapping("/{id}")
    public ResponseEntity<BookData> getBookByID(@PathVariable int id){
        Optional<BookData> FindBook = bookServiceApplication.getBookByID(id);
        return  FindBook.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/post")
    public ResponseEntity<BookData> createBook(@RequestBody BookData newBook){
        BookData bookNew = bookServiceApplication.createBook(newBook);
        return ResponseEntity.ok(bookNew);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<BookData> updateBook(@PathVariable int id, @RequestBody BookData newBook){
        Optional<BookData> oldBook = bookServiceApplication.updateBookByID(id, newBook);
        return oldBook.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<BookData> deleteBook(@PathVariable int id){
        return bookServiceApplication.deleteBookByID(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/reserve/{name}")
    public ResponseEntity<BookData> reservationBook(@PathVariable String name){
        return bookServiceApplication.reserveBookByName(name).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/reserve/devolution/{name}")
    public ResponseEntity<BookData> devolutionBook(@PathVariable String name){
        return bookServiceApplication.devolutionBookByName(name).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


}
