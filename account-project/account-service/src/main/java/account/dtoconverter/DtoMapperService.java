package account.dtoconverter;

import java.util.List;

import account.dto.BankAccountDto;
import account.entity.BankAccount;

public interface DtoMapperService {
	public BankAccountDto getBankAccountDto(BankAccount account);
  
	public List<BankAccountDto> getBankAccountDtos(List<BankAccount> accounts);
	
	public BankAccount getBankAccount(BankAccountDto accountDto);
}
