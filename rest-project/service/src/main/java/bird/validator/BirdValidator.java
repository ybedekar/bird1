package bird.validator;

import bird.request.CreateBirdRequest;

public interface BirdValidator {
	public boolean validateCreateBirdRequest(CreateBirdRequest request);
}
