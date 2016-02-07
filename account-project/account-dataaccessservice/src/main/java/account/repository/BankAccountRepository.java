package account.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import account.entity.BankAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount,Integer>{
	public List<BankAccount> findByUserId(String userId);
}
