package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.applicaton.RestaurantService;
import kr.co.fastcampus.eatgo.domain.MenuItem;
import kr.co.fastcampus.eatgo.domain.Restaurant;
import kr.co.fastcampus.eatgo.domain.RestaurantNotFoundException;
import kr.co.fastcampus.eatgo.domain.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RestaurantService restaurantService;

//    @SpyBean(RestaurantService.class)
//    private RestaurantService restaurantService;
//
//    //컨트롤러에 원하는 객체를 주입할수 있음(의존선 주입)
//    @SpyBean(RestaurantRepositoryImpl.class)
//    private RestaurantRepository restaurantRepository;
//
//    @SpyBean(MenuItemRepositoryImpl.class)
//    private MenuItemRepository menuItemRepository;

    @Test
    public void list() throws Exception {
        //가짜 객체
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(Restaurant.builder()
            .id(1004L)
            .name("JOKER House")
            .address("Seoul")
            .build());

        given(restaurantService.getRestaurants()).willReturn(restaurants);

        mvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1004")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"JOKER House\"")
                ));

    }

    @Test
    public void detailWithExisted() throws Exception {
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("JOKER House")
                .address("Seoul")
                .build();
        MenuItem menuItem = MenuItem.builder()
                .name("Kimchi")
                .build();
        restaurant.setMenuItems(Arrays.asList(menuItem));
        Review review = Review.builder()
                .name("JOKER")
                .score(5)
                .description("Great!")
                .build();
        restaurant.setReviews(Arrays.asList(review));

        given(restaurantService.getRestaurant(1004L)).willReturn(restaurant);

        mvc.perform(get("/restaurants/1004"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                containsString("\"id\":1004")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"JOKER House\"")
                ))
                .andExpect(content().string(
                        containsString("Kimchi")
                ))
                .andExpect(content().string(
                        containsString("Great!")
                ));
    }

    @Test
    public void detailWithNotExisted() throws Exception {
        given(restaurantService.getRestaurant(404L))
                .willThrow((new RestaurantNotFoundException(404L)));
        mvc.perform(get("/restaurants/404"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("{}"));
    }

}