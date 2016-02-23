package superhero.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import superhero.entity.SuperHero;

public interface SuperHeroRepository extends JpaRepository<SuperHero,Integer>{
	public SuperHero findByName(String name);
}
