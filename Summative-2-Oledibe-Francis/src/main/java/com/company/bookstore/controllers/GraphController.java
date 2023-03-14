package com.company.bookstore.controllers;

import com.company.bookstore.models.Author;
import com.company.bookstore.models.Book;
import com.company.bookstore.models.Publisher;
import com.company.bookstore.repositories.AuthorRepository;
import com.company.bookstore.repositories.BookRepository;
import com.company.bookstore.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class GraphController {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;


    @Autowired
    PublisherRepository publisherRepository;

    @QueryMapping
    public List<Author> authors() {
        return  authorRepository.findAll();
    }

    @QueryMapping
    public Author findAuthorById(@Argument String id) {
        Optional<Author> result = authorRepository.findById( Integer.parseInt(id));
        return result.isPresent() ? result.get() :null;
    }


    @QueryMapping
    public List<Publisher> publishers() {
        return  publisherRepository.findAll();
    }

    @QueryMapping
    public Publisher findPublisherById(@Argument String id) {
        Optional<Publisher> result = publisherRepository.findById( Integer.parseInt(id));
        return result.isPresent() ? result.get() :null;
    }

    @QueryMapping
    public List<Book> books() {
        return  bookRepository.findAll();
    }

    @QueryMapping
    public Book findBookById(@Argument String id) {
        Optional<Book> result = bookRepository.findById( Integer.parseInt(id));
        return result.isPresent() ? result.get() :null;
    }
    @SchemaMapping
    public Author author(Book book) {
        Optional<Author> returnVal = authorRepository.findById(book.getAuthorId());
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else { return null;
        } }
    @SchemaMapping
    public Publisher publisher(Book book) {
        Optional<Publisher> returnVal = publisherRepository.findById(book.getPublisherId());
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

}
