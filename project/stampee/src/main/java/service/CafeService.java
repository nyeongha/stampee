package service;

import java.util.List;

import repository.CafeRepository;

public class CafeService {
	private final CafeRepository cafeRepository;

	public CafeService(CafeRepository cafeRepository) {
		this.cafeRepository = cafeRepository;
	}

	public List<String> getSignatureMenu(long cafeId){
		return cafeRepository.findSignatureByCafeId(cafeId);
	}
}
