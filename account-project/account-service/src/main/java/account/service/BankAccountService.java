package account.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import account.dto.BankAccountDto;

/*
 * Interface for bank account operations
 */
public interface BankAccountService {
	public BankAccountDto createBankAccount(HttpServletRequest request, BankAccountDto bankAccount);
	
	public void deleteBankAccountById(HttpServletRequest request, Integer id);

	public void updateBankAccount(HttpServletRequest request, BankAccountDto  bankAccount);
	
	public List<BankAccountDto> getBankAccountsByUser(
			HttpServletRequest request, String userId);

	public BankAccountDto getBankAccountById(HttpServletRequest request, Integer id);
}
