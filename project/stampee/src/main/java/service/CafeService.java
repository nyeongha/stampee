package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.Cafe;
import domain.Signature;
import repository.CafeRepository;

public class CafeService {

	private final CafeRepository cafeRepository;
	private static final Logger log = LoggerFactory.getLogger(CafeService.class);

	public CafeService(CafeRepository cafeRepository) {
		this.cafeRepository = cafeRepository;
	}

	public boolean signUp(Cafe cafe, Signature signature) {
		try {
			cafeRepository.cafeSignUp(cafe, signature);
			log.info("Successfully signed up cafe: {}", cafe.getEmail());
			return true; // 성공적으로 회원가입 완료
		} catch (IllegalArgumentException e) {
			log.info("Failed to sign up cafe: {}", cafe.getEmail(), e);
			return false; // 회원가입 실패
		}
	}

	public boolean login(Cafe cafe) {
		boolean loginResult = cafeRepository.login(cafe);
		if (loginResult) {
			log.info("Successfully logged in cafe: {}", cafe.getEmail());
		} else {
			log.warn("Failed login attempt for cafe: {}", cafe.getEmail());
		}
		return loginResult;
	}
}
