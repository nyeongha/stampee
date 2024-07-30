package service;

import java.util.List;

import repository.CafeRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.Cafe;

public class CafeService {
	private final CafeRepository cafeRepository;
	private static final Logger log = LoggerFactory.getLogger(CafeService.class);

	public CafeService(CafeRepository cafeRepository) {
		this.cafeRepository = cafeRepository;
	}

	public List<String> getSignatureMenu(long cafeId) {
		return cafeRepository.findSignatureByCafeId(cafeId);
	}

	public boolean cafeSignUp(Cafe cafe, String menu1, String menu2) {
		try {
			cafeRepository.cafeSignUp(cafe, menu1, menu2);
			log.info("Successfully signed up cafe: {}", cafe.getEmail());
			return true; // 성공적으로 회원가입 완료
		} catch (IllegalArgumentException e) {
			log.error("Failed to sign up cafe due to invalid arguments: {}", cafe.getEmail(), e);
			return false; // 회원가입 실패
		} catch (Exception e) {
			log.error("Failed to sign up cafe due to unexpected error: {}", cafe.getEmail(), e);
			return false; // 회원가입 실패
		}
	}

	public boolean login(String email, String password) {
		boolean loginResult = cafeRepository.login(email, password);
		if (loginResult) {
			log.info("Successfully logged in cafe: {}", email);
		} else {
			log.warn("Failed login attempt for cafe: {}", email);
		}
		return loginResult;
	}
}
