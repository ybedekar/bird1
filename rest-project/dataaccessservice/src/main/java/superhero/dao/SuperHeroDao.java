package superhero.dao;

import java.util.List;

import superhero.entity.SuperHero;

public interface SuperHeroDao {
	public SuperHero createSuperHero(SuperHero superHero);

	public List<SuperHero> getAllSuperHeroes();

	public SuperHero getSuperHero(String name);
}
