package kr.co.fastcampus.eatgo.applicaton;

import kr.co.fastcampus.eatgo.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class  RestaurantService {

    private RestaurantRepository restaurantRepository;
    private MenuItemRepository menuItemRepository;
   private ReviewRepository reviewRepository;

    public RestaurantService(RestaurantRepository restaurantRepository,
                             MenuItemRepository menuItemRepository, ReviewRepository reviewRepository) {
      this.restaurantRepository = restaurantRepository;
      this.menuItemRepository = menuItemRepository;
      this.reviewRepository = reviewRepository;
    }

   public List<Restaurant> getRestaurants(String region, long categoryId) {
        //Todo: category id 사용

      List<Restaurant> restaurants = restaurantRepository.findAllByAddressContainingAndCategoryId(region, categoryId);

      return restaurants;
   }

   public Restaurant getRestaurant(Long id) {
      Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new RestaurantNotFoundException(id));

      List<MenuItem> menuItems = menuItemRepository.findAllByRestaurantId(id);
      restaurant.setMenuItems(menuItems);

      List<Review> reviews = reviewRepository.findAllByRestaurantId(id);
      restaurant.setReviews(reviews);

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
