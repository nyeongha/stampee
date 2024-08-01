package service;

import static exception.ErrorMessage.*;

import domain.Member;
import lombok.RequiredArgsConstructor;
import repository.MemberRepository;

@RequiredArgsConstructor
public class UserService {
	private final MemberRepository memberRepository;

	public Member findMemberById(long memberId){
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
