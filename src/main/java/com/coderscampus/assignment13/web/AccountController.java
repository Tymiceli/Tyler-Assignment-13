package com.coderscampus.assignment13.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.coderscampus.assignment13.domain.Account;
import com.coderscampus.assignment13.domain.User;
import com.coderscampus.assignment13.service.AccountService;
import com.coderscampus.assignment13.service.UserService;

@Controller
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private UserService userService;
	
	@PostMapping("users/{userId}/accounts")
	public String postOneAccount(@PathVariable Long userId) {
//		Account account = new Account();
//		account.setAccountName("Account" + user.getAccounts().size() +1);
		
		accountService.saveAccount(userId);
		System.out.println(userService.findById(userId).getAccounts().size());
		return "redirect:/users/"+userId;
	}

	
	@GetMapping("users/{userId}/accounts/{accountId}")
	public String getAccountFromUser(ModelMap model, @PathVariable Long accountId) {
		System.out.println("ACCOUNT_ID: "+accountId);
		Account account = accountService.findAccountById(accountId);
//		String accountName = account.getAccountName();
		User user = account.getUsers().get(0);
		model.put("account", account);
		model.put("user", user);
		return "account";
	}
}
