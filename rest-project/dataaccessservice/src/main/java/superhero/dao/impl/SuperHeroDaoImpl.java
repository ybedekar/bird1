package superhero.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import superhero.dao.SuperHeroDao;
import superhero.entity.SuperHero;
import superhero.repository.SuperHeroRepository;

@Repository
public class SuperHeroDaoImpl implements SuperHeroDao {

	@Autowired
	private SuperHeroRepository repository;

	@Override
	public SuperHero getSuperHero(String name) {
		return repository.findByName(name);
	}

	@Override
	public List<SuperHero> getAllSuperHeroes() {
		return repository.findAll();
		
	}

	@Override
	public SuperHero createSuperHero(SuperHero superHero) {
		SuperHero superHero1 = repository.save(superHero);
		repository.flush();

		return superHero1;
	}


}
