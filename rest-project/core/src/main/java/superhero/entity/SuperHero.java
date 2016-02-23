package superhero.entity;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class SuperHero {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer    id;

	@Size(max = 50)
	@Column(unique=true)
	@NotEmpty
	private String name;

	@Size(max = 50)
	@Column
	private String pseudonym;

	@Size(max = 50)
	@Column
	private String publisher;

	@ElementCollection  
	protected Set<String> powers = new HashSet();

	@ElementCollection  
	protected Set<String> allies = new HashSet();

	@Column
	private Date firstAppearanceDate;

	public SuperHero(){

	}

	public SuperHero(Integer id, String name, String pseudonym, String publisher, Set<String> powers, Set<String> allies, Date firstAppDate){
		this.id = id;
		this.name = name;
		this.pseudonym = pseudonym;
		this.publisher = publisher;
		this.powers = powers;
		this.allies = allies;
		this.firstAppearanceDate = firstAppDate;
	}

	public SuperHero(String name, String pseudonym, String publisher, Set<String> powers, Set<String> allies, Date firstAppDate){
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
