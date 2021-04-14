package it.viki.ratinginfoservice.controller;

import it.viki.ratinginfoservice.interfaces.Rating;
import it.viki.ratinginfoservice.interfaces.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingController {

    @RequestMapping("/{technoId}")
    public Rating getRating(@PathVariable final String technoId) {
        return new Rating(technoId, "6");
    }

    @RequestMapping("employee/{empId}")
    public UserRating getUserRating(@PathVariable final String empId) {
        final List<Rating> ratings = Arrays.asList(
                new Rating("111", "5"),
                new Rating("222", "6")
        );
        final UserRating userRating = new UserRating();
        userRating.setRatings(ratings);
        return userRating;
    }
}