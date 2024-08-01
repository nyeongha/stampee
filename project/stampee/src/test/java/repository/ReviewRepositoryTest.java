package repository;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import domain.Cafe;
import domain.Member;
import domain.Review;

class ReviewRepositoryTest {
//
//
	ReviewRepository reviewRepository=new ReviewRepository();
	@Test
	void findAllReviews() {
// 		List<Review> reviews;
// 		reviews=reviewRepository.findAllReviews();
// 		System.out.println(reviews.size());
	}
//
	@Test
	void insertReview() {
// 		// 데이터베이스 상태를 초기화하거나 특정 데이터가 존재하는지 확인합니다
// 		String phone_num = "010-0001-0001";
// 		String contact = "010-0008-0008";
//
// 		// Member member = memberRepository.findUserByPhoneNum(phone_num);
// 		// Cafe cafe = cafeRepository.findCafeByContact(contact);
//
// 		// 데이터가 존재하지 않을 경우 테스트를 실패하게 만듭니다
// 		assertNotNull(member, "Member with phone number " + phone_num + " should exist.");
// 		assertNotNull(cafe, "Cafe with contact " + contact + " should exist.");
//
// 		int cnt1 = reviewRepository.findAllReviews().size();
//
// 		// 새로운 리뷰를 추가합니다
// 		Review newReview = new Review(/* ID를 자동 생성하도록 구현*/4, "메롱", Date.valueOf(LocalDate.of(2024, 6, 25)), member, cafe);
// 		reviewRepository.insertReview(newReview);
//
// 		int cnt2 = reviewRepository.findAllReviews().size();
// 		assertEquals(cnt1 + 1, cnt2, "The number of reviews should be incremented by 1.");
	}
}
