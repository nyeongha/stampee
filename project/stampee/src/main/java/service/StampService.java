package service;

import static exception.ErrorMessage.*;

import java.util.NoSuchElementException;

import javax.mail.MessagingException;

import domain.Cafe;
import domain.Member;
import repository.MemberRepository;
import repository.StampRepository;

public class StampService {
	private final StampRepository stampRepository;
	private final MemberRepository memberRepository;
	private final MailService mailService;

	public StampService(StampRepository stampRepository, MemberRepository memberRepository, MailService mailService) {
		this.stampRepository = stampRepository;
		this.memberRepository = memberRepository;
		this.mailService = mailService;
	}

	public void saveStamp(Cafe cafe, String userPhoneNum, int count){
		Member findUser = memberRepository.findUserByPhoneNum(userPhoneNum);
		if(findUser == null){
			throw new NoSuchElementException(NOT_FOUND_MEMBER.getErrorMessage());
		}

		stampRepository.save(findUser.getId(), cafe.getId(), count);
	}

	// public void shareStamp(Member fromMember, Cafe cafe, String toPhoneNum, int count) throws MessagingException {
	// 	Member toMember = memberRepository.findUserByPhoneNum(toPhoneNum);
	//
	// 	if(toMember == null){
	// 		throw new NoSuchElementException(NOT_FOUND_MEMBER.getErrorMessage());
	// 	}
	//
	// 	stampRepository.updateStamp(cafe.getId(), fromMember.getId(), toMember.getId(), count);
	//
	// 	mailService.sendMail("human9062@gmail.com", "human9062@gmail.com", "스탬프 공유 하기 성공");
	// 	mailService.sendMail("human9062@gmail.com", "human9062@gmail.com", "스탬프 공유 받기 성공");
	// }
}
