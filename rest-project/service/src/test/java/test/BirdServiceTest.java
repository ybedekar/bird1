
package test;
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
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import bird.dao.BirdDao;
import bird.dto.BirdDto;
import bird.dtoconverter.DtoMapperService;
import bird.entity.Bird;
import bird.request.CreateBirdRequest;
import bird.service.impl.BirdServiceImpl;
import bird.validator.BirdValidator;

@RunWith(MockitoJUnitRunner.class)
public class BirdServiceTest {

	@Mock 
	private BirdDao birdDao;

	@Mock 
	private DtoMapperService dtoMapperService;

	@Mock
	private BirdValidator birdValidator;

	@InjectMocks 
	private BirdServiceImpl birdService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}


	@Test
	public void testCreateBird() throws Exception{
		CreateBirdRequest request = new CreateBirdRequest();
		List<String> continents = new ArrayList<String>();
		continents.add("Australia");

		request.setName("Sparrow");
		request.setFamily("Sparrow");
		request.setContinents(continents);

		BirdDto dto1 = new BirdDto();
		dto1.setName("Sparrow");
		dto1.setFamily("Sparrow");
		dto1.setContinents(continents);

		Bird bird = new Bird();
		bird.setName("Sparrow");
		bird.setFamily("Sparrow");
		bird.setContinents(continents);

		Bird bird1 = new Bird();
		bird1.setId("1");
		bird1.setName("Yogesh");
		bird1.setFamily("Sparrow");
		bird1.setContinents(continents);

		when(birdValidator.validateCreateBirdRequest(request)).thenReturn(true);
		when(dtoMapperService.getBirdDto((request))).thenReturn(dto1);
		when(dtoMapperService.getBird(dto1)).thenReturn(bird);
		when(birdDao.createBird(bird)).thenReturn(bird1);

		birdService.createBird(request);

		InOrder order = inOrder(dtoMapperService);
		InOrder order1 = inOrder(birdDao);

		order.verify(dtoMapperService).getBirdDto(request);
		order.verify(dtoMapperService).getBird(dto1);
		order1.verify(birdDao).createBird(bird);

	}


	@Test
	public void voidTestGetBirdById() throws Exception{
		String id = "1";
		Bird bird =new Bird();
		bird.setId(id);

		BirdDto dto = new BirdDto();
		dto.setId("1");

		when(birdDao.getBird(id)).thenReturn(bird);
		when(dtoMapperService.getBirdDto(bird)).thenReturn(dto);

		birdService.getBird(id);

		InOrder order = inOrder(birdDao,dtoMapperService);

		order.verify(birdDao).getBird(id);
		order.verify(dtoMapperService).getBirdDto(bird);

	}


	@Test
	public void TestGetAllBirds() throws Exception{
		List<Bird> birds = new ArrayList<Bird>();
		
		when(birdDao.getAllBirds()).thenReturn(birds);
		
		birdService.getAllBirds();

		InOrder order = inOrder(birdDao);
		order.verify(birdDao).getAllBirds();
		
	} 

	@Test
	public void TestDeleteBird() throws Exception{
		String id = "1";
		Bird bird =new Bird();
		bird.setId(id);

		when(birdDao.getBird(id)).thenReturn(bird);
		Mockito.doNothing().when(birdDao).deleteBird(id);

		birdService.deleteBird(id);

		InOrder order = inOrder(birdDao);
		order.verify(birdDao).getBird(id);
		order.verify(birdDao).deleteBird(id);

	}

}
