package com.example.Book.Webapp.Controller;

import com.example.Book.Webapp.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService; // Injecting the BookService

    @GetMapping
    public ResponseEntity<String> getBooks(@RequestParam("query") String query) {
        try {
            // Call the service to get books based on the query
            String response = bookService.getBooks(query);
            // Return the response with HTTP status 200 (OK)
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Return an error response with HTTP status 500 (Internal Server Error)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
