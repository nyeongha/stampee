package service;

import java.util.NoSuchElementException;

import domain.Cafe;
import domain.Member;
import repository.MemberRepository;
import repository.StampRepository;

public class StampService {
	private final StampRepository stampRepository;
	private final MemberRepository memberRepository;

	public StampService(StampRepository stampRepository, MemberRepository memberRepository) {
		this.stampRepository = stampRepository;
		this.memberRepository = memberRepository;
	}

	public void saveStamp(Cafe cafe, String userPhoneNum, int count){
		Member findUser = memberRepository.findUserByPhoneNum(userPhoneNum);
		if(findUser == null){
			throw new NoSuchElementException("해당 전화번호로 가입된 회원이 존재하지 않습니다.");
		}

		stampRepository.save(findUser.getMemberId(), cafe.getCafeId(), count);
	}
}
