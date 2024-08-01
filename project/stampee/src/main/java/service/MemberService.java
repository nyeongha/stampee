package service;

import static exception.ErrorMessage.*;
import static util.PasswordUtil.*;

import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.Member;
import dto.response.LoggedMemberDto;
import repository.MemberRepository;

public class MemberService {
	private static MemberRepository memberRepository = null;
	private static final Logger log = LoggerFactory.getLogger(CafeService.class);


	public MemberService(MemberRepository memberRepository) {
		MemberService.memberRepository = memberRepository;
	}

	public boolean memberSignUp(Member member){
		try {
			memberRepository.memberSignUp(member);
			log.info("Successfully signed up cafe: {}", member.getEmail());
			return true; // 성공적으로 회원가입 완료
		} catch (Exception e) {
			log.error("Failed to sign up member due to invalid arguments: {}", member.getEmail(), e);
			return false; // 회원가입 실패
		}
	}

	public LoggedMemberDto login(String email, String password){
		//MemberRepository에서 엔티티 가져오기
		Member member = memberRepository.login(email, password);
		try {
			if(member != null && verifyPassword(password, member.getPassword())){
				//엔티티를 DTO로 변환
				LoggedMemberDto loggedMemberDto = new LoggedMemberDto();
				loggedMemberDto.setEmail(member.getEmail());
				loggedMemberDto.setPassword(member.getUserName());
				loggedMemberDto.setPassword(member.getPassword());
				loggedMemberDto.setUsername(member.getPhoneNumber());
				log.info("Successfully logged in member: {}", email);
				return loggedMemberDto;
			} else {
				log.warn("Failed login attempt for member: {}", email);
				return null;
			}
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}


	public static Member findMemberById(long memberId){
		Member findMember = memberRepository.findUserById(memberId);
		if(findMember == null){
			throw new IllegalArgumentException(NOT_FOUND_MEMBER.getErrorMessage());
		}
		return  findMember;
	}

	public Member findMemberByPhoneNumber(String phoneNumber){
		Member findMember = memberRepository.findUserByPhoneNum(phoneNumber);
		if(findMember == null){
			throw new IllegalArgumentException(NOT_FOUND_MEMBER.getErrorMessage());
		}
		return  findMember;
	}
}
