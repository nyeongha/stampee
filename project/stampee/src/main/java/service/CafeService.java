package service;

import static util.PasswordUtil.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import dto.response.LoggedCafeDto;
import lombok.RequiredArgsConstructor;
import repository.CafeRepository;
import domain.Cafe;

@RequiredArgsConstructor
public class CafeService {
	private final CafeRepository cafeRepository;

	public List<String> getSignatureMenu(long cafeId) {
		return cafeRepository.findSignatureByCafeId(cafeId);
	}

	public boolean cafeSignUp(Cafe cafe, String menu1, String menu2) {
		try {
			cafeRepository.cafeSignUp(cafe, menu1, menu2);
			return true; // 성공적으로 회원가입 완료
		} catch (Exception e) {
			return false; // 회원가입 실패
		}
	}

	public LoggedCafeDto login(String email, String password) throws NoSuchAlgorithmException {
		Cafe cafe = cafeRepository.login(email, password);
		if (cafe != null && verifyPassword(password, cafe.getPassword())) {
			return LoggedCafeDto.createLoggedCafeDto(cafe);
		} else {
			return null;
		}
	}
}
