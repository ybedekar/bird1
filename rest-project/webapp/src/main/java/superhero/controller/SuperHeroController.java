package superhero.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import superhero.dto.SuperHeroDto;
import superhero.exceptionHandler.handler.SuperHeroServiceException;
import superhero.request.CreateSuperHeroRequest;
import superhero.service.SuperHeroService;

@RestController
@RequestMapping(SuperHeroController.URL)
public class SuperHeroController {
	public final static String URL = "/superheroservice";
	
	@Autowired
	private SuperHeroService superHeroService;
	
	@RequestMapping(value = "/superhero", method=RequestMethod.POST, produces="application/json")
    @ResponseBody
	public SuperHeroDto createSuperHero(@RequestBody CreateSuperHeroRequest request) throws SuperHeroServiceException{
		SuperHeroDto dto = superHeroService.createSuperHero(request);
		return dto;
	}
	
	@RequestMapping(value = "/allheroes", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public List<SuperHeroDto> getAllSuperHeroes() throws SuperHeroServiceException{
		List<SuperHeroDto> allHeroes = superHeroService.getAllSuperHeroes();
		return allHeroes;
		
	}
	
	@RequestMapping(value = "/superhero/{name}", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public SuperHeroDto getSuperHero( @PathVariable("name") String name) throws SuperHeroServiceException{
		SuperHeroDto dto = superHeroService.getSuperHero(name);
		return dto;
		
	}
	
	
}
