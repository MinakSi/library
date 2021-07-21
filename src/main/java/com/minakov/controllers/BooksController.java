package com.minakov.controllers;


import com.minakov.entities.Book;
import com.minakov.services.AuthorService;
import com.minakov.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookService bookService;
    private final AuthorService authorService;

    @Autowired
    public BooksController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping()
    public String showAll(HttpSession session, Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "customer/books/bookList";
    }

    @PostMapping()
    public String createNewBook(HttpSession session, @ModelAttribute Book book) {
        bookService.createBook(book);
        return "customer/books/success";
    }

    @GetMapping("/{id}")
    public String showDetails(HttpSession session, Model model, @PathVariable("id") Long bookId) {
        if(!bookService.existsById(bookId)){
            return "redirect:/books";
        }
        model.addAttribute("book", bookService.getBookById(bookId));
        return "customer/books/about";
    }
}