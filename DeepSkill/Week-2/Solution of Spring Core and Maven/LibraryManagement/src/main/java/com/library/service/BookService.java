package com.library.service;

import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {
    private BookRepository bookRepository;

    // Default constructor
    public BookService() {
        System.out.println("BookService: Default constructor invoked.");
    }

    // Constructor injection (annotated with @Autowired)
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        System.out.println("BookService: Constructor injection with @Autowired invoked.");
    }

    // Setter injection (annotated with @Autowired)
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        System.out.println("BookService: Setter injection with @Autowired invoked.");
    }

    public List<String> listBooks() {
        System.out.println("BookService: Delegating listing of books to BookRepository...");
        return bookRepository.findAllBooks();
    }

    public void addBook(String bookTitle) {
        System.out.println("BookService: Delegating saving book to BookRepository...");
        bookRepository.saveBook(bookTitle);
    }
}
