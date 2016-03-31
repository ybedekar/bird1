package bird.dtoconverter;

import java.util.List;

import bird.dto.BirdDto;
import bird.entity.Bird;
import bird.request.CreateBirdRequest;

public interface DtoMapperService {
	public BirdDto getBirdDto(Bird bird);

	public BirdDto getBirdDto(CreateBirdRequest request);

	public List<BirdDto> getBirdDtos(List<Bird> birds);

	public Bird getBird(BirdDto birdDto);
}
