package bird.dtoconverter.impl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import bird.dto.BirdDto;
import bird.dtoconverter.DtoMapperService;
import bird.entity.Bird;
import bird.exceptionHandler.handler.ErrorCode;
import bird.exceptionHandler.types.InvalidRequestException;
import bird.request.CreateBirdRequest;

@Service
public class DtoMapperServiceImpl implements DtoMapperService {

	@Override
	public BirdDto getBirdDto(Bird bird) {
		BirdDto birdDto = null;

		if(bird != null){
			birdDto = new BirdDto(bird.getName(),bird.getFamily(),bird.getContinents(),bird.getAdded(),bird.isVisible());
			if(bird.getId() != null){
				birdDto.setId(bird.getId());
			}
		}

		return birdDto;
	}


	@Override
	public BirdDto getBirdDto(CreateBirdRequest request) {
		Date convertedDate = null;
		String date = null;
		BirdDto birdDto = new BirdDto(request.getName(), request.getFamily(), request.getContinents());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		try{
			if(request.getAdded() != null){
				convertedDate = sdf.parse(request.getAdded());
				date= sdf.format(convertedDate);
			}else{
				date = sdf.format(new Date());
			}
		}catch(Exception e){
			throw new InvalidRequestException(ErrorCode.INPUT_INVALID_PAYLOAD.getDescription());
		}

		birdDto.setAdded(date);

		if(request.getVisible() == null){
			birdDto.setVisible(false);
		}else{
			birdDto.setVisible(request.getVisible());
		}

		return birdDto;

	}



	@Override
	public List<BirdDto> getBirdDtos(List<Bird> birds) {
		List<BirdDto> birdDtos = new ArrayList<BirdDto>();

		if(birds != null){
			for(Bird bird:birds){
				BirdDto dto = getBirdDto(bird);
				if(dto != null){
					birdDtos.add(dto);
				}

			}
		}

		return birdDtos;
	}



	@Override
	public Bird getBird(BirdDto birdDto) {
		Bird bird = null;
		if(birdDto != null){
			bird = new Bird(birdDto.getName(),birdDto.getFamily(),birdDto.getContinents(),birdDto.getAdded(),birdDto.isVisible());
			if(birdDto.getId() != null){
				bird.setId(birdDto.getId());
			}
		}

		return bird;
	}



}
