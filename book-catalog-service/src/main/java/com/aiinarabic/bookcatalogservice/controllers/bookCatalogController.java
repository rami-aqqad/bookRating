package com.aiinarabic.bookcatalogservice.controllers;

import com.aiinarabic.bookcatalogservice.models.Book;
import com.aiinarabic.bookcatalogservice.models.CatalogItem;
import com.aiinarabic.bookcatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class bookCatalogController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){

        // Get the books with ratings which are reviewed by this user from rating-data-service
        UserRating userRatings = restTemplate.getForObject("http://rating-data-service/ratingsData/users/"+userId, UserRating.class);

        return userRatings.getUserRating().stream()
                .map(rating ->
                {
                    // Get the book information from book-info-service
                    Book book = restTemplate.getForObject("http://book-info-service/books/"+rating.getBookId(), Book.class);
                    return new CatalogItem(book.getName(), "Desc", rating.getRating());
                })
                .collect(Collectors.toList());

    }
}
