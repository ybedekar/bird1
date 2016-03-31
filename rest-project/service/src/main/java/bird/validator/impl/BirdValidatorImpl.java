package bird.validator.impl;

import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import bird.request.CreateBirdRequest;
import bird.validator.BirdValidator;

@Service
public class BirdValidatorImpl implements BirdValidator {

	@Override
	public boolean validateCreateBirdRequest(CreateBirdRequest request) {
		HashSet<String> seenContinents = new HashSet<String>();

		String name = request.getName();
		String family = request.getFamily();
		List<String> continents = request.getContinents();

		if(name == null || name.isEmpty() 
				|| family == null || family.isEmpty() 
				|| continents == null || continents.isEmpty())
		{
			return false;
		}

		for(String continent: continents){
			if(seenContinents.contains(continent))
				return false;

			seenContinents.add(continent);
		}

		return true;
	}

}
