package com.example.Book.Webapp.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BookService {

    @Value("${google.books.api.key}")
    private String googleApiKey; // Google Books API key

    @Value("${google.books.api.url}")
    private String googleApiUrl; // Google Books API base URL

    public String getBooks(String query) {
        // Construct the full API URL with the query and API key
        String apiUrl = googleApiUrl + query + "&maxResults=40&key=" + googleApiKey;
        RestTemplate restTemplate = new RestTemplate();
        try {
            // Make the HTTP GET request to the Google Books API
            return restTemplate.getForObject(apiUrl, String.class);
        } catch (Exception e) {
            // Throw a runtime exception if an error occurs
            throw new RuntimeException("Error occurred while fetching data from Google Books API: " + e.getMessage(), e);
        }
    }
}