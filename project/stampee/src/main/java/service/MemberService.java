package service;

import static exception.ErrorMessage.*;
import static util.PasswordUtil.*;

import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.Member;
import dto.response.LoggedMemberDto;
import lombok.RequiredArgsConstructor;
import repository.MemberRepository;

@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;

	public boolean memberSignUp(Member member){
		try {
			memberRepository.memberSignUp(member);
			return true; // 성공적으로 회원가입 완료
		} catch (Exception e) {
			return false; // 회원가입 실패
		}
	}

	public LoggedMemberDto login(String email, String password){
		Member member = memberRepository.login(email, password);
		try {
			if(member != null && verifyPassword(password, member.getPassword())){
				return LoggedMemberDto.createLoggedMemberDto(member);
			} else {
				return null;
			}
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}


	public Member findMemberById(long memberId){
		Member findMember = memberRepository.findUserById(memberId);
		if(findMember == null){
			throw new IllegalArgumentException(NOT_FOUND_MEMBER.getErrorMessage());
		}
		return  findMember;
	}
}
