package service;

import static exception.ErrorMessage.*;

import domain.Member;
import repository.MemberRepository;

public class UserService {
	private final MemberRepository memberRepository;

	public UserService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public Member findMemberById(long memberId){
		Member findMember = memberRepository.findUserById(memberId);
		if(findMember == null){
			throw new IllegalArgumentException(NOT_FOUND_MEMBER.getErrorMessage());
		}
		return  findMember;
	}
}
