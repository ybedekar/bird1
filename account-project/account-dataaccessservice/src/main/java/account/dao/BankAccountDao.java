package account.dao;

import java.util.List;

import account.entity.BankAccount;

public interface BankAccountDao {
	public BankAccount createBankAccount(BankAccount bankAccount);
	
	public List<BankAccount> createBankAccounts(List<BankAccount> bankAccounts);

	public BankAccount updateBankAccount(BankAccount account);
	
	public void deleteBankAccounts(List<BankAccount> bankAccounts);

    public List<BankAccount> getBankAccountsByUser(String userId);
    
    public BankAccount getBankAccountById(Integer id);
    
    public void deleteBankAccountById(Integer id);
}
