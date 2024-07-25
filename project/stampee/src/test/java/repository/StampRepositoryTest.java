package repository;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.Cafe;
import domain.Member;
import domain.Stamp;

class StampRepositoryTest {
	StampRepository stampRepository = new StampRepository();

	@Test
	@DisplayName("Stamp를 저장할 수 있다.")
	void save(){
		//given
		Member member = new Member(1L, "1234", "test@naver.com", "010-1234-1234");
		Cafe cafe = new Cafe(1L, "테스트 카페", "서울시 종로구", "1234", "cafe@naver.com","010-1234-1234");
		Stamp stamp = new Stamp(3, LocalDate.now(), member,  cafe);

		//when
		Stamp savedStamp = stampRepository.save(stamp);

		//then
		assertThat(savedStamp.getCount()).isEqualTo(3);
	}
}