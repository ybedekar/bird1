package account.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import account.dao.BankAccountDao;
import account.dto.BankAccountDto;
import account.dtoconverter.DtoMapperService;
import account.entity.BankAccount;
import account.exceptionHandler.handler.BankAccountServiceException;
import account.exceptionHandler.handler.ErrorCode;
import account.exceptionHandler.types.InvalidRequestException;
import account.exceptionHandler.types.NotFoundException;
import account.service.BankAccountService;

@Service("bankAccountService")
public class BankAccountServiceImpl implements BankAccountService{
	@Autowired
	private BankAccountDao bankAccountDao;
	
	@Autowired
	private DtoMapperService dtoMapperService;
	
	
	public BankAccountDao getBankAccountDao() {
		return bankAccountDao;
	}

	public void setBankAccountDao(BankAccountDao bankAccountDao) {
		this.bankAccountDao = bankAccountDao;
	}

	public DtoMapperService getDtoMapperService() {
		return dtoMapperService;
	}

	public void setDtoMapperService(DtoMapperService dtoMapperService) {
		this.dtoMapperService = dtoMapperService;
	}


	@Override
	@Transactional(readOnly = true)
	public BankAccountDto getBankAccountById(HttpServletRequest request, Integer id) {
		BankAccountDto dto = null;
		
		if(id == null){
        	throw new InvalidRequestException(ErrorCode.INPUT_INVALID_PAYLOAD);
        }
        
        try{
			BankAccount bankAccount = bankAccountDao.getBankAccountById(id);
			dto = dtoMapperService.getBankAccountDto(bankAccount);
        }catch(Exception e){
        	throw new BankAccountServiceException(ErrorCode.MISC_SERVER_ERROR);
        }
        
	    return dto;
	}
	
	/*
	 * Obtain bank accounts by user from session or database
	 */
	@Override
	@Transactional(readOnly = true)
	public List<BankAccountDto> getBankAccountsByUser(HttpServletRequest request,
			String userId) {
		List<BankAccount> bankAccounts = null;
		List<BankAccountDto> bankAccountsDto = null;
		
		if(userId == null || userId.isEmpty()){
			throw new InvalidRequestException(ErrorCode.INPUT_INVALID_PAYLOAD);
		}
		
		try{
			bankAccountsDto = getBankAccountsFromSession(request);
			
			if(bankAccountsDto == null){
				//Retrieve from the database if accounts are not present in session
				bankAccounts = bankAccountDao.getBankAccountsByUser(userId);
				bankAccountsDto = dtoMapperService.getBankAccountDtos(bankAccounts);
			}
		
		}catch(Exception e){
			throw new BankAccountServiceException(ErrorCode.MISC_SERVER_ERROR);
		}
		
		return bankAccountsDto;
	}
	
	@Override
	@Transactional
	public void updateBankAccount(HttpServletRequest request,
			BankAccountDto bankAccountDto) {
		if(bankAccountDto == null || bankAccountDto.getId() == null){
			throw new InvalidRequestException(ErrorCode.INPUT_INVALID_PAYLOAD);
		}
		
		try{
			   BankAccount oldAccount = bankAccountDao.getBankAccountById(bankAccountDto.getId());
			   BankAccountDto oldDto = dtoMapperService.getBankAccountDto(oldAccount);
				
			   deleteAccountFromSession(request, oldDto);
				
			   BankAccount accounttoUpdate = dtoMapperService.getBankAccount(bankAccountDto);
			   BankAccount updatedAccount = bankAccountDao.updateBankAccount(accounttoUpdate);
			   BankAccountDto updatedDto =  dtoMapperService.getBankAccountDto(updatedAccount);
				
			   addAccountToSession(request, updatedDto);
			
		}catch(Exception e){
			throw new BankAccountServiceException(ErrorCode.MISC_SERVER_ERROR);
		}
	}

	@Override
	@Transactional
	public BankAccountDto createBankAccount(HttpServletRequest request,
			BankAccountDto bankAccountDto) {
		BankAccountDto createdAccountDto = null;
		
		if(bankAccountDto == null){
			throw new InvalidRequestException(ErrorCode.INPUT_INVALID_PAYLOAD);
		}
		
		try{
			BankAccount bankAccount = dtoMapperService.getBankAccount(bankAccountDto);
			bankAccount = bankAccountDao.createBankAccount(bankAccount);
			if(bankAccount != null){
				createdAccountDto = dtoMapperService.getBankAccountDto(bankAccount);
			}
			addAccountToSession(request, createdAccountDto);
		}catch(Exception e){
			throw new BankAccountServiceException(ErrorCode.MISC_SERVER_ERROR);
		}
		
		return createdAccountDto;
	}

	@Override
	@Transactional
	public void deleteBankAccountById(HttpServletRequest request, Integer id) {
		BankAccount bankAccount = bankAccountDao.getBankAccountById(id);
		if(bankAccount == null){
			throw new NotFoundException(ErrorCode.RESOURCE_NOT_FOUND.getDescription());
		}
		try{
			BankAccountDto bankAccountDto = dtoMapperService.getBankAccountDto(bankAccount);
			deleteAccountFromSession(request, bankAccountDto);
			bankAccountDao.deleteBankAccountById(id);
		}catch(Exception e){
			throw new BankAccountServiceException(ErrorCode.MISC_SERVER_ERROR);
		}
	}

	
	/*
	 * Delete bank account from session
	 */
	private void deleteAccountFromSession(HttpServletRequest request, BankAccountDto account){
		if(request != null){
			ConcurrentHashMap<String,List<BankAccountDto>> userToAccountsMap = (ConcurrentHashMap<String,List<BankAccountDto>>)request.getSession().getAttribute("userToAccountsMap");
		    if(userToAccountsMap != null){
		    	List<BankAccountDto> oldDtos = userToAccountsMap.get(request.getSession().getId());
		    	if(oldDtos != null){
		    		oldDtos.remove(account);
		    	}
		    }
		}
	}

	
	/*
	 * Add bank account to session
	 */
	private void addAccountToSession(HttpServletRequest request, BankAccountDto account){
		List<BankAccountDto> bankAccounts = null;
		if(request != null){
			ConcurrentHashMap<String,List<BankAccountDto>> userToAccountsMap = (ConcurrentHashMap<String,List<BankAccountDto>>)request.getSession().getAttribute("userToAccountsMap");
		    if(userToAccountsMap == null){
		    	userToAccountsMap = new ConcurrentHashMap<String,List<BankAccountDto>>();
		    	bankAccounts = new ArrayList<BankAccountDto>();
		    	userToAccountsMap.put(request.getSession().getId(), bankAccounts);
		    	request.getSession().setAttribute("userToAccountsMap", userToAccountsMap);
		    }else{
		    	bankAccounts = userToAccountsMap.get(request.getSession().getId());
		    }
		    bankAccounts.add(account);
		    
		 }
		
	}
	
	/*
	 * Obtain bank accounts from session
	 */
	private List<BankAccountDto> getBankAccountsFromSession(HttpServletRequest request){
		List<BankAccountDto> bankAccountsDto = null;
		
		if(request != null){
			ConcurrentHashMap<String,List<BankAccountDto>> userToAccountsMap = (ConcurrentHashMap<String,List<BankAccountDto>>)request.getSession().getAttribute("userToAccountsMap");
		    if(userToAccountsMap != null){
		    	bankAccountsDto = userToAccountsMap.get(request.getSession().getId());
		    }
		    
		}
		
		return bankAccountsDto;
	}
	
}
