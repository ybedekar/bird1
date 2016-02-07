package account.dto;

import org.hibernate.validator.constraints.NotBlank;

public class BankAccountDto implements Comparable{
	private Integer id;
	
	@NotBlank(message = "Iban cannot be empty")
	private String iban;

	@NotBlank(message = "Business Code cannot be empty")
	private String businessCode;

	private String userId;
	
	public BankAccountDto(){
		super();
	}
	
	public BankAccountDto(String iban, String businessCode, String userId){
		this.iban = iban;
		this.businessCode = businessCode;
		if(userId != null)
			this.userId = userId;
		else
			this.userId = "defaultUser";
	}
	
	public BankAccountDto(Integer id, String iban, String businessCode, String userId){
		this.id = id;
		this.iban = iban;
		this.businessCode = businessCode;
		if(userId != null)
			this.userId = userId;
		else
			this.userId = "defaultUser";
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
	
	public boolean isNew() {
		return (this.id == null);
	}

	@Override
	public int compareTo(Object id) {
		Integer otherId = (Integer)id;
		return this.id.compareTo(otherId);
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof BankAccountDto))
			return false;
		
		BankAccountDto dto = (BankAccountDto)obj;
		return(dto.getId().equals(id) && dto.getIban().equals(iban) && dto.getBusinessCode().equals(businessCode));
		
	}
	
	
	
}
