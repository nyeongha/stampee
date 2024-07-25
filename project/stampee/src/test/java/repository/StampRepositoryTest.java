package repository;

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
		Member member = new Member(2L, "1234", "test@naver.com", "010-1234-1234", "hello");
		Cafe cafe = new Cafe(1L, "테스트 카페", "서울시 종로구", "1234", "cafe@naver.com","010-1234-1234");
		int count = 3;

		//when
		stampRepository.save(member.getMemberId(), cafe.getCafeId(), count);

		//then
		Stamp stamp = stampRepository.findStamp(member.getMemberId(), cafe.getCafeId());
	}
}