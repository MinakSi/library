package com.minakov.controllers.admin;

import com.minakov.entities.Book;
import com.minakov.services.AuthorService;
import com.minakov.services.BookService;
import com.minakov.services.GenreService;
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
@RequestMapping("/admin/books")
public class AdminBooksController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Autowired
    public AdminBooksController(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
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
