package account.dtoconverter.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import account.dto.BankAccountDto;
import account.dtoconverter.DtoMapperService;
import account.entity.BankAccount;

@Service("DtoMapperService")
public class DtoMapperServiceImpl implements DtoMapperService {

	@Override
	public BankAccountDto getBankAccountDto(BankAccount account) {
		BankAccountDto bankAccountDto = null;
		
		if(account != null){
			bankAccountDto = new BankAccountDto(account.getIban(), account.getBusinessCode(), account.getUserId());
			if(account.getId() != null){
				bankAccountDto.setId(account.getId());
			}
		}
		
		return bankAccountDto;
	}
	
	@Override
	public List<BankAccountDto> getBankAccountDtos(List<BankAccount> accounts) {
		List<BankAccountDto> bankAccountDtos = null;
		
		if(accounts != null){
			for(BankAccount account:accounts){
				BankAccountDto dto = getBankAccountDto(account);
				if(dto != null){
					bankAccountDtos.add(dto);
				}
					
			}
		}
		
		return bankAccountDtos;
	}



	@Override
	public BankAccount getBankAccount(BankAccountDto accountDto) {
		BankAccount account = null;
		if(accountDto != null){
			account = new BankAccount(accountDto.getIban(), accountDto.getBusinessCode(), accountDto.getUserId());
			if(accountDto.getId() != null){
				account.setId(accountDto.getId());
			}
		}
		
		return account;
	}

}
