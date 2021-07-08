package com.minakov.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "BOOK_AUTHOR",
            joinColumns = {@JoinColumn(name = "BOOK_ID")},
            inverseJoinColumns = {@JoinColumn(name = "AUTHOR_ID")})
    private Set<Author> authors = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "BOOK_GENRE",
            joinColumns = {@JoinColumn(name = "BOOK_ID")},
            inverseJoinColumns = {@JoinColumn(name = "GENRE_ID")})
    private Set<Genre> genres = new HashSet<>();

    public Book() {
    }

    public Book(String name, Set<Author> authors, Set<Genre> genres) {
        this.name = name;
        this.authors = authors;
        this.genres = genres;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(name, book.name)
                && Objects.equals(authors, book.authors) && Objects.equals(genres, book.genres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, authors, genres);
    }
}
