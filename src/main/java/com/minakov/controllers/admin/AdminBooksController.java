package com.minakov.controllers.admin;

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

/**
 * This controller describes actions that should be performed when
 * URL matches /admin/books/**
 * This controller is for users with role 'ADMIN'
 */
@Controller
@RequestMapping("/admin/books")
public class AdminBooksController {

    private final BookService bookService;
    private final AuthorService authorService;

    @Autowired
    public AdminBooksController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping()
    public String showAllBooks(HttpSession session, Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "admin/books/adminBooks";
    }

    @PostMapping()
    public String createNewBook(HttpSession session, @ModelAttribute Book book) {
        bookService.createBook(book);
        return "admin/books/success";
    }

    @GetMapping("/new")
    public String startCreation(HttpSession session, Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("allAuthors", authorService.getAllAuthors());
        return "admin/books/createNew";
    }

    @GetMapping("/{id}")
    public String showDetails(HttpSession session, Model model, @PathVariable("id") Long bookId) {
        if (!bookService.existsById(bookId)) {
            return "redirect:/books";
        }
        model.addAttribute("book", bookService.getBookById(bookId));
        return "admin/books/about";
    }
}
