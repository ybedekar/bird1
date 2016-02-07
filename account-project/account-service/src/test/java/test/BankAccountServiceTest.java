package test;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import account.dao.BankAccountDao;
import account.dto.BankAccountDto;
import account.dtoconverter.DtoMapperService;
import account.entity.BankAccount;
import account.service.impl.BankAccountServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class BankAccountServiceTest {
	
	@Mock 
	private BankAccountDao bankAccountDao;
	
	@Mock 
	private DtoMapperService dtoMapperService;
	
	@InjectMocks 
	private BankAccountServiceImpl bankAccountService;
	
	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	
	
	@Test
	public void testGetBankAccountById(){
		
		BankAccountDto resultDto = null;
		final BankAccount bankAccount1 = new BankAccount(1,"'iban1","code1","user1");
		final BankAccountDto dto1 = new BankAccountDto(1,"iban1","code1","user1");
		
		when(bankAccountDao.getBankAccountById(1)).thenReturn(bankAccount1);
		when(dtoMapperService.getBankAccountDto(bankAccount1)).thenReturn(dto1);
        
		resultDto = bankAccountService.getBankAccountById(null, 1);
        
		Assert.assertNotNull(resultDto);
        Assert.assertNotNull(resultDto.getIban());
        Assert.assertSame(resultDto.getIban(), "iban1"); 
	}
	
	@Test
	public void testUpdateAccount(){
		BankAccount account1 = new BankAccount(3,"iban3","code3","user3");
		BankAccountDto dto1 = new BankAccountDto(3,"iban3","code3","user3");
		BankAccountDto dto2 = new BankAccountDto(3,"iban3","code3","user3");
		BankAccount account2 = new BankAccount(3,"iban3","code3","user3");
		
		when(bankAccountDao.getBankAccountById(3)).thenReturn(account1);
		when(dtoMapperService.getBankAccountDto(account1)).thenReturn(dto1);
		when(dtoMapperService.getBankAccount(dto2)).thenReturn(account2);
		when(bankAccountDao.updateBankAccount(account2)).thenReturn(account2);
		when(dtoMapperService.getBankAccountDto(account2)).thenReturn(dto2);
		
		bankAccountService.updateBankAccount(null, dto2);
		
		InOrder order = inOrder(bankAccountDao);
		InOrder order1 = inOrder(dtoMapperService);

		order.verify(bankAccountDao).getBankAccountById(account1.getId());
		order1.verify(dtoMapperService).getBankAccountDto(account1);
		order1.verify(dtoMapperService).getBankAccount(dto2);
		order.verify(bankAccountDao).updateBankAccount(account2);
		order1.verify(dtoMapperService).getBankAccountDto(account2);
		
	}
	
	@Test
	public void testCreateAccount() throws Exception{
		BankAccount account1 = new BankAccount(null,"iban3","code3","user3");
		BankAccount account2 = new BankAccount(3,"iban3","code3","user3");
		BankAccountDto dto1 = new BankAccountDto(null,"iban3","code3","user3");
		BankAccountDto dto2 = new BankAccountDto(3,"iban3","code3","user3");
	   
		when(dtoMapperService.getBankAccount(dto1)).thenReturn(account1);
		when(bankAccountDao.createBankAccount(account1)).thenReturn(account2);
		when(dtoMapperService.getBankAccountDto(account2)).thenReturn(dto2);
	
		bankAccountService.createBankAccount(null,dto1);
		InOrder order = inOrder(dtoMapperService);
		InOrder order1 = inOrder(bankAccountDao);
		
		order.verify(dtoMapperService).getBankAccount(dto1);
		order1.verify(bankAccountDao).createBankAccount(account1);
		order.verify(dtoMapperService).getBankAccountDto(account2);
	}
	
	@Test
	public void testDeleteBankAccount() throws Exception{
		BankAccount account = new BankAccount(3,"iban","businessCode","user1");
		BankAccountDto dto = new BankAccountDto(null,"iban","businessCode","user1");
		
		when(bankAccountDao.getBankAccountById(3)).thenReturn(account);
		when(dtoMapperService.getBankAccountDto(account)).thenReturn(dto);
		doNothing().when(bankAccountDao).deleteBankAccountById(3);
				
		bankAccountService.deleteBankAccountById(null, 3);
		
		InOrder order = inOrder(bankAccountDao);
		InOrder order1 = inOrder(dtoMapperService);
		order.verify(bankAccountDao).getBankAccountById(3);
		order.verify(bankAccountDao).deleteBankAccountById(3);
		
		order1.verify(dtoMapperService).getBankAccountDto(account);
		
	}
	
	@Test
	public void voidTestGetBankAccountsByUser() throws Exception{
		String userId = "user1";
		List<BankAccount> accounts = new ArrayList<BankAccount>();
		List<BankAccountDto> bankAccountsDto = new ArrayList<BankAccountDto>();
	
		when(bankAccountDao.getBankAccountsByUser(userId)).thenReturn(accounts);
		when(dtoMapperService.getBankAccountDtos(accounts)).thenReturn(bankAccountsDto);
		
		bankAccountService.getBankAccountsByUser(null, userId);
		
		InOrder order = inOrder(bankAccountDao,dtoMapperService);
		
		order.verify(bankAccountDao).getBankAccountsByUser(userId);
		order.verify(dtoMapperService).getBankAccountDtos(accounts);
		
	}
}
