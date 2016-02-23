package superhero.dto;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.validator.constraints.NotBlank;


public class SuperHeroDto{
	private Integer id;
	
	@NotBlank(message="name cannot be wmpty")
	private String name;

	private String pseudonym;
	
	private String publisher;

	private Set<String> powers = new HashSet();
	
    private Set<String> allies = new HashSet();
	
	private Date firstAppearanceDate;
	
	public SuperHeroDto(){
		super();
	}
	
	public SuperHeroDto(Integer id, String name, String pseudonym, String publisher, Set<String> powers, Set<String> allies, Date firstAppDate){
		this.id = id;
		this.name = name;
		this.pseudonym = pseudonym;
		this.publisher = publisher;
		this.powers = powers;
		this.allies = allies;
		this.firstAppearanceDate = firstAppDate;
	}
	
	public SuperHeroDto(String name, String pseudonym, String publisher, Set<String> powers, Set<String> allies, Date firstAppDate){
		this.name = name;
		this.pseudonym = pseudonym;
		this.publisher = publisher;
		this.powers = powers;
		this.allies = allies;
		this.firstAppearanceDate = firstAppDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPseudonym() {
		return pseudonym;
	}

	public void setPseudonym(String pseudonym) {
		this.pseudonym = pseudonym;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Set<String> getPowers() {
		return powers;
	}

	public void setPowers(Set<String> powers) {
		this.powers = powers;
	}

	public Set<String> getAllies() {
		return allies;
	}

	public void setAllies(Set<String> allies) {
		this.allies = allies;
	}

	public Date getFirstAppearanceDate() {
		return firstAppearanceDate;
	}

	public void setFirstAppearanceDate(Date firstAppearanceDate) {
		this.firstAppearanceDate = firstAppearanceDate;
	}
	
		
}
