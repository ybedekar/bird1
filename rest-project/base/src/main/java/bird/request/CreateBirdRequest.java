package bird.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class CreateBirdRequest implements Serializable{
	static final long serialVersionUID = 12332L;

	private String name;

	private String family;

	private List<String> continents =new ArrayList<String>();

	private String added;

	private Boolean visible;

	public CreateBirdRequest(){

	}

	public CreateBirdRequest(String name, String family, List<String> continents, String registrationDate, boolean visible){
		this.name = name;
		this.family = family;
		this.continents = continents;
		this.added = registrationDate;
		this.visible = visible;

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

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}




}
