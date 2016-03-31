package bird.dao;

import java.util.List;

import bird.entity.Bird;

public interface BirdDao {
	public Bird createBird(Bird bird);

	public List<Bird> getAllBirds();

	public Bird getBird(String id);

	public void deleteBird(String id);
}
