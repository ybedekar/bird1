package bird.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import bird.entity.Bird;

public interface BirdRepository extends MongoRepository<Bird,String>{

}
