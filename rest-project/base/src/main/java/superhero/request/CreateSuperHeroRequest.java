package superhero.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

public class CreateSuperHeroRequest implements Serializable{
	static final long serialVersionUID = 12332L;

	@NotEmpty(message="Name cannot be empty")
	private String name;

	private String pseudonym;

	private String publisher;

	private List<String> skills =new ArrayList<String>();

	private List<String> allies = new ArrayList<String>();

	private String firstAppearanceDate;

	public CreateSuperHeroRequest(){

	}

	public CreateSuperHeroRequest(String name, String pseudonym, String publisher, List<String> skills, List<String> allies, String firstDate){
		this.name = name;
		this.pseudonym = pseudonym;
		this.publisher = publisher;
		this.skills = skills;
		this.allies = allies;
		this.firstAppearanceDate = firstDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPseudonym() {
		return pseudonym;
	}

	public void setPseudonym(String pseudonym) {
		this.pseudonym = pseudonym;
	}



	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	public List<String> getAllies() {
		return allies;
	}

	public void setAllies(List<String> allies) {
		this.allies = allies;
	}

	public String getFirstAppearanceDate() {
		return firstAppearanceDate;
	}

	public void setFirstAppearanceDate(String firstAppearanceDate) {
		this.firstAppearanceDate = firstAppearanceDate;
	}


}
