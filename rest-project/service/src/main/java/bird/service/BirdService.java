package bird.service;

import bird.dto.BirdDto;
import bird.request.CreateBirdRequest;
import bird.response.BirdsResponse;

/*
 * Interface for bird operations
 */
public interface BirdService {
	public BirdDto createBird(CreateBirdRequest request);

	public BirdsResponse getAllBirds();

	public BirdDto getBird(String id);

	public void deleteBird(String id);

}
