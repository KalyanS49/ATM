package com.example.atm.model.request;

public class LoginReq {
	private String cardNumber;
	private Integer pin;
	
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber.replaceAll("\\s", "");
	}

	public void setPin(Integer pin) {
		this.pin = pin;
	}

	public String getCardNumber() {
		return cardNumber;
	}
	
	public Integer getPin() {
		return pin;
	}
}
