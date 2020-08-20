package kr.co.fastcampus.eatgo.applicaton;

import kr.co.fastcampus.eatgo.domain.Review;
import kr.co.fastcampus.eatgo.domain.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

//    public Review addReview(Long restaurnatId, Review review) {
//        review.setRestaurantId(restaurnatId);
//        retu rn reviewRepository.save(review);
//    }

    public List<Review> getReviews() {

        return reviewRepository.findAll();
    }
}
