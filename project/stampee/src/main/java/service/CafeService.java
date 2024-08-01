package service;

import static util.PasswordUtil.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import dto.response.LoggedCafeDto;
import lombok.RequiredArgsConstructor;
import repository.CafeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import domain.Cafe;

@RequiredArgsConstructor
public class CafeService {
	private final CafeRepository cafeRepository;
	private static final Logger log = LoggerFactory.getLogger(CafeService.class);

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

	public LoggedCafeDto login(String email, String password) throws NoSuchAlgorithmException {
		//CafeRepository에서 엔티티 가져오기
		Cafe cafe = cafeRepository.login(email,password);
		if (cafe != null && verifyPassword(password, cafe.getPassword())) {
			//엔티티를 DTO로 변환
			LoggedCafeDto loggedCafeDto = new LoggedCafeDto();
			loggedCafeDto.setEmail(cafe.getEmail());
			loggedCafeDto.setName(cafe.getName());
			loggedCafeDto.setPassword(cafe.getPassword());
			loggedCafeDto.setAddress(cafe.getAddress());
			loggedCafeDto.setContact(cafe.getContact());
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
