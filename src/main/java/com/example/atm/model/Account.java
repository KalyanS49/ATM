/**
 * 
 */
package com.example.atm.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity()
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long customerId;
	private String firstName;
	private String lastName;
	private String cardNumber;
	private Long accountNumber;
	private Integer pin;
	private Double balance;
	
	

	public Long getCustomerId() {
		return customerId;
	}



	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getCardNumber() {
		return cardNumber;
	}



	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}



	public Long getAccountNumber() {
		return accountNumber;
	}



	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}



	public Integer getPin() {
		return pin;
	}



	public void setPin(Integer pin) {
		this.pin = pin;
	}



	public Double getBalance() {
		return balance;
	}



	public void setBalance(Double balance) {
		this.balance = balance;
	}



	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return firstName+" "+lastName;
	}
	
}
