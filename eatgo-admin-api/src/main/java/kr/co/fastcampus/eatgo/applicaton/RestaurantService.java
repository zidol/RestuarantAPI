package kr.co.fastcampus.eatgo.applicaton;

import kr.co.fastcampus.eatgo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class  RestaurantService {

    private RestaurantRepository restaurantRepository;
    //31강에서 지움
//    private MenuItemRepository menuItemRepository;
//   private ReviewRepository reviewRepository;

    //31강에서 지움
//    public RestaurantService(RestaurantRepository restaurantRepository,
//                             MenuItemRepository menuItemRepository, ReviewRepository reviewRepository) {
//      this.restaurantRepository = restaurantRepository;
//      this.menuItemRepository = menuItemRepository;
//      this.reviewRepository = reviewRepository;
//    }
    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

   public List<Restaurant> getRestaurants() {
      List<Restaurant> restaurants = restaurantRepository.findAll();

      return restaurants;
   }

   public Restaurant getRestaurant(Long id) {
      Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new RestaurantNotFoundException(id));
      return restaurant;
   }

    public Restaurant addRestaurants(Restaurant restaurant) {
       return restaurantRepository.save(restaurant);
    }

    @Transactional
    public Restaurant updateRestaurant(Long id, String name, String address) {
      Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
      restaurant.updateInformamtion(name, address);

       return restaurant;
    }
}
