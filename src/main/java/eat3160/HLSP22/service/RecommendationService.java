package eat3160.HLSP22.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eat3160.HLSP22.model.RecommendationEntity;
import eat3160.HLSP22.repository.RecommendationRepository;

@Service
public class RecommendationService {
	
	@Autowired
	private RecommendationRepository recommendationRepository;
	
	/**
	 * Get all the recommendation records.
	 * 
	 * @return recommendation records. 
	 */
	public Iterable<RecommendationEntity> findAll() {
		return recommendationRepository.findAll();
	}
	
}
