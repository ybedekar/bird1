package bird.entity;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Bird {
	@Id
	private String  id;

	private String name;

	private String family;

	@ElementCollection
	private List<String> continents;

	private String added;

	private boolean visible;

	public Bird(){

	}

	public Bird(String name, String family, List<String> continents, String registrationDate, boolean visible){
		this.name = name;
		this.family = family;
		this.continents = continents;
		this.added = registrationDate;
		this.visible = visible;
	}

	public Bird(String id, String name, String family, List<String> continents, String registrationDate, boolean visible){
		this.id = id;
		this.name = name;
		this.family = family;
		this.continents = continents;
		this.added = registrationDate;
		this.visible = visible;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public List<String> getContinents() {
		return continents;
	}

	public void setContinents(List<String> continents) {
		this.continents = continents;
	}

	public String getAdded() {
		return added;
	}

	public void setAdded(String added) {
		this.added = added;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}


}
