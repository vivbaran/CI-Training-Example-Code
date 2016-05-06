package com.cg.demo.service;
import com.cg.demo.beans.Account;
import com.cg.demo.beans.Customer;
import com.cg.demo.exceptions.InsufficientBalanceException;
import com.cg.demo.exceptions.InsufficientInitialAmountException;
import com.cg.demo.exceptions.InvalidAccountException;

public interface AccountService {
	
	public Account createAccount(Customer c , float amount) throws InsufficientInitialAmountException;
	public Account showBalance(int accountNumber) throws InvalidAccountException;
	public Account withdraw(int accountNumber, float amount)throws InvalidAccountException, InsufficientBalanceException;
	public Account deposit(int accountNumber, float amount) throws InvalidAccountException;
	
}
