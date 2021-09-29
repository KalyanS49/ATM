package com.example.atm.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.atm.model.request.LoginReq;
import com.example.atm.model.request.TransferReq;
import com.example.atm.model.request.WithdrawalReq;
import com.example.atm.service.AccountService;
import com.example.atm.exception.AmountTransactionException;

/**
 * 
 * @author KALYAN
 *
 */
@RestController
@RequestMapping("/api")
public class AtmController {
	
	@Autowired
	private AccountService accountService;

	@PostMapping("/accountInfo")
	public Map<String, Object> getAccountInfo(@RequestBody LoginReq request) {
		return accountService.accountInfo(request.getCardNumber(), request.getPin());
	}
	
	@PostMapping("/balance")
	public Map<String, Double> getAccountBalance(@RequestBody WithdrawalReq request) {
		return accountService.accountBalance(request.getCustomerId());
	}
	
	@PostMapping("/withdraw")
	public Map<String, String> getWithdrawal(@RequestBody WithdrawalReq request) {
		Map<String, String> response = new HashMap<>();
		try {
			accountService.withdrawalAmount(request.getAmount(), request.getCustomerId());
			response.put("status", "success");
		} catch (AmountTransactionException e) {
			response.put("status", "error");
			response.put("message", e.getMessage());
		}
		return response;
	}
	
	@PostMapping("/transfer")
	public Map<String, String> getFundTransfer(@RequestBody TransferReq request) {
		Map<String, String> response = new HashMap<>();
		try {
			accountService.transferAmount(request.getCustomerId(), request.getCardNumber(), request.getAmount());
			response.put("status", "success");
		} catch (AmountTransactionException e) {
			response.put("status", "error");
			response.put("message", e.getMessage());
		}	
		return response;
	}
	
	@PostMapping("/changePin")
	public Map<String, String> getChangePIN() {
		Map<String, String> accountPin = new HashMap<>();
		accountPin.put("status", "success");
		return accountPin;
	}
}
