package account.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import account.dto.BankAccountDto;
import account.exceptionHandler.handler.BankAccountServiceException;
import account.service.BankAccountService;

@RestController
@RequestMapping(BankAccountController.URL)
public class BankAccountController {
	public final static String URL = "/accounts";
	
	@Autowired
	private BankAccountService bankAccountService;
	
	@RequestMapping(value = "/show", method=RequestMethod.GET)
    public ModelAndView showAccounts(HttpServletRequest request) throws BankAccountServiceException{
		    ModelAndView modelView = new ModelAndView("accounts");	
			populatePage(request, modelView);
				
			return modelView;
    }
	
		
	@RequestMapping(value = "/add", method=RequestMethod.GET)
    public ModelAndView addAccount() throws BankAccountServiceException{
			BankAccountDto bankAccountDto = new BankAccountDto();
			
			ModelMap model = new ModelMap();
			model.addAttribute("bankAccountForm", bankAccountDto);
			ModelAndView modelView = new ModelAndView("addAccount", model);
							
			return modelView;
    }
	
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public ModelAndView editAccount(HttpServletRequest request, @PathVariable("id") int id) throws BankAccountServiceException{
			BankAccountDto bankAccountDto = bankAccountService.getBankAccountById(request, id);
			
			ModelMap model = new ModelMap();
			model.addAttribute("bankAccountForm", bankAccountDto);
			ModelAndView modelView = new ModelAndView("addAccount", model);
				
			return modelView;
    }
	
	@RequestMapping(value = "/save", method=RequestMethod.POST)
    public ModelAndView saveOrUpdateAccount(HttpServletRequest request, @ModelAttribute("bankAccountForm") @Validated BankAccountDto bankAccountDto, BindingResult bindingResult) throws BankAccountServiceException{
		if (bindingResult.hasErrors()) {
			ModelMap model = new ModelMap();
			model.addAttribute("bankAccountForm", bankAccountDto);
			ModelAndView modelView = new ModelAndView("addAccount", model);
			
			return modelView;
        }
		
		if(bankAccountDto != null && bankAccountDto.getId() != null){
			bankAccountService.updateBankAccount(request, bankAccountDto);
		}
		else{
			//Create a new account
			bankAccountService.createBankAccount(request, bankAccountDto);		
		}	
		
		ModelAndView modelView = new ModelAndView(new RedirectView("/accounts/show"));	
		populatePage(request, modelView);
			
		return modelView;
    }
	
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public ModelAndView deleteAccount(HttpServletRequest request, @PathVariable("id") int id) throws BankAccountServiceException{
			bankAccountService.deleteBankAccountById(request, id);
			
			ModelAndView modelView = new ModelAndView(new RedirectView("/accounts/show"));	
			populatePage(request, modelView);
				
			return modelView;
    }
	
	
	/*
	 * Populate accounts from session or database
	 */
	private void populatePage(HttpServletRequest request, ModelAndView modelView){
		List<BankAccountDto> accounts = null;
			
		HttpSession currentSession = request.getSession(true);
		if(currentSession != null){
			accounts = bankAccountService.getBankAccountsByUser(request, request.getSession().getId());
	        
		}
	
		if(accounts != null){
			modelView.addObject("bankAccounts", accounts);
		}
	}
	
	
}
