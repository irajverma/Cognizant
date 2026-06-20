package com.library;

import com.library.model.Book;
import com.library.repository.BookJpaRepository;
import com.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class LibraryManagementApplication implements CommandLineRunner {

    @Autowired
    private BookService annotationBookService;

    @Autowired
    private BookJpaRepository bookJpaRepository;

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("Starting Library Management Spring Application...");
        System.out.println("==================================================");
        SpringApplication.run(LibraryManagementApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n--- DEMONSTRATING EXERCISES 1 TO 8 (Spring XML & AOP) ---");
        // 1. Load the XML application context
        System.out.println("Loading applicationContext.xml...");
        ClassPathXmlApplicationContext xmlContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        System.out.println("XML context loaded successfully.\n");

        // 2. Fetch the XML-configured bean with Setter Injection (Exercise 2 & 5)
        System.out.println("[Exercise 2 & 5] Fetching bookServiceSetter bean...");
        BookService xmlServiceSetter = (BookService) xmlContext.getBean("bookServiceSetter");
        System.out.println("Listing books via xmlServiceSetter (AOP logging should trigger):");
        xmlServiceSetter.listBooks();
        System.out.println();

        // 3. Fetch the XML-configured bean with Constructor Injection (Exercise 7)
        System.out.println("[Exercise 7] Fetching bookServiceConstructor bean...");
        BookService xmlServiceConstructor = (BookService) xmlContext.getBean("bookServiceConstructor");
        xmlServiceConstructor.addBook("Test-Driven Development");
        xmlServiceConstructor.listBooks();
        System.out.println();

        // 4. Test Annotation-Configured BookService (Exercise 6)
        System.out.println("[Exercise 6] Testing Component Scanned and Autowired bookService...");
        System.out.println("Listing books via annotationBookService:");
        annotationBookService.listBooks();
        System.out.println();

        xmlContext.close();
        System.out.println("XML Application Context closed.");

        System.out.println("\n--- DEMONSTRATING EXERCISE 9 (Spring Boot JPA & H2) ---");
        // Seed JPA Repository for Spring Boot
        System.out.println("Saving initial JPA Book entities into H2 database...");
        bookJpaRepository.save(new Book(null, "Design Patterns", "GoF"));
        bookJpaRepository.save(new Book(null, "Refactoring", "Martin Fowler"));
        
        System.out.println("All seeded books in JPA database:");
        bookJpaRepository.findAll().forEach(book -> 
            System.out.println(" - " + book.getTitle() + " by " + book.getAuthor() + " (ID: " + book.getId() + ")")
        );
        System.out.println("Spring Boot REST endpoints are available at: http://localhost:8080/api/books\n");
    }
}
