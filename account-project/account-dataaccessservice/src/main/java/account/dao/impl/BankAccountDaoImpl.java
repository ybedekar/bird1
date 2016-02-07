package account.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import account.dao.BankAccountDao;
import account.entity.BankAccount;
import account.repository.BankAccountRepository;

@Repository
public class BankAccountDaoImpl implements BankAccountDao {
	@Autowired
	private BankAccountRepository repository;

	
    @Override
	public BankAccount getBankAccountById(Integer id){
		return repository.findOne(id);
	}
    
    
    @Override
	public BankAccount updateBankAccount(BankAccount account) {
		BankAccount oldAccount = repository.findOne(account.getId());
		oldAccount.setBusinessCode(account.getBusinessCode());
		oldAccount.setIban(account.getIban());
		oldAccount.setUserId(account.getUserId());
		BankAccount updatedAccount = repository.save(oldAccount);
		repository.flush();
		
		return updatedAccount;
	}

    
	@Override
	public BankAccount createBankAccount(BankAccount bankAccount) {
		BankAccount createdAccount = repository.save(bankAccount);
	    repository.flush();
	    
	    return createdAccount;
	}


	@Override
	public List<BankAccount> createBankAccounts(List<BankAccount> bankAccounts) {
		List<BankAccount> createdAccounts = repository.save(bankAccounts);
		repository.flush();
		
		return createdAccounts;
	}

	@Override
	public void deleteBankAccounts(List<BankAccount> bankAccounts) {
		repository.deleteInBatch(bankAccounts);
		repository.flush();
	}

	@Override
	public List<BankAccount> getBankAccountsByUser(String userId) {
		return repository.findByUserId(userId);
	}

	@Override
	public void deleteBankAccountById(Integer id) {
		repository.delete(id);
		repository.flush();
		
	}

	
}
