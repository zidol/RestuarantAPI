package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.applicaton.RestaurantService;
import kr.co.fastcampus.eatgo.domain.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;;

    @GetMapping("/restaurants")
    public List<Restaurant> list(
            @RequestParam("region") String region,
            @RequestParam("category") long categoryId
    ) {
        List<Restaurant> restaurants = restaurantService.getRestaurants(region, categoryId);

        return restaurants;
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id) {

        Restaurant restaurant = restaurantService.getRestaurant(id);

        return restaurant;
    }
}
