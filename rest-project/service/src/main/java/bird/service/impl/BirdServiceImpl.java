package bird.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bird.dao.BirdDao;
import bird.dto.BirdDto;
import bird.dtoconverter.DtoMapperService;
import bird.entity.Bird;
import bird.exceptionHandler.handler.ErrorCode;
import bird.exceptionHandler.types.InvalidRequestException;
import bird.exceptionHandler.types.NotFoundException;
import bird.exceptionHandler.types.ServerException;
import bird.request.CreateBirdRequest;
import bird.response.BirdsResponse;
import bird.service.BirdService;
import bird.validator.BirdValidator;

@Service
public class BirdServiceImpl implements BirdService{

	@Autowired
	private BirdDao birdDao;

	@Autowired
	private DtoMapperService dtoMapperService;

	@Autowired
	private BirdValidator birdValidator;


	@Override
	@Transactional(readOnly=true)
	public BirdsResponse getAllBirds() {
		BirdsResponse birdsResponse = new BirdsResponse();
		List<String> birdIds = new ArrayList<String>();

		try{
			List<Bird> birds = birdDao.getAllBirds();
			if(birds != null){
				for(Bird bird:birds){
					birdIds.add(bird.getId());
				}
			}

			birdsResponse.setBirdIds(birdIds);
		}catch(Exception e){
			throw new ServerException(ErrorCode.MISC_SERVER_ERROR.getDescription());
		}

		return birdsResponse;
	} 


	@Override
	@Transactional(readOnly=true)
	public BirdDto getBird(String id) {
		BirdDto dto = null;

		if(id == null || id.isEmpty()){
			throw new InvalidRequestException(ErrorCode.INPUT_INVALID_PAYLOAD.getDescription());
		}

		Bird bird = birdDao.getBird(id);
		if(bird == null){
			throw new NotFoundException(ErrorCode.RESOURCE_NOT_FOUND.getDescription());
		}

		try{
			dto = dtoMapperService.getBirdDto(bird);
		}catch(Exception e){
			throw new ServerException(ErrorCode.MISC_SERVER_ERROR.getDescription());
		}
		return dto;
	} 

	@Override
	@Transactional
	public BirdDto createBird(CreateBirdRequest request){
		BirdDto birdDto = null;

		if(!birdValidator.validateCreateBirdRequest(request)){
			throw new InvalidRequestException(ErrorCode.INPUT_INVALID_PAYLOAD.getDescription());
		}

		birdDto = dtoMapperService.getBirdDto(request);

		try{
			Bird bird = dtoMapperService.getBird(birdDto);
			bird = birdDao.createBird(bird);
			if(bird != null){
				birdDto = dtoMapperService.getBirdDto(bird);
			}

		}catch(Exception e){
			throw new ServerException(ErrorCode.MISC_SERVER_ERROR.getDescription());
		}

		return birdDto;
	}


	@Override
	@Transactional
	public void deleteBird(String id) {
		Bird bird = birdDao.getBird(id);

		if(bird == null){
			throw new NotFoundException(ErrorCode.RESOURCE_NOT_FOUND.getDescription());
		}

		birdDao.deleteBird(id);

	}


}
