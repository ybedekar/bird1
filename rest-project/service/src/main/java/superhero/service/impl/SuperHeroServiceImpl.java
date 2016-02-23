package superhero.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import superhero.dao.SuperHeroDao;
import superhero.dto.SuperHeroDto;
import superhero.dtoconverter.DtoMapperService;
import superhero.entity.SuperHero;
import superhero.exceptionHandler.handler.SuperHeroServiceException;
import superhero.exceptionHandler.handler.ErrorCode;
import superhero.exceptionHandler.types.InvalidRequestException;
import superhero.exceptionHandler.types.NotFoundException;
import superhero.request.CreateSuperHeroRequest;
import superhero.service.SuperHeroService;

@Service("superHeroService")
public class SuperHeroServiceImpl implements SuperHeroService{
	@Autowired
	private SuperHeroDao superHeroDao;

	@Autowired
	private DtoMapperService dtoMapperService;

	public SuperHeroDao getSuperHeroDao() {
		return superHeroDao;
	}

	public void setSuperHeroDao(SuperHeroDao superHeroDao) {
		this.superHeroDao = superHeroDao;
	}

	public DtoMapperService getDtoMapperService() {
		return dtoMapperService;
	}

	public void setDtoMapperService(DtoMapperService dtoMapperService) {
		this.dtoMapperService = dtoMapperService;
	}


	@Override
	@Transactional(readOnly=true)
	public List<SuperHeroDto> getAllSuperHeroes() {
		List<SuperHero> superHeroes = superHeroDao.getAllSuperHeroes();
		List<SuperHeroDto> dtos = dtoMapperService.getSuperHeroDtos(superHeroes);
		return dtos;
	}


	@Override
	@Transactional(readOnly=true)
	public SuperHeroDto getSuperHero(String name) {
		if(name == null || name.isEmpty()){
			throw new InvalidRequestException(ErrorCode.INPUT_INVALID_PAYLOAD.getDescription());
		}

		SuperHero superHero = superHeroDao.getSuperHero(name);
		if(superHero == null){
			throw new NotFoundException(ErrorCode.RESOURCE_NOT_FOUND.getDescription());
		}
		
		SuperHeroDto dto = dtoMapperService.getSuperHeroDto(superHero);
		return dto;
	}

	@Override
	@Transactional
	public SuperHeroDto createSuperHero(CreateSuperHeroRequest request) {
		SuperHeroDto createdSuperHeroDto = null;

		if(request == null || request.getName() == null || request.getName().isEmpty()){
			throw new InvalidRequestException(ErrorCode.INPUT_INVALID_PAYLOAD.getDescription());
		}

		SuperHeroDto superHeroDto = dtoMapperService.getSuperHeroDto(request);

		try{
			SuperHero superHero = dtoMapperService.getSuperHero(superHeroDto);
			superHero = superHeroDao.createSuperHero(superHero);
			if(superHero != null){
				createdSuperHeroDto = dtoMapperService.getSuperHeroDto(superHero);
			}

		}catch(Exception e){
			throw new SuperHeroServiceException(ErrorCode.MISC_SERVER_ERROR.getDescription());
		}

		return createdSuperHeroDto;
	}


}
