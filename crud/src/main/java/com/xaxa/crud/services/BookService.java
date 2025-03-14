package com.xaxa.crud.services;

import com.xaxa.crud.model.Book;
import com.xaxa.crud.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Integer id){
        return bookRepository.findById(id);
    }

    public Optional<Book> updateBook(Integer id, Book updatedBook){
        return bookRepository.findById(id)
                .map(book -> {
                book.setName(updatedBook.getName());
                book.setDescription(updatedBook.getDescription());
                book.setRate(updatedBook.getRate());
                book.setAuthor(updatedBook.getAuthor());
                book.setUrlImage(updatedBook.getUrlImage());
                book.setCategory(updatedBook.getCategory());
                return bookRepository.save(book);
        });
    }

    public Book saveBook(Book book){
        return bookRepository.save(book);
    }

    public void deleteBookById(Integer id){
        bookRepository.deleteById(id);
    }
}
