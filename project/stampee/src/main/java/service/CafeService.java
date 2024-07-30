package service;

import java.util.List;
import dto.response.LoggedCafeDto;
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

	public LoggedCafeDto login(String email, String password) {
		//CafeRepository에서 로그인 시도 및 사용자 정보 반환
		LoggedCafeDto loggedCafeDto = cafeRepository.login(email, password);
		if (loggedCafeDto != null) {
			log.info("Successfully logged in cafe: {}", email);
			return loggedCafeDto;
			//성공 시 사용자 정보를 담은 DTO 반환
		} else {
			log.warn("Failed login attempt for cafe: {}", email);
			return null;
			//실패 시 null 반환
		}
	}
}
