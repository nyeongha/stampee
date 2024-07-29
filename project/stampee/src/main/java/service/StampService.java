package service;

import static exception.ErrorMessage.*;
import static formatter.MailMessageFormatter.*;
import static java.lang.String.*;

import java.sql.SQLException;
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

	public void saveStamp(Cafe cafe, String userPhoneNum, int count) throws SQLException {	//스탬프 저장하는 메서드
		Member findUser = memberRepository.findUserByPhoneNum(userPhoneNum);
		if(findUser == null){
			throw new NoSuchElementException(NOT_FOUND_MEMBER.getErrorMessage());
		}
		stampRepository.save(findUser.getId(), cafe.getId(), count);
	}

	//친구에게 스탬프 공유하는 메서드
	public void shareStamp(Member fromMember, Cafe cafe, String toPhoneNum, int count) throws MessagingException, SQLException {
		Member toMember = memberRepository.findUserByPhoneNum(toPhoneNum);
		if(toMember == null){
			throw new NoSuchElementException(NOT_FOUND_MEMBER.getErrorMessage());
		}
		if (stampRepository.updateStamp(cafe.getId(), fromMember.getId(), toMember.getId(), count)) {	//성공한 경우
			mailService.sendMail(fromMember.getEmail(), "human9062@gmail.com", format(SEND_STAMP_MESSAGE.getMessage(), count, toMember.getUserName()), SHARE_STAMP_TITLE.getMessage());
			mailService.sendMail(toMember.getEmail(), "human9062@gmail.com", format(RECEIVE_STAMP_MESSAGE.getMessage(), fromMember.getUserName(), count), SHARE_STAMP_TITLE.getMessage());
		} else {	//실패 한 경우
			mailService.sendMail(fromMember.getEmail(), "human9062@gmail.com", FAIL_SEND_STAMP_MESSAGE.getMessage(), SHARE_STAMP_TITLE.getMessage());
		}
	}
}
