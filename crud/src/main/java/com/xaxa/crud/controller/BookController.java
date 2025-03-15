package com.xaxa.crud.controller;

import com.xaxa.crud.model.Book;
import com.xaxa.crud.repository.BookRepository;
import com.xaxa.crud.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> allBooks = bookService.getAllBooks();
        return ResponseEntity.status(HttpStatus.OK).body(allBooks);
    }
    @PostMapping("/books")
    public ResponseEntity<Book> saveBook(@RequestBody Book bookToSave){
        Book savedBook = bookService.saveBook(bookToSave);
        return ResponseEntity.status(HttpStatus.OK).body(savedBook);
    }

    @DeleteMapping("/books/{bookId}")
    public void deleteBookById(@RequestBody Integer bookId){
        Optional<Book> bookToDelete = bookRepository.findById(bookId);
        if (bookToDelete.isPresent()){
            bookService.deleteBookById(bookId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
    }

    @PutMapping("/books/{bookId}")
    public Book updateBook (@PathVariable Integer bookId, Book updatedBook){
        return bookRepository.findById(bookId)
                .map(book -> {
                    book.setName(updatedBook.getName());
                    book.setDescription(updatedBook.getDescription());
                    return bookRepository.save(book);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
    }


}
