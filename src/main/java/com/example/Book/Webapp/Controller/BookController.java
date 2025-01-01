package com.example.Book.Webapp.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/books")
public class BookController {
    
    // Injecting the API key from the application.properties file
    @Value("${google.books.api.key}")
    private String apiKey;
    
    /**
     * API endpoint to fetch books from Google Books API.
     * The client sends a query parameter to search for books.
     *
     * @param query The search term entered by the user.
     * @return JSON response containing book data or an error message.
     */
    @GetMapping
    public ResponseEntity<String> getBooks(@RequestParam("query") String query) {
        // Constructing the Google Books API URL with the search query and API key
        String apiUrl = "https://www.googleapis.com/books/v1/volumes?q=" + query + "&maxResults=40&key=" + apiKey;
        
        // Using RestTemplate to make an HTTP GET request to the external API
        RestTemplate restTemplate = new RestTemplate();
        try {
            // Fetching the response as a String
            String response = restTemplate.getForObject(apiUrl, String.class);
            
            // Returning the API response with an HTTP 200 status
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Returning an HTTP 500 status with an error message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while fetching data from Google Books API : " + e.getMessage());
        }
    }
    
}
