package com.minakov.services;

import com.minakov.entities.Book;
import com.minakov.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Controller
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public boolean existsById(Long id){
        return bookRepository.existsById(id);
    }

    public Book getBookById(Long id) {
        Book book = bookRepository.getById(id);
        if (book == null) {
            throw new EntityNotFoundException();
        }
        return book;
    }

    public void createBook(Book book) {
        bookRepository.save(book);
    }
}
