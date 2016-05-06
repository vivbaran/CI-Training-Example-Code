package com.cg.demo.service;
import com.cg.demo.beans.Account;
import com.cg.demo.beans.Customer;
import com.cg.demo.exceptions.InsufficientBalanceException;
import com.cg.demo.exceptions.InsufficientInitialAmountException;
import com.cg.demo.exceptions.InvalidAccountException;
import com.cg.demo.repo.AccountRepository;
import com.cg.demo.util.AccountNumberGenerator;

public class AccountServiceImpl implements AccountService {
	
	private AccountRepository repo;
	public static final int INITIALAMOUNT=500;
	
	public AccountServiceImpl(AccountRepository repo) {
		super();
		this.repo = repo;
	}

	@Override
	public Account createAccount(Customer c, float amount) throws InsufficientInitialAmountException {
	
		
		if(c==null){
			
			throw new IllegalArgumentException();
		}
		if(amount < INITIALAMOUNT){
			throw new InsufficientInitialAmountException();
		}
		
		Account a = new Account(AccountNumberGenerator.getAccountNumber());
		a.setCustomer(c);
		a.setBalance(amount);
		if(repo.save(a)){
			
			return a;
		}
		return null;
	}

	@Override
	public Account showBalance(int accountNumber) throws InvalidAccountException {
	
		Account a = repo.findByAccountnumber(accountNumber);
		if(a==null){
			throw new InvalidAccountException(); 
		}
		return a;
	}

	@Override
	public Account withdraw(int accountNumber, float amount)
			throws InvalidAccountException, InsufficientBalanceException {
				
		if(amount<=0){
			throw new IllegalArgumentException();
		}
		
		Account a = repo.findByAccountnumber(accountNumber);
		
		
		if(a==null){
			throw new InvalidAccountException();
		}
		if(a.getBalance()<amount){
			throw new InsufficientBalanceException();
		}
		a.setBalance(a.getBalance()-amount);	
		
		return a;
	}

	@Override
	public Account deposit(int accountNumber, float amount) throws InvalidAccountException {
	
		if(amount<=0){
			throw new IllegalArgumentException();
		}

		Account a = repo.findByAccountnumber(accountNumber);
		if(a==null){
			throw new InvalidAccountException();
		}
		a.setBalance(a.getBalance()+amount);	
		
		return a;
	}

}