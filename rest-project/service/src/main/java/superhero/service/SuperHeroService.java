package superhero.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import superhero.dto.SuperHeroDto;
import superhero.request.CreateSuperHeroRequest;

/*
 * Interface for super hero operations
 */
public interface SuperHeroService {
	public SuperHeroDto createSuperHero(CreateSuperHeroRequest request);
	
	public List<SuperHeroDto> getAllSuperHeroes();
	
	public SuperHeroDto getSuperHero(String name);
	
}
