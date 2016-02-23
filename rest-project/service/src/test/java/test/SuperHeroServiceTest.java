
package test;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import superhero.dao.SuperHeroDao;
import superhero.dto.SuperHeroDto;
import superhero.dtoconverter.DtoMapperService;
import superhero.entity.SuperHero;
import superhero.request.CreateSuperHeroRequest;
import superhero.service.impl.SuperHeroServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class SuperHeroServiceTest {
	
	@Mock 
	private SuperHeroDao superHeroDao;
	
	@Mock 
	private DtoMapperService dtoMapperService;
	
	@InjectMocks 
	private SuperHeroServiceImpl superHeroService;
	
	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	
	
	@Test
	public void testCreateSuperHero() throws Exception{
		CreateSuperHeroRequest request = new CreateSuperHeroRequest();
		request.setName("Yogesh");
		request.setPseudonym("Yogi");
		request.setPublisher("PUB");
		
		SuperHeroDto dto1 = new SuperHeroDto();
		dto1.setName("Yogesh");
		dto1.setPseudonym("Yogi");
		dto1.setPublisher("PUB");
		
		SuperHero hero = new SuperHero();
		hero.setName("Yogesh");
		hero.setPseudonym("Yogi");
		hero.setPublisher("PUB");
		
		SuperHero hero1 = new SuperHero();
		hero.setId(1);
		hero.setName("Yogesh");
		hero.setPseudonym("Yogi");
		hero.setPublisher("PUB");
		
		when(dtoMapperService.getSuperHeroDto((request))).thenReturn(dto1);
		when(dtoMapperService.getSuperHero(dto1)).thenReturn(hero);
		when(superHeroDao.createSuperHero(hero)).thenReturn(hero1);
	
		superHeroService.createSuperHero(request);
		InOrder order = inOrder(dtoMapperService);
		InOrder order1 = inOrder(superHeroDao);
		
		order.verify(dtoMapperService).getSuperHeroDto(request);
		order.verify(dtoMapperService).getSuperHero(dto1);
		order1.verify(superHeroDao).createSuperHero(hero);
		order.verify(dtoMapperService).getSuperHeroDto(hero1);
	}
	
	@Test
	public void voidTestGetSuperHeroByName() throws Exception{
		String name = "Yogesh";
		SuperHero hero =new SuperHero();
		SuperHeroDto dto = new SuperHeroDto();
		
		hero.setName(name);
		dto.setName(name);
		
		when(superHeroDao.getSuperHero(name)).thenReturn(hero);
		when(dtoMapperService.getSuperHeroDto(hero)).thenReturn(dto);
		
		superHeroService.getSuperHero(name);
		
		InOrder order = inOrder(superHeroDao,dtoMapperService);
		
		order.verify(superHeroDao).getSuperHero(name);
		order.verify(dtoMapperService).getSuperHeroDto(hero);
		
	}
	
	@Test
	public void voidTestGetAllHeroes() throws Exception{
		List<SuperHero> heroes = new ArrayList<SuperHero>();
		List<SuperHeroDto> superHeroDtos = new ArrayList<SuperHeroDto>();
		
		when(superHeroDao.getAllSuperHeroes()).thenReturn(heroes);
		when(dtoMapperService.getSuperHeroDtos(heroes)).thenReturn(superHeroDtos);
		
		superHeroService.getAllSuperHeroes();
		
		InOrder order = inOrder(superHeroDao,dtoMapperService);
		
		order.verify(superHeroDao).getAllSuperHeroes();
		order.verify(dtoMapperService).getSuperHeroDtos(heroes);
		
	}
	
}
