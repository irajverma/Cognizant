package com.library.repository;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {
    private final List<String> books = new ArrayList<>();

    public BookRepository() {
        // Seed some sample books
        books.add("Effective Java");
        books.add("Clean Code");
        books.add("Spring in Action");
    }

    public List<String> findAllBooks() {
        System.out.println("BookRepository: Fetching all books from the database...");
        return new ArrayList<>(books);
    }

    public void saveBook(String bookTitle) {
        books.add(bookTitle);
        System.out.println("BookRepository: Saved book '" + bookTitle + "' to the database.");
    }
}
