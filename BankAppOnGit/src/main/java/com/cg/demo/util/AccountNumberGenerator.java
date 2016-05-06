package com.cg.demo.util;
public class AccountNumberGenerator {
private static int accountNumber = 1;

	private AccountNumberGenerator() {
	}

	public static int getAccountNumber() {
		return accountNumber++;
	}
}
