package superhero.dtoconverter;

import java.util.List;

import superhero.dto.SuperHeroDto;
import superhero.entity.SuperHero;
import superhero.request.CreateSuperHeroRequest;

public interface DtoMapperService {
	public SuperHeroDto getSuperHeroDto(SuperHero account);
  
	public SuperHeroDto getSuperHeroDto(CreateSuperHeroRequest request);
	
	public List<SuperHeroDto> getSuperHeroDtos(List<SuperHero> accounts);
	
	public SuperHero getSuperHero(SuperHeroDto superHeroDto);
}
