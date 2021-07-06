package com.aiinarabic.ratingdataservice.resources;

import com.aiinarabic.ratingdataservice.models.Rating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratingsData")
public class RatingResource {

    @RequestMapping("{bookId}")
    public Rating getRating(@PathVariable("bookId") String bookId){
        return new Rating(bookId, 4);
    }
}
