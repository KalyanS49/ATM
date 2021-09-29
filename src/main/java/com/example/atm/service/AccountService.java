package com.example.atm.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.atm.exception.AmountTransactionException;
import com.example.atm.model.Account;
import com.example.atm.repository.AccountRepository;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepository;

	public Map<String, Object> accountInfo(String cardNumber, Integer pin) {
		Map<String, Object> info = new HashMap<>();
		Account account = accountRepository.findByCardNumberAndPin(cardNumber, pin);
		if(account != null) {
			info.put("status", "valid");
			info.put("firstName", account.getFirstName());
			info.put("lastName", account.getLastName());
			info.put("accountNumber", account.getAccountNumber());
			info.put("customerId", account.getCustomerId());
		} else {
			info.put("status", "invalid");
		}
		return info;
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = AmountTransactionException.class)
	public void withdrawalAmount(Double amount, Long customerId) throws AmountTransactionException {
		deductAmount(customerId, amount);
	}
	
	public Map<String, Double> accountBalance(Long customerId) {
		Map<String, Double> response = new HashMap<>();
		Account account = accountRepository.findByCustomerId(customerId);
		response.put("balance", account.getBalance());
		return response;
	}
	
	@Transactional(propagation = Propagation.MANDATORY )
	private void addAmount(String toCardNumber, Long fromCustomerId, double amount) throws AmountTransactionException {
		Account account = accountRepository.findByCardNumber(toCardNumber);
		if (account == null) {
			throw new AmountTransactionException("Invalid card number");
		}
		if(account.getCustomerId() == fromCustomerId) {
			throw new AmountTransactionException("Source and destination accounts are same");
		}
		double newBalance = account.getBalance() + amount;
		account.setBalance(newBalance);
	}
	
	@Transactional(propagation = Propagation.MANDATORY )
	private void deductAmount(Long fromCustomerId, double amount) throws AmountTransactionException {
		Account account = accountRepository.findByCustomerId(fromCustomerId);
		double newBalance = account.getBalance() - amount;
		if (account.getBalance() - amount < 0) {
			throw new AmountTransactionException("You don't have sufficient balance in your account");
		}
		account.setBalance(newBalance);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = AmountTransactionException.class)
	public void transferAmount(Long fromCustomerId, String toCardNumber, double amount) throws AmountTransactionException {
		addAmount(toCardNumber, fromCustomerId, amount);
		deductAmount(fromCustomerId, amount);
	}
}