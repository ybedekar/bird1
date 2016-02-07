package account.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class BankAccount {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer    id;
	
	@Size(max = 50)
    private String iban;

	@Size(max = 50)
    private String businessCode;
	
	@Size(max = 50)
	private String userId;

	public BankAccount(){
		
	}
	
	public BankAccount(Integer id, String iban, String businessCode, String userId){
		this.id = id;
		this.iban = iban;
		this.businessCode = businessCode;
		this.userId = userId;
	}
	
	public BankAccount(String iban, String businessCode, String userId){
		this.iban = iban;
		this.businessCode = businessCode;
		this.userId = userId;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getBusinessCode() {
		return businessCode;
	}

	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
    
    
}
