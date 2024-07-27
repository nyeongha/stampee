package repository;

import org.assertj.core.api.Assertions;
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
		Member member = Member.createMember(35L, "1234", "test@naver.com", "010-1234-1234", "hello");
		Cafe cafe = new Cafe(5L, "테스트 카페", "서울시 종로구", "1234", "cafe@naver.com","010-1234-1234");
		int count = 3;

		//when
		stampRepository.save(member.getId(), cafe.getId(), count);

		//then
		Stamp stamp = stampRepository.findStamp(member.getId(), cafe.getId());
		Assertions.assertThat(stamp.getCount()).isEqualTo(count);
	}
}