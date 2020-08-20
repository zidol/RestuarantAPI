package kr.co.fastcampus.eatgo.applicaton;

import kr.co.fastcampus.eatgo.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class RestaurantServiceTest {

    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;
//31강에서 지움
//    @Mock
//    private MenuItemRepository menuItemRepository;
//
//    @Mock
//    private ReviewRepository reviewRepository;

    @Before// 모든 테스트 긱긱 ㅅ;ㄹ전에 무조건 한번 실행
    public void setUp() {
//        restaurantRepository = new RestaurantRepositoryImpl();
//        menuItemRepository = new MenuItemRepositoryImpl();
        MockitoAnnotations.initMocks(this); //@Mock 객체 초기화

        mockRestaurantRepository();                                         //====> 가짜 Repository 생성
        //31강에서 지움
//        mockMenuItemRepository();
//        mockReviewRepository();
//        restaurantService =  new RestaurantService(restaurantRepository, menuItemRepository, reviewRepository);
        restaurantService =  new RestaurantService(restaurantRepository);
    }
//31강에서 지움
//    private void mockMenuItemRepository() {
//        List<MenuItem> menuItems = new ArrayList<>();
//        menuItems.add(MenuItem.builder()
//                .name("Kimchi")
//                .build());
//        given(menuItemRepository.findAllByRestaurantId(1004L)).willReturn(menuItems);
//    }
//
    private void mockRestaurantRepository() {
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .address("Seoul")
                .name("Bob Zip")

                .build();
        restaurants.add(restaurant);

        given(restaurantRepository.findAll()).willReturn(restaurants);

        given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));

    }
//
//    private void mockReviewRepository() {
//        List<Review> reviews = new ArrayList<>();
//        reviews.add(Review.builder()
//        .name("BeRyong")
//        .score(1)
//        .description("Bad")
//        .build());
//        given(reviewRepository.findAllByRestaurantId(1004L))
//                .willReturn(reviews);
//    }


    @Test
    public void getRestaurants() {
        List<Restaurant> restaurants = restaurantService.getRestaurants();

        Restaurant restaurant = restaurants.get(0);

        assertThat(restaurant.getId(), is(1004L));
    }

    @Test
    public void getRestaurantWithExisted() {
        Restaurant restaurant = restaurantService.getRestaurant(1004L);
//31강에서 지움
//        verify(menuItemRepository).findAllByRestaurantId(eq(1004L));
//        verify(reviewRepository).findAllByRestaurantId(eq(1004L));

        assertThat(restaurant.getId(), is(1004L));

        //31강에서 지움
//        MenuItem menuItem = restaurant.getMenuItems().get(0);
//
//        assertThat(menuItem.getName(), is("Kimchi"));
//
//        Review review = restaurant.getReviews().get(0);
//        assertThat(review.getDescription(), is("Bad"));
    }

    @Test(expected = RestaurantNotFoundException.class)
    public void getRestaurantWithNotExisted() {
        restaurantService.getRestaurant(404L);
    }

    @Test
    public void addRestaurant() {
        given(restaurantRepository.save(any())).will(invocation -> {
            Restaurant restaurant = invocation.getArgument(0);
            restaurant.setId(1234L);
            return  restaurant;
        });
        Restaurant restaurant = Restaurant.builder()
                .name("BeRyong")
                .address("Busan")
                .build();


        Restaurant created = restaurantService.addRestaurants(restaurant);
        assertThat(created.getId(), is(1234L));
    }

    @Test
    public void updateRestaurant() {
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build();

        given(restaurantRepository.findById(1004L))
                .willReturn(Optional.of(restaurant));

        Restaurant updated =  restaurantService.updateRestaurant(1004L, "Sul zip", "Busan");

         assertThat(restaurant.getName(), is("Sul zip"));
        assertThat(restaurant.getAddress(), is("Busan"));
    }
}