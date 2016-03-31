package bird.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bird.dao.BirdDao;
import bird.entity.Bird;
import bird.exceptionHandler.handler.ErrorCode;
import bird.exceptionHandler.types.ServerException;
import bird.repository.BirdRepository;

@Repository
public class BirdDaoImpl implements BirdDao {

	@Autowired
	private BirdRepository repository;


	@Override
	public Bird getBird(String id) {
		try{
			return repository.findOne(id);
		}catch(Exception e){
			throw new ServerException(ErrorCode.MISC_SERVER_ERROR.getDescription());
		}
	}


	@Override
	public List<Bird> getAllBirds() {
		try{
			return repository.findAll();
		}catch(Exception e){
			throw new ServerException(ErrorCode.MISC_SERVER_ERROR.getDescription());
		}
	}

	@Override
	public Bird createBird(Bird bird) {
		try{
			Bird bird1 = repository.save(bird);
			return bird1;
		}catch(Exception e){
			throw new ServerException(ErrorCode.MISC_SERVER_ERROR.getDescription());
		}
	}


	@Override
	public void deleteBird(String id) {
		try{
			repository.delete(id);
		}catch(Exception e){
			throw new ServerException(ErrorCode.MISC_SERVER_ERROR.getDescription());
		}
	}


}
