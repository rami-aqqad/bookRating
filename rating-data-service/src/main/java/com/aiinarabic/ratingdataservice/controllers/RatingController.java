package com.aiinarabic.ratingdataservice.controllers;

import com.aiinarabic.ratingdataservice.models.Rating;
import com.aiinarabic.ratingdataservice.models.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsData")
public class RatingController {

    @RequestMapping("/{bookId}")
    public Rating getRating(@PathVariable("bookId") String bookId){
        return new Rating(bookId, 4);
    }

    @RequestMapping("/users/{userId}")
    public UserRating getUserRating(@PathVariable("userId") String userId){
        List<Rating> ratings = Arrays.asList(
                new Rating("A1B", 4),
                new Rating("B2C", 3)
        );
        UserRating userRating = new UserRating();
        userRating.setUserRating(ratings);
        return userRating;
    }

}
