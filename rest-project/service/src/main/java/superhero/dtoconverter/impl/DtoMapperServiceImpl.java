package superhero.dtoconverter.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import superhero.dto.SuperHeroDto;
import superhero.dtoconverter.DtoMapperService;
import superhero.entity.SuperHero;
import superhero.exceptionHandler.handler.ErrorCode;
import superhero.exceptionHandler.types.InvalidRequestException;
import superhero.request.CreateSuperHeroRequest;

@Service("DtoMapperService")
public class DtoMapperServiceImpl implements DtoMapperService {

	@Override
	public SuperHeroDto getSuperHeroDto(SuperHero superHero) {
		SuperHeroDto superHeroDto = null;

		if(superHero != null){
			superHeroDto = new SuperHeroDto(superHero.getName(), superHero.getPseudonym(), superHero.getPublisher(), superHero.getPowers(), superHero.getAllies(), superHero.getFirstAppearanceDate());
			if(superHero.getId() != null){
				superHeroDto.setId(superHero.getId());
			}
		}

		return superHeroDto;
	}


	@Override
	public SuperHeroDto getSuperHeroDto(CreateSuperHeroRequest request) {
		Set<String> skills = getSkills(request.getSkills());
		Set<String> allies = getAllies(request.getAllies());
		java.sql.Date firstAppDate = getFirstAppDate(request.getFirstAppearanceDate());
		
		return new SuperHeroDto(request.getName(), request.getPseudonym(), request.getPublisher(), skills, allies, firstAppDate);
	
	}



	@Override
	public List<SuperHeroDto> getSuperHeroDtos(List<SuperHero> superHeroes) {
		List<SuperHeroDto> superHeroDtos = new ArrayList<SuperHeroDto>();

		if(superHeroes != null){
			for(SuperHero superHero:superHeroes){
				SuperHeroDto dto = getSuperHeroDto(superHero);
				if(dto != null){
					superHeroDtos.add(dto);
				}

			}
		}

		return superHeroDtos;
	}



	@Override
	public SuperHero getSuperHero(SuperHeroDto superHeroDto) {
		SuperHero superHero = null;
		if(superHeroDto != null){
			superHero = new SuperHero(superHeroDto.getName(), superHeroDto.getPseudonym(), superHeroDto.getPublisher(), superHeroDto.getPowers(), superHeroDto.getAllies(), superHeroDto.getFirstAppearanceDate());
			if(superHeroDto.getId() != null){
				superHero.setId(superHeroDto.getId());
			}
		}

		return superHero;
	}

	private Set<String> getSkills(List<String> skills){
		Set<String> skillSet = new HashSet<String>();
		if(skills != null){
			for(String skill:skills){
				skillSet.add(skill);
			}
		}

		return skillSet;
	}
	
	private Set<String> getAllies(List<String> allies){
		Set<String> allySet = new HashSet<String>();
		if(allies != null){
			for(String ally:allies){
				allySet.add(ally);
			}
		}
		
		return allySet;
	}

	private Date getFirstAppDate(String date){
		java.sql.Date sqlDate = null;
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date convertedDate = sdf.parse(date);
			sqlDate = new java.sql.Date(convertedDate.getTime());
		}catch(Exception e){
			throw new InvalidRequestException(ErrorCode.INPUT_INVALID_PAYLOAD.getDescription());
		}

		return sqlDate;
	}

}
