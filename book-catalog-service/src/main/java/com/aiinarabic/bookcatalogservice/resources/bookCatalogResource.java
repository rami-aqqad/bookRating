package com.aiinarabic.bookcatalogservice.resources;

import com.aiinarabic.bookcatalogservice.models.Book;
import com.aiinarabic.bookcatalogservice.models.CatalogItem;
import com.aiinarabic.bookcatalogservice.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class bookCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){

        List<Rating> ratings = Arrays.asList(
                new Rating("1234", 4),
                new Rating("4567", 4)
        );

        return ratings.stream()
                .map(rating ->
                {
                    Book book = restTemplate.getForObject("http://localhost:8084/books/"+rating.getBookId(), Book.class);
                    return new CatalogItem(book.getName(), "Desc", rating.getRating());
                })
                .collect(Collectors.toList());



        //return Collections.singletonList(
          //      new CatalogItem("1001", "Test", 4)
        //);
    }
}
